package instagram;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomCollectionTest {

    @Test
    public void getRandom() {
        RandomCollection<String> a = new RandomCollection<>();
        a.add("a1");
        a.add("a2");
        a.add("a3");
        a.add("a4");
        a.add("a5");
        a.add("a6");

        for (int i = 0; i < 10000; i++)
            a.getRandom();
    }
}