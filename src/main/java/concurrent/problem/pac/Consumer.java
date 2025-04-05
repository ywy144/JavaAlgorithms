package concurrent.problem.pac;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;

@Slf4j
public class Consumer<T> implements Runnable {

    private final BlockingDeque<T> deque;

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

    public Consumer(BlockingDeque<T> deque, int sleepTime, int iterations) {
        this.deque = deque;
        this.sleepTime = sleepTime;
        this.iterations = iterations;
    }
}
