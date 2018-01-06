package base;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

import java.io.IOException;

public class Run {
    public static void main(String[] args) {
        // Login
        Instagram4j i = LoginHelper.login("jpleorx1234", "admin1234");

        // Get tag feed
        InstagramFeedResult f = TagHelper.getFeed(i, "jdm");

        // Like last post
        MediaInteractionHelper.like(i, f.getItems().get(0).getPk());

    }
}
