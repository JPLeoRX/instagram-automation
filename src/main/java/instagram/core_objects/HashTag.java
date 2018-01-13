package instagram.core_objects;

import instagram.MapUtils;
import instagram.request_handling.tags_interactions.GetTags;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchTagsResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchTagsResultTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashTag extends ObjectInstagram {
    // Must be initialized with
    private String hashTag;

    // Fields that are filled in later
    private List<SingleHashTag> similarHashTags;
    private SingleHashTag thisHashTag;



    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public HashTag(Instagram4j instagram4j, String hashTag) {
        super(instagram4j);
        this.hashTag = hashTag;
    }
    //------------------------------------------------------------------------------------------------------------------



    // Getters
    //------------------------------------------------------------------------------------------------------------------
    public String getHashTag() {
        return hashTag;
    }

    public List<SingleHashTag> getSimilarHashTags() {
        if (similarHashTags == null) {
            // Create a new list
            ArrayList<SingleHashTag> newHashTags = new ArrayList<>();

            // Send a new request
            InstagramSearchTagsResult result = new GetTags(instagram4j,hashTag).sendRequest();

            // For each tag found
            for (InstagramSearchTagsResultTag singleTag : result.getResults())
                newHashTags.add(new SingleHashTag(instagram4j, singleTag));

            // Return result
            similarHashTags = newHashTags;
        }

        return similarHashTags;
    }

    public HashMap<String, Integer> getSimilarHashTagsAndNumberOfMedia() {
        return MapUtils.toMapTagMediaCount(getSimilarHashTags());
    }

    public SingleHashTag getThisHashTag() {
        if (thisHashTag == null)
            thisHashTag = getSimilarHashTags().get(0);
        return thisHashTag;
    }

    public int getMediaCount() {
        return getThisHashTag().getMediaCount();
    }

    public List<Media> getMedia() {
        return getThisHashTag().getMedia();
    }
    //------------------------------------------------------------------------------------------------------------------


    @Override
    public String toString() {
        return "HashTag: tag=" + hashTag + ", mediaCount=" + getMediaCount() + ", similarTags=" + getSimilarHashTags();
    }
}