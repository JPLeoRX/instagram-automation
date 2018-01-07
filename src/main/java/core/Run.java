package core;

import core.instagram_helpers.LoginHelper;
import core.instagram_helpers.MediaInteractionHelper;
import core.instagram_helpers.TagHelper;
import core.instagram_helpers.UserInteractionHelper;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

public class Run {
    public static void main(String[] args) {
        // Login
        Instagram4j i = LoginHelper.login("jpleorx1234", "admin1234");

        // Get tag feed
        InstagramFeedResult f = TagHelper.getFeed(i, "jdm");

        // Like last post
        MediaInteractionHelper.like(i, f.getItems().get(0).getPk());

        // Find user
        InstagramUser u = UserInteractionHelper.findUser(i, "jpleorx");
        System.out.println("Followers: " + u.getFollower_count());
        System.out.println("Following: " + u.getFollowing_count());
        System.out.println("Bio: " + u.getBiography());
        System.out.println("Posts: " + u.getMedia_count());
    }
}
