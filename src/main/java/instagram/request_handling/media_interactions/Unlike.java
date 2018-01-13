package instagram.request_handling.media_interactions;

import instagram.request_handling.ActionRequest;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramUnlikeRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLikeResult;

/**
 * Basic action
 *
 * Unlike this media
 *
 * @author Leo Ertuna
 */
public class Unlike extends ActionRequest<InstagramLikeResult> {
    private long mediaId;

    public Unlike(Instagram4j instagram4j, long mediaId) {
        super(instagram4j);
        this.mediaId = mediaId;
    }

    @Override
    protected InstagramUnlikeRequest buildRequest() {
        return new InstagramUnlikeRequest(mediaId);
    }
}