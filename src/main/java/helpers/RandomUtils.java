package helpers;

import instagram.core_objects.HashTag;
import instagram.core_objects.Media;

import java.util.List;

public class RandomUtils {
    public static RandomWeightedCollection<HashTag> mostPosts(List<HashTag> hashTagList) {
        RandomWeightedCollection<HashTag> weightedCollection = new RandomWeightedCollection<>();
        for (HashTag tag : hashTagList)
            weightedCollection.addWeighted(tag.getMediaCount(), tag);
        return weightedCollection;
    }

    public static RandomWeightedCollection<Media> mostLikes(List<Media> mediaList) {
        RandomWeightedCollection<Media> weightedCollection = new RandomWeightedCollection<>();
        for (Media media : mediaList)
            weightedCollection.addWeighted(media.getNumberOfLikes(), media);
        return weightedCollection;
    }
}