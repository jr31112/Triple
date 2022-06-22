package com.example.triple.model.log;

import com.example.triple.model.user.User;

import javax.persistence.*;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;
    @Column(nullable=false)
    private String reviewId;
    @Column(nullable=false)
    private int score;
    @Column(nullable=false)
    private String action;

    public Log(){

    }
    public Log(User User, String Review_Id, int Score, String Action){
        this.user = User;
        this.reviewId = Review_Id;
        this.score = Score;
        this.action = Action;
    }
    public long getId(){
        return this.id;
    }
    public User getUser(){
        return this.user;
    }
    public String getReviewId(){
        return this.reviewId;
    }
    public int getScore(){
        return this.score;
    }
    public String getAction(){
        return this.action;
    }

}
