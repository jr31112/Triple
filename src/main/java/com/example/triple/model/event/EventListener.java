package com.example.triple.model.event;

import javax.persistence.PreRemove;
//이거 물어봐야겠다 persist되는 부분

public class EventListener {
    @PreRemove
    public void preRemove(Event event){
        event.getUser().addScore(-1 * (event.getContentScore()+event.getBonusScore()));
    }
}
