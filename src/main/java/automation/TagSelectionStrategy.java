package automation;

import instagram.core_objects.HashTag;
import instagram.request_handling.tags_interactions.TagFeed;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TagSelectionStrategy {
    private Instagram4j instagram4j;

    private List<HashTag> hashTags;

    public TagSelectionStrategy(Instagram4j instagram4j, String ... hashTags) {
        this.instagram4j = instagram4j;
        this.hashTags = new ArrayList<>(hashTags.length);
        for (String hashTag : hashTags)
            this.hashTags.add(new HashTag(instagram4j, hashTag));
    }
}
