package instagram;

import java.security.SecureRandom;
import java.util.TreeMap;

public class RandomWeightedCollection<AnyObject> {
    private TreeMap<Integer, AnyObject> map = new TreeMap<Integer, AnyObject>();
    private SecureRandom random = new SecureRandom();
    private int totalWeight = 0;

    public void add(int weight, AnyObject object) {
        totalWeight += weight;
        map.put(totalWeight, object);
    }

    public AnyObject next() {
        int value = random.nextInt(totalWeight) + 1;
        return map.ceilingEntry(value).getValue();
    }
}