package concurrent.problem.pac;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class PACRLockDeque<T> extends PACLockDeque<T> {

    protected static final ReentrantLock lock = new ReentrantLock();

    protected static final Condition notEmpty = lock.newCondition();

    protected static final Condition notFull = lock.newCondition();

    public PACRLockDeque(int capacity) {
        super(capacity);
    }

    public PACRLockDeque(int capacity, boolean limitCapacity) {
        super(capacity, limitCapacity);
    }

    @Override
    public T pollFirst() {
        lock.lock();
        try {
            while (CollectionUtils.isEmpty(delegate)) {
                notEmpty.await();
            }
            T element = delegate.pollFirst();
            if (limitCapacity) {
                notFull.signalAll();
            }
            return element;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
        if (limitCapacity) {
            try {
                while (delegate.size() == capacity) {
                    notFull.await();
                }
                delegate.offerFirst(t);
                notEmpty.signalAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            } finally {
                lock.unlock();
            }
        } else {
            try {
                delegate.offerFirst(t);
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }
        return true;
    }

    @Override
    public boolean offerLast(T t) {
        return false;
    }
}
