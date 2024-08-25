package com.example.userserver.users;

import java.time.ZonedDateTime;

/**
 * PostActivity
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/25/24
 */
public class PostActivity {
    private final int userId;
    private final ZonedDateTime lastUploadDatetime;
    private final String lastUpdatedId;

    public PostActivity(int userId, ZonedDateTime lastUploadDatetime, String lastUpdatedId) {
        this.userId = userId;
        this.lastUploadDatetime = lastUploadDatetime;
        this.lastUpdatedId = lastUpdatedId;
    }

    public int getUserId() {
        return userId;
    }

    public ZonedDateTime getLastUploadDatetime() {
        return lastUploadDatetime;
    }

    public String getLastUpdatedId() {
        return lastUpdatedId;
    }
}
