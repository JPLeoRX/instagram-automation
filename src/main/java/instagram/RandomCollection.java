package instagram;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;

public class RandomCollection<AnyObject> extends ArrayList<AnyObject> {
    private SecureRandom random = new SecureRandom();

    public RandomCollection(int initialCapacity) {
        super(initialCapacity);
    }

    public RandomCollection() {
    }

    public RandomCollection(Collection<? extends AnyObject> c) {
        super(c);
    }

    public AnyObject getRandom() {
        return get(random.nextInt(this.size()));
    }
}