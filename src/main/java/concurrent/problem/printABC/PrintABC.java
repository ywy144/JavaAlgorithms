package concurrent.problem.printABC;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition expectedCharCondition = lock.newCondition();

    private static char currentChar = 'A';

    private static final char START_CHAR = 'A';

    private static final AtomicInteger count = new AtomicInteger(0);

    private static final int MAX_COUNT = 10000;

    public static void printABCWithLock(char expectedChar) {
        while (count.get() < MAX_COUNT) {
            lock.lock();
            try {
                while (currentChar != expectedChar) {
                    expectedCharCondition.await();
                }
                System.out.print(currentChar);
                currentChar = (char) (START_CHAR + count.incrementAndGet() % 3);
                expectedCharCondition.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void printABCWithSynchronized(char expectedChar) {
        while (count.get() < MAX_COUNT) {
            synchronized (lock) {
                try {
                    while (currentChar != expectedChar) {
                        lock.wait();
                    }
                    System.out.print(currentChar);
                    currentChar = (char) (START_CHAR + count.incrementAndGet() % 3);
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
