package concurrent.problem.pac;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;

@Slf4j
public class IntegerProducer implements Runnable {

    private final BlockingDeque<Integer> deque;

    private int product;

    private final int sleepTime;

    private final int iterations;

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            deque.offerFirst(product);
            product++;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                log.warn("Thread interrupted: {}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    public IntegerProducer(BlockingDeque<Integer> deque, int product, int sleepTime, int iterations) {
        this.deque = deque;
        this.product = product;
        this.sleepTime = sleepTime;
        this.iterations = iterations;
    }
}
