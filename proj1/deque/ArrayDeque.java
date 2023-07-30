package deque;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[10];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        size--;
        return items[nextFirst];
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }

        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        size--;
        return items[nextLast];
    }

    @Override
    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }

    @Override
    public void printDeque() {
        //TODO - add functionality to print the contents of the deque
    }
}
