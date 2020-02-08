package com.example.wowtimedemo.Model;

public class ItemsModel {
    String title;
    String channelName;
    String dateTime;
    String thumbnailUrl;
    String videoId;
    String description;

    public ItemsModel(String title, String channelName, String dateTime, String thumbnailUrl, String videoId, String description) {
        this.title = title;
        this.channelName = channelName;
        this.dateTime = dateTime;
        this.thumbnailUrl = thumbnailUrl;
        this.videoId = videoId;
        this.description=description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
