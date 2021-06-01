package com.emcove.rest.api.Core.dto;

public class UserSessionDTO {
    private Integer userId;
    private Integer entrepreneurshipId;
    private String userAvatar;

    public UserSessionDTO(Integer userId, Integer entrepreneurshipId, String userAvatar) {
        this.userId = userId;
        this.entrepreneurshipId = entrepreneurshipId;
        this.userAvatar = userAvatar;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEntrepreneurshipId() {
        return entrepreneurshipId;
    }

    public void setEntrepreneurshipId(Integer entrepreneurshipId) {
        this.entrepreneurshipId = entrepreneurshipId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
