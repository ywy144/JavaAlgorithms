package concurrent.problem.pac;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Consumer implements Runnable {

    private final BlockingDeque<Integer> deque;

    private final AtomicInteger actionCount;

    private final int sleepTime;

    private final int iterations;

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            deque.pollFirst();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                log.warn("Thread interrupted: {}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    public Consumer(BlockingDeque<Integer> deque, AtomicInteger actionCount, int sleepTime, int iterations) {
        this.deque = deque;
        this.actionCount = actionCount;
        this.sleepTime = sleepTime;
        this.iterations = iterations;
    }
}
