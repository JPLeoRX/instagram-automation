package instagram.request_handling.user_interactions;

import instagram.request_handling.ActionRequestWithUserFollowersResult;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;

public class GetFollowing extends ActionRequestWithUserFollowersResult {
    public GetFollowing(Instagram4j instagram4j, long userId, String maxId) {
        super(instagram4j, userId, maxId);
    }

    public GetFollowing(Instagram4j instagram4j, long userId) {
        super(instagram4j, userId);
    }

    @Override
    public InstagramGetUserFollowingRequest buildRequestWithMaxId() {
        return new InstagramGetUserFollowingRequest(userId, maxId);
    }

    @Override
    public InstagramGetUserFollowingRequest buildRequestWithoutMaxId() {
        return new InstagramGetUserFollowingRequest(userId);
    }
}