package com.example.triple.model.event;

import com.example.triple.model.user.User;

import javax.persistence.*;

@EntityListeners(EventListener.class)
@Entity
@Table(indexes={@Index(name="EventIndex", columnList="user_id, reviewId")})
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private String reviewId;
    @ManyToOne
    private User user;
    @Column(nullable=false)
    private String placeId;
    @Column(nullable=false)
    private int contentScore;
    @Column(nullable=false)
    private int bonusScore;
    public Event(){

    }
    public Event(User User , String Review_Id, String Place_Id, int Content_Score, int BonusScore){
        this.user = User;
        this.reviewId = Review_Id;
        this.placeId = Place_Id;
        this.contentScore = Content_Score;
        this.bonusScore = BonusScore;
    }
    public long getId(){
        return this.id;
    }
    public String getReviewId(){
        return this.reviewId;
    }
    public User getUser(){
        return this.user;
    }
    public String getPlaceId(){
        return this.placeId;
    }
    public int getContentScore(){
        return this.contentScore;
    }
    public int getBonusScore(){
        return this.bonusScore;
    }
    public void setContentScore(int newScore){
        this.contentScore = newScore;
    }



}
