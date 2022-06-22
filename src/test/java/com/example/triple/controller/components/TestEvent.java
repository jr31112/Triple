package com.example.triple.controller.components;

import com.example.triple.model.event.Event;
import com.example.triple.model.event.EventRepository;
import com.example.triple.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestEvent{
    @Autowired
    private EventRepository eventRepository;
    public Event saveEvent(User user, String ReviewId, String PlaceId){
        return eventRepository.save(new Event(user,ReviewId, PlaceId, 0, 1));
    }
}