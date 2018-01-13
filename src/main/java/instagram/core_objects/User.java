package instagram.core_objects;

import instagram.ListUtils;
import instagram.request_handling.user_interactions.UserFeed;
import instagram.request_handling.message_interactions.ShareMedia;
import instagram.request_handling.message_interactions.ShareMessage;
import instagram.request_handling.user_interactions.*;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends ObjectInstagram {
    // Must be initialized with
    private String username;

    // Fields that are filled later
    private InstagramUser user;
    private List<User> followers;
    private List<User> followings;
    private List<Media> mediaShort;
    private List<Media> media;

    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public User(Instagram4j instagram4j, String username) {
        super(instagram4j);
        this.username = username;
    }
    //------------------------------------------------------------------------------------------------------------------



    // Actions
    //------------------------------------------------------------------------------------------------------------------
    public void follow() {
        new Follow(instagram4j, getId()).sendRequest();
    }

    public void unfollow() {
        new Unfollow(instagram4j, getId()).sendRequest();
    }

    public void sendMedia(Media media, String messageText) {
        new ShareMedia(instagram4j, ListUtils.createWithOneElement(String.valueOf(this.getId())), media.getId(), messageText);
    }

    public void sendMessage(String messageText) {
        new ShareMessage(instagram4j, ListUtils.createWithOneElement(String.valueOf(this.getId())), messageText);
    }
    //------------------------------------------------------------------------------------------------------------------



    // Getters
    //------------------------------------------------------------------------------------------------------------------
    private InstagramUser getUser() {
        if (user == null)
            user = new FindByUsername(instagram4j, username).sendRequest().getUser();
        return user;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return getUser().getPk();
    }

    public int getNumberOfMedia() {
        return getUser().getMedia_count();
    }

    public int getNumberOfFollowers() {
        return getUser().getFollower_count();
    }

    public int getNumberOfFollowings() {
        return getUser().getFollowing_count();
    }

    public List<User> getFollowers() {
        if (followers == null) {
            // Get initial result
            InstagramGetUserFollowersResult usersResult = new GetFollowers(instagram4j, getId()).sendRequest();

            // Create new user summary list
            List<InstagramUserSummary> userSummaryList = new ArrayList<>(usersResult.getUsers());

            // If there are next parts of the list
            while (usersResult.getNext_max_id() != null) {
                // Get new result
                usersResult = new GetFollowers(instagram4j, getId(), usersResult.getNext_max_id()).sendRequest();

                // Add all new users
                userSummaryList.addAll(usersResult.getUsers());
            }

            // Return converted results
            followers = ListUtils.toListOfUsers(instagram4j, userSummaryList);
        }

        return followers;
    }

    public List<User> getFollowing() {
        if (followings == null) {
            // Get initial result
            InstagramGetUserFollowersResult usersResult = new GetFollowing(instagram4j, getId()).sendRequest();

            // Create new user summary list
            List<InstagramUserSummary> userSummaryList = new ArrayList<>(usersResult.getUsers());

            // If there are next parts of the list
            while (usersResult.getNext_max_id() != null) {
                // Get new result
                usersResult = new GetFollowing(instagram4j, getId(), usersResult.getNext_max_id()).sendRequest();

                // Add all new users
                userSummaryList.addAll(usersResult.getUsers());
            }

            // Return converted results
            followings = ListUtils.toListOfUsers(instagram4j, userSummaryList);
        }

        return followings;
    }

    public boolean hasInFollowers(User user) {
        return getFollowers().contains(user);
    }

    public boolean hasInFollowings(User user) {
        return getFollowing().contains(user);
    }

    public List<Media> getMediaShort() {
        // Get initial result
        InstagramFeedResult userFeedResult = new UserFeed(instagram4j, getId()).sendRequest();

        // Create new media feed items list
        List<InstagramFeedItem> mediaFeedItemList = new ArrayList<>(userFeedResult.getItems());

        // Return converted results
        return ListUtils.toListOfMedia(instagram4j, mediaFeedItemList);
    }

    public List<Media> getMedia() {
        if (media == null) {
            // Get initial result
            InstagramFeedResult userFeedResult = new UserFeed(instagram4j, getId()).sendRequest();

            // Create new media feed items list
            List<InstagramFeedItem> mediaFeedItemList = new ArrayList<>(userFeedResult.getItems());

            // If there are next parts of the list
            while (userFeedResult.getNext_max_id() != null) {
                // Get new result
                userFeedResult = new UserFeed(instagram4j, getId(), userFeedResult.getNext_max_id()).sendRequest();

                // Add all new media
                mediaFeedItemList.addAll(userFeedResult.getItems());
            }

            // Return converted results
            media = ListUtils.toListOfMedia(instagram4j, mediaFeedItemList);
        }

        return media;
    }
    //------------------------------------------------------------------------------------------------------------------



    // Others
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User: username=" + username;
    }
    //------------------------------------------------------------------------------------------------------------------
}