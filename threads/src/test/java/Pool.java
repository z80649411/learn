import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 邓仁波 on 2017-9-21.
 * java线程池的使用
 * 以下方法使用CountDownLatch的原因如果不使用cd.await()
 * 等待未完成的进程结束则当测试跑完后还有进程没跑完
 * 会导致输出结果不完整 不利于观察
 * git测试
 */
public class Pool {
    @Test
    public void cachedThreadPoolTest() {
        System.out.println("Start first SYNC program.");
        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
        //线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //线程同步工具
        CountDownLatch cd = new CountDownLatch(10);
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println(index + " in");
//                        Thread.sleep(1000);
                        System.out.println(index + " out");
                        cd.countDown();
                        // 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能打印5条记录，之后线程一直阻塞
                        semp.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        //暂停完成的线程等待所有线程完成任务
        try {
            cd.await(60,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cachedThreadPool.shutdown();
        cachedThreadPool.shutdown();
        System.out.println("Finish first SYNC program.");
    }


    @Test
    public void fixedThreadPoolTest() {
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        CountDownLatch cd = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index + " in");
                        Thread.sleep(1000);
                        System.out.println(index + " out");
                        cd.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            cd.await();
            fixedThreadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish first SYNC program.");
    }

    @Test
    public void scheduledThreadPoolTest() {
        //创建一个定长线程池，支持定时及周期性任务执行
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        CountDownLatch cd = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
//            //延迟1秒执行
//            scheduledThreadPool.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(index + " in");
//                    System.out.println(index + " out");
//                    cd.countDown();
//                }
//            }, 5, TimeUnit.SECONDS);

            //表示延迟1秒后每3秒执行一次。
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + " in");
                    System.out.println(index + " out");
                }
            }, 1, 3, TimeUnit.SECONDS);
        }
        try {
            cd.await();
            scheduledThreadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void singleThreadExecutorTest() {
        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index + " in");
                        Thread.sleep(2000);
                        System.out.println(index + " out");
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        singleThreadExecutor.shutdown();
    }


    public static int maxPool(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static AtomicInteger sum = new AtomicInteger(0);
    private static ReentrantLock r = new ReentrantLock();
    private static int sum0 = 0;

    @Test
    public void arraysMaxTest() throws ExecutionException, InterruptedException {

        int[] arr0 = {1, 2, 109};
        int[][] a = new int[10000][3];
        for (int i = 0; i < 10000; i++) {
            a[i] = arr0;
        }
        CountDownLatch downLatch = new CountDownLatch(10000);
        ExecutorService cachedThreadPool = Executors.newScheduledThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            final int index = i;
            Future<Integer> submit = cachedThreadPool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    r.lock();
                    sum0 = sum0 + maxPool(a[index]);
                    r.unlock();
                    downLatch.countDown();
                    return maxPool(a[index]);
                }
            });
            sum.addAndGet(submit.get());
        }
        downLatch.await();
        System.out.println(sum);
        System.out.println(sum0);
    }


}
