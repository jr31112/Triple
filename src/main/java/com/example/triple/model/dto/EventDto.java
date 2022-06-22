package com.example.triple.model.dto;

import java.util.List;

public class EventDto {
    private String type, action,reviewId, content,userId,placeId;
    private List<String> photoIds;
    public EventDto(){

    }
    public EventDto(String Type, String Action, String ReviewId, String Content, String UserId, String PlaceId, List<String> PhotoIds){
        this.type = Type;
        this.action = Action;
        this.reviewId = ReviewId;
        this.content = Content;
        this.userId = UserId;
        this.placeId = PlaceId;
        this.photoIds = PhotoIds;
    }

    public String getType(){
        return this.type;
    }
    public String getAction(){
        return this.action;
    }
    public String getReviewId(){
        return this.reviewId;
    }
    public String getContent(){
        return this.content;
    }
    public String getUserId(){
        return this.userId;
    }
    public String getPlaceId(){
        return this.placeId;
    }
    public List<String> getPhotoIds(){
        return this.photoIds;
    }
}
