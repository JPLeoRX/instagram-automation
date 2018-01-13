package instagram.request_handling;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;

public abstract class ActionRequestWithUserFollowersResult extends ActionRequest<InstagramGetUserFollowersResult> {
    protected long userId;
    protected String maxId;

    public ActionRequestWithUserFollowersResult(Instagram4j instagram4j, long userId, String maxId) {
        super(instagram4j);
        this.userId = userId;
        this.maxId = maxId;
    }

    public ActionRequestWithUserFollowersResult(Instagram4j instagram4j, long userId) {
        super(instagram4j);
        this.userId = userId;
    }

    protected abstract <Request extends InstagramRequest<InstagramGetUserFollowersResult>> Request buildRequestWithMaxId();

    protected abstract <Request extends InstagramRequest<InstagramGetUserFollowersResult>> Request buildRequestWithoutMaxId();

    @Override
    protected <Request extends InstagramRequest<InstagramGetUserFollowersResult>> Request buildRequest() {
        if (maxId == null)
            return buildRequestWithoutMaxId();
        else
            return buildRequestWithMaxId();
    }
}