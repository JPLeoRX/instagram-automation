package instagram.request_handling;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;

public abstract class ActionRequestWithStatusResult extends ActionRequest<StatusResult> {
    public ActionRequestWithStatusResult(Instagram4j instagram4j) {
        super(instagram4j);
    }
}