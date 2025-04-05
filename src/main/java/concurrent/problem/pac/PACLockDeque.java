package concurrent.problem.pac;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

public abstract class PACLockDeque<T> implements BlockingDeque<T> {

    protected Deque<T> delegate;

    protected int capacity = 10;

    protected boolean limitCapacity = false;

    public PACLockDeque(Deque<T> deque) {
        capacity = deque.size();
        this.delegate = deque;
    }

    public PACLockDeque(int capacity) {
        this.capacity = capacity;
        delegate = new ArrayDeque<>(capacity);
    }

    public PACLockDeque(int capacity, boolean limitCapacity) {
        this.capacity = capacity;
        this.limitCapacity = limitCapacity;
        delegate = new ArrayDeque<>(capacity);
    }

    public PACLockDeque() {
        delegate = new ArrayDeque<>(capacity);
    }

    @Override
    public T pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offerFirst(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offerLast(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addFirst(T t) {

    }

    @Override
    public void addLast(T t) {

    }

    @Override
    public void putFirst(T t) throws InterruptedException {

    }

    @Override
    public void putLast(T t) throws InterruptedException {

    }

    @Override
    public boolean offerFirst(T t, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public boolean offerLast(T t, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public T takeFirst() throws InterruptedException {
        return null;
    }

    @Override
    public T takeLast() throws InterruptedException {
        return null;
    }

    @Override
    public T pollFirst(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public T pollLast(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public void put(T t) throws InterruptedException {

    }

    @Override
    public boolean offer(T t, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T take() throws InterruptedException {
        return null;
    }

    @Override
    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void push(T t) {

    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public T getLast() {
        return null;
    }

    @Override
    public T peekFirst() {
        return null;
    }

    @Override
    public T peekLast() {
        return null;
    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super T> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super T> c, int maxElements) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
