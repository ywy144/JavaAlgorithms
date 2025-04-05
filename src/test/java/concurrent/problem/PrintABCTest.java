package concurrent.problem;

import concurrent.problem.printABC.PrintABC;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PrintABCTest {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            8, // 核心线程数
            16, // 最大线程数
            60L, // 非核心线程的空闲存活时间
            TimeUnit.SECONDS, // 存活时间的单位
            new LinkedBlockingQueue<>(10), // 任务队列，最大容量为10
            Executors.defaultThreadFactory(), // 默认线程工厂
            new ThreadPoolExecutor.DiscardOldestPolicy() // 拒绝策略
    );

    @Test
    public void testPrintABCWithLock() throws InterruptedException {
        long start = System.nanoTime();
        executor.submit(() -> PrintABC.printABCWithLock('A'));
        executor.submit(() -> PrintABC.printABCWithLock('B'));
        executor.submit(() -> PrintABC.printABCWithLock('C'));
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        long end = System.nanoTime();
        log.debug("\ntestPrintABCWithLock took " + (end - start) / 1_000_000 + " ms");
    }

    @Test
    public void testPrintABCWithSync() throws InterruptedException {
        long start = System.nanoTime();
        executor.submit(() -> PrintABC.printABCWithSynchronized('A'));
        executor.submit(() -> PrintABC.printABCWithSynchronized('B'));
        executor.submit(() -> PrintABC.printABCWithSynchronized('C'));
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        long end = System.nanoTime();
        log.debug("\ntestPrintABCWithSync took " + (end - start) / 1_000_000 + " ms");
    }
}
