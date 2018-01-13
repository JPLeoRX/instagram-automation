package automation;

import instagram.core_objects.HashTag;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.ArrayList;
import java.util.List;

public abstract class TagSelectionStrategy {
    protected Instagram4j instagram4j;
    protected List<HashTag> hashTagsList = new ArrayList<>();

    public TagSelectionStrategy(Instagram4j instagram4j, String ... hashTags) {
        this.instagram4j = instagram4j;
        for (String hashTag : hashTags)
            this.hashTagsList.add(new HashTag(instagram4j, hashTag));
    }

    public abstract HashTag randomTag();
}