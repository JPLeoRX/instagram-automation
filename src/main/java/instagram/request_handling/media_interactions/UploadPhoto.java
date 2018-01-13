package instagram.request_handling.media_interactions;

import instagram.request_handling.ActionRequest;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigurePhotoResult;

import java.io.File;

public class UploadPhoto extends ActionRequest<InstagramConfigurePhotoResult> {
    private String photoFilePath;
    private String photoComment;

    public UploadPhoto(Instagram4j instagram4j, String photoFilePath, String photoComment) {
        super(instagram4j);
        this.photoFilePath = photoFilePath;
        this.photoComment = photoComment;
    }

    @Override
    protected InstagramUploadPhotoRequest buildRequest() {
        return new InstagramUploadPhotoRequest(new File(photoFilePath), photoComment);
    }
}