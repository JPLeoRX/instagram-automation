package instagram.request_handling.media_interactions;

import instagram.request_handling.ActionRequest;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramPostCommentRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramPostCommentResult;

/**
 * Basic action
 *
 * Add a comment to this media
 *
 * @author Leo Ertuna
 */
public class PostComment extends ActionRequest<InstagramPostCommentResult> {
    private long mediaId;
    private String commentText;

    public PostComment(Instagram4j instagram4j, long mediaId, String commentText) {
        super(instagram4j);
        this.mediaId = mediaId;
        this.commentText = commentText;
    }

    @Override
    protected InstagramPostCommentRequest buildRequest() {
        return new InstagramPostCommentRequest(mediaId, commentText);
    }
}