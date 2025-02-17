package concurrent.problem;

import concurrent.problem.pac.Consumer;
import concurrent.problem.pac.PACLockDeque;
import concurrent.problem.pac.PACReentrantLockDeque;
import concurrent.problem.pac.Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class PacTest {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            8, // 核心线程数
            16, // 最大线程数
            60L, // 非核心线程的空闲存活时间
            TimeUnit.SECONDS, // 存活时间的单位
            new LinkedBlockingQueue<>(10), // 任务队列，最大容量为10
            Executors.defaultThreadFactory(), // 默认线程工厂
            new ThreadPoolExecutor.DiscardOldestPolicy() // 拒绝策略
    );

    private static final AtomicInteger actionCount = new AtomicInteger(0);

    @Test
    public void PACReentrantLockDequeTest() throws InterruptedException {
        long start = System.nanoTime();
        PACLockDeque<Integer> deque = new PACReentrantLockDeque<>(10);
        executor.submit(new Producer(deque, actionCount, 1, 500));
        executor.submit(new Producer(deque, actionCount, 1, 500));
        executor.submit(new Producer(deque, actionCount, 1, 500));
        executor.submit(new Producer(deque, actionCount, 1, 500));
        executor.submit(new Consumer(deque, actionCount, 1, 500));
        executor.submit(new Consumer(deque, actionCount, 1, 500));
        executor.submit(new Consumer(deque, actionCount, 1, 500));
        executor.submit(new Consumer(deque, actionCount, 1, 500));
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
        long end = System.nanoTime();
        log.debug("PACReentrantLockDequeTest took " + (end - start) / 1_000_000 + " ms");
    }

    @Test
    public void PACLinkedBlockingDequeTest() throws InterruptedException {
        long start = System.nanoTime();
        LinkedBlockingDeque<Integer> deque = new LinkedBlockingDeque<>(10);
        executor.submit(new Producer(deque, actionCount, 1, 500));
        executor.submit(new Producer(deque, actionCount, 1, 500));
        executor.submit(new Producer(deque, actionCount, 1, 500));
        executor.submit(new Producer(deque, actionCount, 1, 500));
        executor.submit(new Consumer(deque, actionCount, 1, 500));
        executor.submit(new Consumer(deque, actionCount, 1, 500));
        executor.submit(new Consumer(deque, actionCount, 1, 500));
        executor.submit(new Consumer(deque, actionCount, 1, 500));
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
        long end = System.nanoTime();
        log.debug("PACReentrantLockDequeTest took " + (end - start) / 1_000_000 + " ms");
    }
}
