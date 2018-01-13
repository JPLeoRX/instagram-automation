package instagram.request_handling.media_interactions;

import instagram.request_handling.ActionRequest;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetMediaCommentsRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetMediaCommentsResult;

/**
 * Basic action
 *
 * Get comments from a media
 *
 * @author Leo Ertuna
 */
public class GetComments extends ActionRequest<InstagramGetMediaCommentsResult> {
    private long mediaId;

    public GetComments(Instagram4j instagram4j, long mediaId) {
        super(instagram4j);
        this.mediaId = mediaId;
    }

    @Override
    protected InstagramGetMediaCommentsRequest buildRequest() {
        return new InstagramGetMediaCommentsRequest(String.valueOf(mediaId), null);
    }
}