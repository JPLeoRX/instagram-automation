package instagram.request_handling.user_interactions;

import instagram.request_handling.ActionRequestWithUserFollowersResult;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;

public class GetFollowers extends ActionRequestWithUserFollowersResult {
    public GetFollowers(Instagram4j instagram4j, long userId, String maxId) {
        super(instagram4j, userId, maxId);
    }

    public GetFollowers(Instagram4j instagram4j, long userId) {
        super(instagram4j, userId);
    }

    @Override
    public InstagramGetUserFollowersRequest buildRequestWithMaxId() {
        return new InstagramGetUserFollowersRequest(userId, maxId);
    }

    @Override
    public InstagramGetUserFollowersRequest buildRequestWithoutMaxId() {
        return new InstagramGetUserFollowersRequest(userId);
    }
}