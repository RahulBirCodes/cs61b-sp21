package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    /* Basic tests for checkpoint */

    @Test
    public void addAndRemoveConsecutiveItemsAndCheckGet() {
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

//    @Test
//    public void testResize() {
//        ArrayDeque<Integer> ad = new ArrayDeque<>();
//        ad.addFirst(6);
//        ad.addFirst(5);
//        ad.addFirst(4);
//        ad.addFirst(3);
//        ad.addFirst(2);
//        ad.addFirst(1);
//
//        ad.resize(20);
//    }
}
