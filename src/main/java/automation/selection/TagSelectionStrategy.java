package automation.selection;

import automation.selection.RandomizedSelectionStrategy;
import helpers.ListUtils;
import instagram.core_objects.HashTag;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.List;

public abstract class TagSelectionStrategy extends RandomizedSelectionStrategy<HashTag> {
    protected Instagram4j instagram4j;

    public TagSelectionStrategy(Instagram4j instagram4j, List<HashTag> hashTags) {
        this.instagram4j = instagram4j;
        this.setOriginals(hashTags);
    }

    public TagSelectionStrategy(Instagram4j instagram4j, String ... hashTags) {
        this(instagram4j, ListUtils.toListOfHashTags(instagram4j, hashTags));
    }

    @Override
    public HashTag select(List<HashTag> originalList) {
        this.setOriginals(originalList);
        return this.select();
    }
}