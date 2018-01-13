package instagram.request_handling.media_interactions;

import instagram.request_handling.ActionRequest;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramUploadVideoRequest;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;

import java.io.File;

public class UploadVideo extends ActionRequest<StatusResult> {
    private String videoFilePath;
    private String videoComment;

    public UploadVideo(Instagram4j instagram4j, String videoFilePath, String videoComment) {
        super(instagram4j);
        this.videoFilePath = videoFilePath;
        this.videoComment = videoComment;
    }

    @Override
    protected InstagramUploadVideoRequest buildRequest() {
        return new InstagramUploadVideoRequest(new File(videoFilePath), videoComment);
    }
}