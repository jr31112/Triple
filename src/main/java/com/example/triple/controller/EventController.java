package com.example.triple.controller;

import com.example.triple.model.dto.EventDto;
import com.example.triple.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;
    public EventController(){

    }
    public EventController(EventService Event_Service){
        this.eventService = Event_Service;
    }
    @RequestMapping(value="", method=RequestMethod.GET)
    public ResponseEntity<Long> getMe(@RequestParam(value="userId") String userId){
        try{
            return new ResponseEntity<Long>(eventService.getMe(userId), HttpStatus.OK);
        }catch(Exception e){
            throw new IllegalArgumentException(e.toString());
        }
    }
    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Boolean> save(@RequestBody EventDto requestDto){
        try{
            String action = requestDto.getAction();
            if(action.equals("ADD")){
                eventService.save(requestDto);
            }else if(action.equals("MOD")){
                eventService.update(requestDto);
            }else{
                eventService.delete(requestDto);
            }
        }catch(Exception e){
            throw new IllegalArgumentException(e.toString());
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
