package com.example.homework.model;

public class SecondComment {

    private Integer id;

    private Integer parent_id;
    private Long create_time;
    private String content;
    private String comment_username;
    private String parentCommentUUID;

    public String getParentCommentUUID() {
        return parentCommentUUID;
    }

    public void setParentCommentUUID(String parentCommentUUID) {
        this.parentCommentUUID = parentCommentUUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment_username() {
        return comment_username;
    }

    public void setComment_username(String comment_username) {
        this.comment_username = comment_username;
    }

}
