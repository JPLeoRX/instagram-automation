package instagram;

import java.util.TreeMap;

public class RandomWeightedCollection<AnyObject> extends RandomCollection<AnyObject> {
    // Weighted randomization map
    private TreeMap<Integer, AnyObject> map = new TreeMap<>();

    // Keep track of combined weight
    private int totalWeight = 0;



    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public RandomWeightedCollection() {

    }
    //------------------------------------------------------------------------------------------------------------------



    public void addWeighted(int weight, AnyObject object) {
        totalWeight += weight;
        map.put(totalWeight, object);
    }

    @Override
    public AnyObject getRandom() {
        int value = random.nextInt(totalWeight) + 1;
        return map.ceilingEntry(value).getValue();
    }

    @Override
    @Deprecated
    public AnyObject getRandom(int bound) {
        return getRandom();
    }
}