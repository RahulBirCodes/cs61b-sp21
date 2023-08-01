package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }

    public T max() {
        T max = get(0);
        for (int i = 0; i < size(); i++) {
            if (comp.compare(get(i), max) > 0) {
                max = get(i);
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        T max = get(0);
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), max) > 0) {
                max = get(i);
            }
        }
        return max;
    }
}
