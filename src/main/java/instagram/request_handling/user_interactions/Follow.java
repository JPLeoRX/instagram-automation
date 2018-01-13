package instagram.request_handling.user_interactions;

import instagram.request_handling.ActionRequestWithStatusResult;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramFollowRequest;

public class Follow extends ActionRequestWithStatusResult {
    private long userId;

    public Follow(Instagram4j instagram4j, long userId) {
        super(instagram4j);
        this.userId = userId;
    }

    @Override
    protected InstagramFollowRequest buildRequest() {
        return new InstagramFollowRequest(userId);
    }
}