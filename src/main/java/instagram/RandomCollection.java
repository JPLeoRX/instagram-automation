package instagram;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;

public class RandomCollection<AnyObject> extends ArrayList<AnyObject> implements RandomInterface<AnyObject> {
    // Random generator
    protected SecureRandom random = new SecureRandom();

    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public RandomCollection(int initialCapacity) {
        super(initialCapacity);
    }

    public RandomCollection() {

    }

    public RandomCollection(Collection<? extends AnyObject> c) {
        super(c);
    }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public AnyObject getRandom() {
        return getRandom(this.size());
    }

    @Override
    public AnyObject getRandom(int bound) {
        return get(random.nextInt(bound));
    }
}