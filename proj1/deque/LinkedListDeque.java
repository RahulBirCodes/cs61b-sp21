package deque;

import afu.org.checkerframework.checker.oigj.qual.O;

public class LinkedListDeque<T> implements Deque<T> {
    private class ListNode {
        public T value;
        public ListNode next;
        public ListNode prev;

        public ListNode(T value, ListNode next, ListNode prev) {
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
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        ListNode l = sentinel.next;
        while (l != sentinel) {
            System.out.print(l.value + " ");
            l = l.next;
        }
        System.out.print("\n");
    }

    @Override
    public T removeFirst() {
        T removedVal = sentinel.next.value;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return removedVal;
    }

    @Override
    public T removeLast() {
        T removedVal = sentinel.prev.value;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return removedVal;
    }

    @Override
    public T get(int index) {
        ListNode l = sentinel.next;
        int currentInd = 0;
        while (currentInd != index) {
            l = l.next;
            currentInd++;
        }
        return l.value;
    }

    private T getRecursive(ListNode node, int index) {
        if (index == 0) {
            return node.value;
        }
        return getRecursive(node.next, index - 1);
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }
}
