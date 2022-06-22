package com.example.triple.controller;


import com.example.triple.controller.components.TestEvent;
import com.example.triple.controller.components.TestUser;
import com.example.triple.model.dto.EventDto;
import com.example.triple.model.event.Event;
import com.example.triple.model.event.EventRepository;
import com.example.triple.model.user.User;
import com.example.triple.model.user.UserRepository;
import com.example.triple.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class EventControllerTest extends BaseIntegrationTest{
//    @MockBean
//    private EventService eventService;
//    @MockBean
//    private EventRepository eventRepository;
//    @MockBean
//    private UserRepository userRepository;
    @Autowired
    private TestUser testUser;
    @Autowired
    private TestEvent testEvent;
    @Test
    @DisplayName("ADD EVENT TEST 1")
    public void addTest() throws Exception{
        //given
        String Action = "ADD";
        EventDto eventDto = getDto(Action);
        //when
        String body = mapper.writeValueAsString(eventDto);
        //then
        ResultActions resultActions = mvc.perform(post("/events")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("DELETE EVENT TEST 1")
    public void deleteTest() throws Exception{
        //given
        String Action = "DELETE";
        EventDto eventDto = getDto(Action);
        User user = testUser.saveUser(eventDto.getUserId());
        Event event = testEvent.saveEvent(user,eventDto.getReviewId(), eventDto.getPlaceId());
        //when
        String body = mapper.writeValueAsString(eventDto);
        //then
        ResultActions resultActions = mvc.perform(post("/events")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("UPDATE EVENT TEST")
    public void updateTest()throws Exception{
        //given
        String Action = "MOD";
        EventDto eventDto = getDto(Action);
        User user = testUser.saveUser(eventDto.getUserId());
        Event event = testEvent.saveEvent(user,eventDto.getReviewId(), eventDto.getPlaceId());
        // when
        String body = mapper.writeValueAsString(eventDto);
        // then
        ResultActions resultActions = mvc.perform(post("/events")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }
    public EventDto getDto(String Action){
        List<String> photoIds = new ArrayList<String>();
        String Review_Id = UUID.randomUUID().toString();
        String Type = "REVIEW";
        for(int i = 0; i < 2; i++){
            photoIds.add(UUID.randomUUID().toString());
        }
        String newContent = "좋아요";
        String User_Id = UUID.randomUUID().toString();
        String Place_Id = UUID.randomUUID().toString();
        return new EventDto(Type,Action,Review_Id, newContent,User_Id,Place_Id, photoIds);
    }


}
