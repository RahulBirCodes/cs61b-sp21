package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    public class NutritionLabel {
        public String foodName;
        public int calories;

        public NutritionLabel(String foodName, int calories) {
            this.foodName = foodName;
            this.calories = calories;
        }

        public static class NameComparator implements Comparator<NutritionLabel> {
            public int compare(NutritionLabel n1, NutritionLabel n2) {
                return n1.foodName.compareTo(n2.foodName);
            }
        }

        public static class CaloriesComparator implements Comparator<NutritionLabel> {
            public int compare(NutritionLabel n1, NutritionLabel n2) {
                return n1.calories - n2.calories;
            }
        }
    }

    @Test
    public void testFoodNutritionComparator() {
        MaxArrayDeque<NutritionLabel> lst = new MaxArrayDeque<>(new NutritionLabel.NameComparator());
        lst.addLast(new NutritionLabel("Apple", 50));
        lst.addLast(new NutritionLabel("Orange", 30));
        lst.addLast(new NutritionLabel("Pretzels", 75));

        NutritionLabel maxByName = lst.max();
        assertEquals(maxByName.foodName, "Pretzels");

        NutritionLabel maxByCalories = lst.max(new NutritionLabel.CaloriesComparator());
        assertEquals(maxByCalories.calories, 75);
    }
}
