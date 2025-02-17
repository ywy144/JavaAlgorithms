package concurrent.problem.pac;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class PACReentrantLockDeque<T> extends PACLockDeque<T> {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition notEmpty = lock.newCondition();

    private static final Condition notFull = lock.newCondition();

    public PACReentrantLockDeque(int i) {
        super(i);
    }

    @Override
    public T pollFirst() {
        lock.lock();
        try {
            while (CollectionUtils.isEmpty(delegate)) {
                //log.debug("Queue is empty. Waiting for offering elements...");
                notEmpty.await();
            }
            T element = delegate.pollFirst();
            notFull.signal();
            //log.debug("{} polled an element: {}, currentDeque: {}", Thread.currentThread().getName(), element, delegate.toArray());
            return element;
        } catch (InterruptedException e) {
            //log.info("Thread interrupted: {}", e.getMessage());
            Thread.currentThread().interrupt(); // 重新设置中断状态
            return null;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T pollLast() {
        return null;
    }

    @Override
    public boolean offerFirst(T t) {
        lock.lock();
        try {
            while (delegate.size() == capacity) {
                //log.debug("Queue is full. Waiting for polling elements...");
                notFull.await();
            }
            delegate.offerFirst(t);
            notEmpty.signal();
            //log.debug("{} offered an element: {}, currentDeque: {}", Thread.currentThread().getName(), t, delegate.toArray());
        } catch (InterruptedException e) {
            //log.warn("Thread interrupted: {}", e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
        return false;
    }

    @Override
    public boolean offerLast(T t) {

        return false;
    }
}
