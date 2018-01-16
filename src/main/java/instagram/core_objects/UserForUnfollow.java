package instagram.core_objects;

import java.time.LocalDateTime;

public class UserForUnfollow {
    private User user;
    private LocalDateTime followedTime;

    public UserForUnfollow(User user, LocalDateTime followedTime) {
        this.user = user;
        this.followedTime = followedTime;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getFollowedTime() {
        return followedTime;
    }
}