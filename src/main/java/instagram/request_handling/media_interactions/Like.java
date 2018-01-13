package instagram.request_handling.media_interactions;

import instagram.request_handling.ActionRequest;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramLikeRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLikeResult;

/**
 * Basic action
 *
 * Like this media
 *
 * @author Leo Ertuna
 */
public class Like extends ActionRequest<InstagramLikeResult> {
    private long mediaId;

    public Like(Instagram4j instagram4j, long mediaId) {
        super(instagram4j);
        this.mediaId = mediaId;
    }

    @Override
    protected InstagramLikeRequest buildRequest() {
        return new InstagramLikeRequest(mediaId);
    }
}