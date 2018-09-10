import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 邓仁波 on 2017-9-22.
 * 阻塞队列
 */
public class BlockingQueueTest {
    private AtomicInteger scnum = new AtomicInteger(0);
    private AtomicInteger xfnum = new AtomicInteger(0);

    @Test
    public void arrayBlockingQueueTest() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(2000);
        for (int i = 0; i < 1000; i++) {
            final int index = i;
            cachedThreadPool.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    queue.put("第" + index + "个商品");
                    scnum.addAndGet(1);
                    countDownLatch.countDown();
                    return null;
                }
            });
            cachedThreadPool.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    queue.take();
                    xfnum.addAndGet(1);
                    System.out.println("消费" + index);
                    countDownLatch.countDown();
                    return null;
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cachedThreadPool.shutdown();
        System.out.println(scnum.get());
        System.out.println(xfnum.get());

    }
}
