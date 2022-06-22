package com.example.triple.model.user;

import com.example.triple.model.event.Event;
import com.example.triple.model.log.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes={@Index(name="UserIndex", columnList="uuid")})
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private String uuid;
    @OneToMany(mappedBy="user")
    List<Event> events;
    @OneToMany(mappedBy="user")
    List<Log> logs;
    @Column(nullable=false)
    private long score;
    public User(){

    }
    public User(String Uuid,long Score){
        this.uuid = Uuid;
        this.score = Score;
        this.events = new ArrayList<Event>();
        this.logs = new ArrayList<Log>();
    }
    public long getId(){
        return this.id;
    };
    public long getScore(){
        return this.score;
    }
    public void addScore(long newScore){
        this.score += newScore;
    }
    public void addLog(Log newLog){
        this.logs.add(newLog);
        this.score += newLog.getScore();
    }
    public void update(long newScore, Event event, Log log){
        this.score += newScore;
        this.events.add(event);
        this.logs.add(log);
    }
}
