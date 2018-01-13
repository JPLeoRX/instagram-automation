package instagram.request_handling.tags_interactions;

import instagram.request_handling.ActionRequest;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchTagsRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchTagsResult;

public class GetTags extends ActionRequest<InstagramSearchTagsResult> {
    private String hashtag;

    public GetTags(Instagram4j instagram4j, String hashtag) {
        super(instagram4j);
        this.hashtag = hashtag;
    }

    @Override
    protected InstagramSearchTagsRequest buildRequest() {
        return new InstagramSearchTagsRequest(hashtag);
    }
}
