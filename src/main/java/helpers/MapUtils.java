package helpers;

import instagram.core_objects.SingleHashTag;

import java.util.HashMap;
import java.util.List;

public class MapUtils {
    public static HashMap<String, Integer> toMapTagMediaCount(List<SingleHashTag> hashTagList) {
        HashMap<String, Integer> hashTagMap = new HashMap<>();
        for (SingleHashTag similarHashTag : hashTagList)
            hashTagMap.put(similarHashTag.getHashTag(), similarHashTag.getMediaCount());
        return hashTagMap;
    }


}
