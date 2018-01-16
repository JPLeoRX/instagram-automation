package helpers;

import instagram.core_objects.HashTag;
import instagram.core_objects.Media;
import instagram.core_objects.SingleHashTag;
import instagram.core_objects.User;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListUtils {
    public static List<User> toListOfUsers(Instagram4j instagram4j, List<InstagramUserSummary> userSummaryList) {
        ArrayList<User> users = new ArrayList<>();
        for (InstagramUserSummary userSummary : userSummaryList)
            users.add(new User(instagram4j, userSummary.getUsername()));
        return users;
    }

    public static List<String> toListOfIds(List<User> users) {
        ArrayList<String> ids = new ArrayList<>();
        for (User user : users)
            ids.add(String.valueOf(user.getId()));
        return ids;
    }

    public static List<Media> toListOfMedia(Instagram4j instagram4j, List<InstagramFeedItem> instagramFeedItems) {
        ArrayList<Media> medias = new ArrayList<>();
        for (InstagramFeedItem item : instagramFeedItems)
            medias.add(new Media(instagram4j, item));
        return medias;
    }

    public static <Any> List<Any> createWithOneElement(Any object) {
        ArrayList<Any> list = new ArrayList<>();
        list.add(object);
        return list;
    }

    public static boolean hasUntouched(List<Media> media, User user) {
        for (Media pic : media)
            if (!pic.hasInLikers(user) && !pic.getAuthor().hasInFollowers(user))
                return true;
        return false;
    }

    public static List<HashTag> toListOfHashTags(Instagram4j instagram4j, String[] hashTagsArray) {
        ArrayList<HashTag> tagsList = new ArrayList<>();
        for (String hashTag : hashTagsArray)
            tagsList.add(new HashTag(instagram4j, hashTag));
        return tagsList;
    }
}