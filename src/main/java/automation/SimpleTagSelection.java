package automation;

import instagram.RandomCollection;
import instagram.core_objects.HashTag;
import org.brunocvcunha.instagram4j.Instagram4j;

public class SimpleTagSelection extends TagSelectionStrategy {
    private RandomCollection<HashTag> randomTags = new RandomCollection<>();

    public SimpleTagSelection(Instagram4j instagram4j, String... hashTags) {
        super(instagram4j, hashTags);
        randomTags.addAll(this.hashTagsList);
    }

    @Override
    public HashTag randomTag() {
        return randomTags.getRandom();
    }
}