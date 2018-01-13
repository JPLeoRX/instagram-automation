package instagram;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomWeightedCollectionTest {

    @Test
    public void nextRandom() {
        RandomWeightedCollection<String> a = new RandomWeightedCollection<>();
        a.addWeighted(6, "cat");
        a.addWeighted(3, "mouse");
        a.addWeighted(1, "dog");


        int countCat = 0;
        int countMouse = 0;
        int countDog = 0;
        for (int i = 0; i < 1000; i++) {
            String c = a.next();
            if (c.equalsIgnoreCase("cat"))
                countCat++;
            else if (c.equalsIgnoreCase("mouse"))
                countMouse++;
            else if (c.equalsIgnoreCase("dog"))
                countDog++;
        }

        assertEquals(600, countCat, 50);
        assertEquals(300, countMouse, 50);
        assertEquals(100, countDog, 50);

        System.out.println("Cat: " + countCat + ", Mouse: " + countMouse + ", Dog: " + countDog);
    }

    @Test
    public void loop() {
        for (int i = 0; i < 100; i++)
            this.nextRandom();
    }
}