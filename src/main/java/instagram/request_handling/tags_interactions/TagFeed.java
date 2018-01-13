package instagram.request_handling.tags_interactions;

import instagram.request_handling.ActionRequestWithFeedResult;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;

public class TagFeed extends ActionRequestWithFeedResult {
    private String hashtag;

    public TagFeed(Instagram4j instagram4j, String hashtag, String maxId) {
        super(instagram4j, maxId);
        this.hashtag = hashtag;
    }

    public TagFeed(Instagram4j instagram4j, String hashtag) {
        super(instagram4j);
        this.hashtag = hashtag;
    }

    @Override
    protected InstagramTagFeedRequest buildRequestWithMaxId() {
        return new InstagramTagFeedRequest(hashtag, maxId);
    }

    @Override
    protected InstagramTagFeedRequest buildRequestWithoutMaxId() {
        return new InstagramTagFeedRequest(hashtag);
    }
}