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

    private int getPreviousIndexCircular(int index) {
        if (index == 0) {
            return items.length - 1;
        } else {
            return --index;
        }
    }

    private int getNextIndexCircular(int index) {
        return (index + 1) % items.length;
    }

    public void resize(int capacity) {
        T[] resizedItems = (T[]) new Object[capacity];
        int firstSourceArrItemIndex = getNextIndexCircular(nextFirst);
        int lastSourceArrItemIndex = getPreviousIndexCircular(nextLast);
        if (lastSourceArrItemIndex < firstSourceArrItemIndex) {
            int firstHalfNumberOfITC = items.length - firstSourceArrItemIndex;
            System.arraycopy(items, firstSourceArrItemIndex, resizedItems, 0, firstHalfNumberOfITC);
            System.arraycopy(items, 0, resizedItems, firstHalfNumberOfITC, size - firstHalfNumberOfITC);
        } else {
            System.arraycopy(items, firstSourceArrItemIndex, resizedItems, 0, size);
        }
        items = resizedItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = getPreviousIndexCircular(nextFirst);
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = getNextIndexCircular(nextLast);
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
        if (size < items.length / 4) {
            resize(items.length / 4);
        }
        nextFirst = getNextIndexCircular(nextFirst);
        size--;
        return items[nextFirst];
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        if (size < items.length / 4) {
            resize(items.length / 4);
        }
        nextLast = getPreviousIndexCircular(nextLast);
        size--;
        return items[nextLast];
    }

    @Override
    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }

    @Override
    public void printDeque() {
        int first = getNextIndexCircular(nextFirst);
        int last = getPreviousIndexCircular(nextLast);
        if (last < first) {
            for (int i = first; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i <= last; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = first; i <= last; i++) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.print("\n");
    }
}
