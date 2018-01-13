package instagram.request_handling;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

public abstract class ActionRequestWithFeedResult extends ActionRequest<InstagramFeedResult> {
    protected String maxId;

    public ActionRequestWithFeedResult(Instagram4j instagram4j, String maxId) {
        super(instagram4j);
        this.maxId = maxId;
    }

    public ActionRequestWithFeedResult(Instagram4j instagram4j) {
        super(instagram4j);
    }

    protected abstract <Request extends InstagramRequest<InstagramFeedResult>> Request buildRequestWithMaxId();

    protected abstract <Request extends InstagramRequest<InstagramFeedResult>> Request buildRequestWithoutMaxId();

    @Override
    protected <Request extends InstagramRequest<InstagramFeedResult>> Request buildRequest() {
        if (maxId == null)
            return buildRequestWithoutMaxId();
        else
            return buildRequestWithMaxId();
    }
}
