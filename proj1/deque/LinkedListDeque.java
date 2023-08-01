package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class ListNode {
        private T value;
        private ListNode next;
        private ListNode prev;

        ListNode(T value, ListNode next, ListNode prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
    private int size;
    private ListNode sentinel;

    public LinkedListDeque() {
        // sentinel value is irrelevant and should point to itself in a "cycle"
        sentinel = new ListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        ListNode prevFirstNode = sentinel.next;
        ListNode nodeToAdd = new ListNode(item, prevFirstNode, sentinel);
        sentinel.next = nodeToAdd;
        prevFirstNode.prev = nodeToAdd;
        size++;
    }

    @Override
    public void addLast(T item) {
        ListNode prevLastNode = sentinel.prev;
        ListNode nodeToAdd = new ListNode(item, sentinel, prevLastNode);
        prevLastNode.next = nodeToAdd;
        sentinel.prev = nodeToAdd;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        ListNode l = sentinel.next;
        while (l.next != sentinel) {
            System.out.print(l.value + " ");
            l = l.next;
        }
        System.out.print(l.value);
        System.out.print("\n");
    }

    @Override
    public T removeFirst() {
        T removedVal = sentinel.next.value;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (removedVal != null) {
            size--;
        }
        return removedVal;
    }

    @Override
    public T removeLast() {
        T removedVal = sentinel.prev.value;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (removedVal != null) {
            size--;
        }
        return removedVal;
    }

    @Override
    public T get(int index) {
        ListNode l = sentinel.next;
        int currentInd = 0;
        while (currentInd != index && l != sentinel) {
            l = l.next;
            currentInd++;
        }
        return l.value;
    }

    private T getRecursive(ListNode node, int index) {
        if (node == sentinel) {
            return null;
        }
        if (index == 0) {
            return node.value;
        }
        return getRecursive(node.next, index - 1);
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

//    public boolean equals(Object o) {
//        if (o instanceof Deque lst) {
//            if (this.size() != lst.size()) {
//                return false;
//            }
//            ListNode l = sentinel.next;
//            int i = 0;
//            while (l != sentinel) {
//                if (!lst.get(i).equals(l.value)) {
//                    return false;
//                }
//                l = l.next;
//                i++;
//            }
//            return true;
//        }
//        return false;
//    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> lst = (Deque<T>) o;
        if (this.size() != lst.size()) {
            return false;
        }
        ListNode l = sentinel.next;
        int i = 0;
        while (l != sentinel) {
            if (!lst.get(i).equals(l.value)) {
                return false;
            }
            l = l.next;
            i++;
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private ListNode nextNode = sentinel.next;

        @Override
        public boolean hasNext() {
            return nextNode != sentinel;
        }

        @Override
        public T next() {
            T nextItem = nextNode.value;
            nextNode = nextNode.next;
            return nextItem;
        }
    }
}
