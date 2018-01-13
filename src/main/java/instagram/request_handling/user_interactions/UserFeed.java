package instagram.request_handling.user_interactions;

import instagram.request_handling.ActionRequestWithFeedResult;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;

/**
 * Get feed of the user
 *
 * @author Leo Ertuna
 */
public class UserFeed extends ActionRequestWithFeedResult {
    private long userId;

    public UserFeed(Instagram4j instagram4j, long userId, String maxId) {
        super(instagram4j, maxId);
        this.userId = userId;
    }

    public UserFeed(Instagram4j instagram4j, long userId) {
        super(instagram4j);
        this.userId = userId;
    }

    @Override
    protected InstagramUserFeedRequest buildRequestWithMaxId() {
        return new InstagramUserFeedRequest(userId, maxId, 0);
    }

    @Override
    protected InstagramUserFeedRequest buildRequestWithoutMaxId() {
        return new InstagramUserFeedRequest(userId);
    }
}