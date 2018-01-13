package instagram.request_handling.user_interactions;

import instagram.request_handling.ActionRequest;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;

public class FindByUsername extends ActionRequest<InstagramSearchUsernameResult> {
    private String username;

    public FindByUsername(Instagram4j instagram4j, String username) {
        super(instagram4j);
        this.username = username;
    }

    @Override
    protected InstagramSearchUsernameRequest buildRequest() {
        return new InstagramSearchUsernameRequest(username);
    }
}