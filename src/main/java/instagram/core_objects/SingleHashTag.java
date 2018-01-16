package instagram.core_objects;

import helpers.ListUtils;
import instagram.request_handling.tags_interactions.TagFeed;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchTagsResultTag;

import java.util.List;
import java.util.Objects;

public class SingleHashTag extends ObjectInstagram {
    // Must be initialized with
    private String hashTag;
    private int mediaCount;

    // Fields that are filled in later
    private List<Media> media;

    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public SingleHashTag(Instagram4j instagram4j, InstagramSearchTagsResultTag searchTagsResultTag) {
        super(instagram4j);
        this.hashTag = searchTagsResultTag.getName();
        this.mediaCount = searchTagsResultTag.getMedia_count();
    }
    //------------------------------------------------------------------------------------------------------------------


    // Getters
    //------------------------------------------------------------------------------------------------------------------
    public String getHashTag() {
        return hashTag;
    }

    public int getMediaCount() {
        return mediaCount;
    }

    public List<Media> getMedia() {
        if (media == null)
            media = ListUtils.toListOfMedia(instagram4j, new TagFeed(instagram4j, hashTag).sendRequest().getItems());
        return media;
    }
    //------------------------------------------------------------------------------------------------------------------



    // Others
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleHashTag that = (SingleHashTag) o;
        return Objects.equals(hashTag, that.hashTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashTag);
    }


    @Override
    public String toString() {
        return "Single HashTag: tag=" + hashTag + ", mediaCount=" + mediaCount;
    }
    //------------------------------------------------------------------------------------------------------------------
}