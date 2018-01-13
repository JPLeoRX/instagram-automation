package instagram.request_handling.media_interactions;

import instagram.request_handling.ActionRequest;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetMediaLikersRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetMediaLikersResult;

/**
 * Basic action
 *
 * Get people who liked this media
 *
 * @author Leo Ertuna
 */
public class GetLikers extends ActionRequest<InstagramGetMediaLikersResult> {
    private long mediaId;

    public GetLikers(Instagram4j instagram4j, long mediaId) {
        super(instagram4j);
        this.mediaId = mediaId;
    }

    @Override
    protected InstagramGetMediaLikersRequest buildRequest() {
        return new InstagramGetMediaLikersRequest(mediaId);
    }
}