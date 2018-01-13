package instagram.request_handling.message_interactions;

import instagram.request_handling.ActionRequestWithStatusResult;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramDirectShareRequest;

import java.util.List;

public class ShareMessage extends ActionRequestWithStatusResult {
    private List<String> recipientsId;
    private String message;

    public ShareMessage(Instagram4j instagram4j, List<String> recipientsId, String message) {
        super(instagram4j);
        this.recipientsId = recipientsId;
        this.message = message;
    }

    @Override
    protected InstagramDirectShareRequest buildRequest() {
        return InstagramDirectShareRequest.builder(InstagramDirectShareRequest.ShareType.MESSAGE, recipientsId).message(message).build();
    }
}