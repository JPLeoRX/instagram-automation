package automation.tag_selection;

import automation.RandomizedSelectionStrategy;
import automation.SelectionStrategy;
import instagram.ListUtils;
import instagram.RandomCollection;
import instagram.core_objects.HashTag;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.ArrayList;
import java.util.List;

public abstract class TagSelectionStrategy extends RandomizedSelectionStrategy<HashTag> {
    protected Instagram4j instagram4j;

    public TagSelectionStrategy(Instagram4j instagram4j, List<HashTag> hashTags) {
        this.instagram4j = instagram4j;
        this.setOriginalList(hashTags);
    }

    public TagSelectionStrategy(Instagram4j instagram4j, String ... hashTags) {
        this(instagram4j, ListUtils.toListOfHashTags(instagram4j, hashTags));
    }
}