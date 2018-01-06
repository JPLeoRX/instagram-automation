package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUploadVideoRequest;

import java.io.File;
import java.io.IOException;

public class MediaUploadHelper {
    private static final Logger log = LogManager.getLogger();

    private static void debug(String methodName, String message) {
        log.debug("Method " + methodName + "(): " + message);
    }

    public static void uploadPhoto(Instagram4j instagram, String photoFilePath, String photoComment) {
        debug("uploadPhoto", "Started");

        File photo = new File(photoFilePath);
        debug("uploadPhoto", "Photo file created");

        InstagramUploadPhotoRequest r = new InstagramUploadPhotoRequest(photo, photoComment);
        debug("uploadPhoto", "Upload request created");

        try {
            instagram.sendRequest(r);
            debug("uploadPhoto", "Sending request");
        }

        catch (IOException e) {
            debug("uploadPhoto", "Exception when sending request");
            e.printStackTrace();
            return;
        }

        debug("uploadPhoto", "Uploaded successfully");
    }


    public static void uploadVideo(Instagram4j instagram, String videoFilePath, String videoComment) {
        debug("uploadVideo", "Started");

        File photo = new File(videoFilePath);
        debug("uploadVideo", "Video file created");

        InstagramUploadVideoRequest r = new InstagramUploadVideoRequest(photo, videoComment);
        debug("uploadVideo", "Upload request created");

        try {
            instagram.sendRequest(r);
            debug("uploadVideo", "Sending request");
        }

        catch (IOException e) {
            debug("uploadVideo", "Exception when sending request");
            e.printStackTrace();
            return;
        }

        debug("uploadVideo", "Uploaded successfully");
    }
}
