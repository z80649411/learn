/**
 * Created by 邓仁波 on 2017-9-22.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 延期队列 优先级队列的一种变体
 *
 * @author lenovo
 */

class Delayedtask implements Runnable, Delayed {

    private static int counter = 0;
    private final int id = counter++;// id 从0++
    private final int delta;// 多少毫秒停止
    private final long trigger;// 纳秒停止

    // 保存了任务被创建的顺序
    protected static List<Delayedtask> sequence = new ArrayList<Delayedtask>();

    public Delayedtask(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime()
                + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);

        sequence.add(this);
    }

    @Override
    public void run() {
        System.out.println(this + " run");
    }

    public String toString() {
        // 1个id对应一项任务 会安顺序来执行任务
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    // 排序触发
    @Override
    public int compareTo(Delayed o) {
        Delayedtask that = (Delayedtask) o;
        if (trigger < that.trigger)
            return -1;
        if (trigger > that.trigger)
            return 1;
        return 0;
    }

    /**
     * 告知延迟到期有多长时间 ，或者延迟在多长时间之前已经到期
     */
    @Override
    public long getDelay(TimeUnit unit) {
        // 对象到期时间 -现在时间 =执行时间
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    // 关闭所有事物的途径 具体做剥去 是将其放置为队列的最后一个元素。
    public static class EndSentinel extends Delayedtask {

        private ExecutorService exec;

        public EndSentinel(int delayInMilliseconds, ExecutorService c) {
            super(delayInMilliseconds);
            exec = c;
        }

        @Override
        public void run() {
            for (Delayedtask pt : sequence) {
                System.out.print(pt.summary() + " ");
            }
            System.out.println();
            System.out.println(this + "  calling shutdownNow");
            exec.shutdownNow();
        }
    }
}

// 自身是一个任务
class DelayedtaskConsumer implements Runnable {
    private DelayQueue<Delayedtask> q;

    public DelayedtaskConsumer(DelayQueue<Delayedtask> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                q.take().run();// run task with the current thread 与当前线程运行的任务
            }
        } catch (InterruptedException e) {
            System.out.println("可接受的方式退出=");
            // 可接受的方式退出
        }
        System.out.println("finished DelayedtaskConsumer");
    }
}

public class DelayQueueDemo {
    public static void main(String[] args) {
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<Delayedtask> queue = new DelayQueue<Delayedtask>();
        // fill with tasks that have random delays:充满随机延迟的任务
        for (int i = 0; i < 20; i++) {
            queue.put(new Delayedtask(rand.nextInt(5000)));
        }
        // 设置停止点
        queue.add(new Delayedtask.EndSentinel(5000, exec));
        exec.execute(new DelayedtaskConsumer(queue));
        System.out.println("end");
    }
}
