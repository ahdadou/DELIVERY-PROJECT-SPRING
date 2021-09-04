package com.gaming.worspace.models.dto.response;

public class MessagesResponse {
    private long  Id;
    private UserResponse user_sender;
    private String msg;
    private String createAt;

    public MessagesResponse() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public UserResponse getUser_sender() {
        return user_sender;
    }

    public void setUser_sender(UserResponse user_sender) {
        this.user_sender = user_sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
