package instagram.request_handling.message_interactions;

import instagram.request_handling.ActionRequestWithStatusResult;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramDirectShareRequest;

import java.util.List;

public class ShareMedia extends ActionRequestWithStatusResult {
    private List<String> recipientsId;
    private String mediaId;
    private String message;

    public ShareMedia(Instagram4j instagram4j, List<String> recipientsId, long mediaId, String message) {
        super(instagram4j);
        this.recipientsId = recipientsId;
        this.mediaId = String.valueOf(mediaId);
        this.message = message;
    }

    @Override
    protected InstagramDirectShareRequest buildRequest() {
        return InstagramDirectShareRequest.builder(InstagramDirectShareRequest.ShareType.MEDIA, recipientsId).mediaId(mediaId).message(message).build();
    }
}