package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
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

    private void resize(int capacity) {
        T[] resizedItems = (T[]) new Object[capacity];
        int firstSourceArrItemIndex = getNextIndexCircular(nextFirst);
        int lastSourceArrItemIndex = getPreviousIndexCircular(nextLast);
        if (lastSourceArrItemIndex < firstSourceArrItemIndex) {
            int firstHalfOfITC = items.length - firstSourceArrItemIndex;
            System.arraycopy(items, firstSourceArrItemIndex, resizedItems, 0, firstHalfOfITC);
            System.arraycopy(items, 0, resizedItems, firstHalfOfITC, size - firstHalfOfITC);
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
        for (int i = 0; i < size() - 1; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.print(get(size() - 1) + "\n");
    }


    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> lst = (Deque<T>) o;
        if (this.size() != lst.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            // we can use this.get since it has a constant runtime unlike linked list implementation
            if (!lst.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int nextInd = 0;

        @Override
        public boolean hasNext() {
            return nextInd < size;
        }

        @Override
        public T next() {
            T nextItem = get(nextInd);
            nextInd++;
            return nextItem;
        }
    }
}
