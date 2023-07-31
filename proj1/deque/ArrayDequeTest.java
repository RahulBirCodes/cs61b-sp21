package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    /* Basic tests for checkpoint */

    @Test
    public void addAndRemoveConsecutiveItemsAndCheckGetTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(6);
        ad.addFirst(5);
        ad.addFirst(4);
        ad.addFirst(3);
        ad.addFirst(2);
        ad.addFirst(1);
        ad.addLast(7);
        ad.addLast(8);

        ad.removeLast();
        ad.removeLast();
        ad.removeFirst();

        assertEquals((int) ad.get(0), 2);
        assertEquals(ad.get(7), null);
    }

    @Test
    public void addCausingResizeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 50; i++) {
            ad.addFirst(50 - i);
            ad.addLast(50 + i);
        }
        ad.printDeque();
    }

    @Test
    public void removeItemsCausingResizeDownTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.addLast(4);

        ad.removeLast();
        ad.removeLast();
        ad.removeLast();

        assertEquals(ad.size(), 1);
    }

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> ll  = new ArrayDeque<>();

        int N = 5000;
        for (int i = 0; i < N; i++) {
            int opNum = StdRandom.uniform(0, 5);
            if (opNum == 0) {
                // addFirst
                ll.addFirst(StdRandom.uniform(0, 100));
            } else if (opNum == 1) {
                // addLast
                ll.addLast(StdRandom.uniform(0, 100));
            } else if (opNum == 2) {
                // removeFirst
                ll.removeFirst();
            } else if (opNum == 3) {
                // removeLast
                ll.removeLast();
            } else if (opNum == 4) {
                // get arbitrary value
                if (!ll.isEmpty()) {
                    ll.get(StdRandom.uniform(0, ll.size()));
                }
            }
        }

    }
}
