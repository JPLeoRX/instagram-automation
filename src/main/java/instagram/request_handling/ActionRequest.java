package instagram.request_handling;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramRequest;

import java.io.IOException;

public abstract class ActionRequest<Response> {
    protected Instagram4j instagram4j;

    public ActionRequest(Instagram4j instagram4j) {
        this.instagram4j = instagram4j;
    }

    protected abstract <Request extends InstagramRequest<Response>> Request buildRequest();

    public Response sendRequest() {
        try {
            return instagram4j.sendRequest(buildRequest());
        }

        catch (IOException | NoClassDefFoundError e) {
            e.printStackTrace();
            return null;
        }
    }
}