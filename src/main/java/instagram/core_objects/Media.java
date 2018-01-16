package instagram.core_objects;

import helpers.ListUtils;
import instagram.request_handling.media_interactions.*;
import instagram.request_handling.media_interactions.PostComment;
import instagram.request_handling.message_interactions.ShareMedia;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramComment;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;

import java.util.List;
import java.util.Objects;

public class Media extends ObjectInstagram {
    // Must be initialized with
    private InstagramFeedItem feedItem;

    // Fields that are filled later
    private long id;
    private User author;
    private List<User> likers;
    private List<InstagramComment> comments;

    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public Media(Instagram4j instagram4j, InstagramFeedItem feedItem) {
        super(instagram4j);
        this.feedItem = feedItem;
        this.id = feedItem.getPk();
        this.author = new User(instagram4j, feedItem.getUser().getUsername());
    }
    //------------------------------------------------------------------------------------------------------------------



    // Actions
    //------------------------------------------------------------------------------------------------------------------
    public void like() {
        new Like(instagram4j, id).sendRequest();
    }

    public void unlike() {
        new Unlike(instagram4j, id).sendRequest();
    }

    public void comment(String commentText) {
        new PostComment(instagram4j, id, commentText).sendRequest();
    }

    public void shareWith(List<User> recipients, String messageText) {
        new ShareMedia(instagram4j, ListUtils.toListOfIds(recipients), id, messageText).sendRequest();
    }

    public void sendTo(User recipient, String messageText) {
        shareWith(ListUtils.createWithOneElement(recipient), messageText);
    }

    public void refresh() {
        likers = null;
        comments = null;
    }
    //------------------------------------------------------------------------------------------------------------------



    // Getters
    //------------------------------------------------------------------------------------------------------------------
    public long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getCaption() {
        return String.valueOf(feedItem.getCaption().get("text"));
    }

    public int getNumberOfLikes() {
        return feedItem.getLike_count();
    }

    public List<User> getLikers() {
        if (likers == null) {
            GetLikers getLikers = new GetLikers(instagram4j, id);
            likers = ListUtils.toListOfUsers(instagram4j, getLikers.sendRequest().getUsers());
        }

        return likers;
    }

    public boolean hasInLikers(User user) {
        return getLikers().contains(user);
    }

    public List<InstagramComment> getComments() {
        if (comments == null) {
            GetComments getComments = new GetComments(instagram4j, id);
            comments = getComments.sendRequest().getComments();
        }

        return comments;
    }
    //------------------------------------------------------------------------------------------------------------------



    // Others
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return id == media.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Media: id=" + id;
    }
    //------------------------------------------------------------------------------------------------------------------
}