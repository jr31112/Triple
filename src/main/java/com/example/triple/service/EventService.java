package com.example.triple.service;

import com.example.triple.model.dto.EventDto;
import com.example.triple.model.event.Event;
import com.example.triple.model.event.EventRepository;
import com.example.triple.model.log.Log;
import com.example.triple.model.log.LogRepository;
import com.example.triple.model.user.User;
import com.example.triple.model.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
// 문의 할것
// 1) 포인트 조회 API의 url이나 요청 인자 같은건 편한대로 구현하면 되는지? ㅇㅇ 그렇대여
// 남은거 : testCode 작성 및 그... response들 dto 만들어서 return하기
@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private UserRepository userRepository;
    public EventService(){

    }
    public EventService(EventRepository Event_Repository, LogRepository Log_Repository, UserRepository User_Repository){
        this.eventRepository = Event_Repository;
        this.logRepository = Log_Repository;
        this.userRepository = User_Repository;
    }
    public long getMe(String userId){
        Optional<User> oUser = userRepository.findByUuid(userId);
        if(oUser.isPresent()){
            return oUser.get().getScore();
        }else{
            return 0;
        }
    }
    @Transactional
    public void save(EventDto requestDto){
        User user = userRepository.findByUuid(requestDto.getUserId()).orElseGet(() -> userRepository.save(new User(requestDto.getUserId(), 0)));
        Optional<Event> oEvent = eventRepository.findByUserIdAndReviewId(user.getId(), requestDto.getReviewId());
        if(oEvent.isEmpty()){
            int contentScore = getContentScore(requestDto.getContent().length() , requestDto.getPhotoIds().size());
            int bonusScore = eventRepository.findByPlaceId(requestDto.getPlaceId()).isEmpty() ? 1 : 0;
            Event event = eventRepository.save(new Event(user,requestDto.getReviewId(), requestDto.getPlaceId(), contentScore, bonusScore));
            Log log = logRepository.save(new Log(user,requestDto.getReviewId(), bonusScore + contentScore, requestDto.getAction()));
            user.update(contentScore+bonusScore, event, log);
        }else{
            throw new IllegalArgumentException("이미 작성한 Review가 존재합니다");
        }
    }
    @Transactional
    public void update(EventDto requestDto){
        User user = userRepository.findByUuid(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 리뷰를 남긴 이력이 없습니다"));
        Optional<Event> oEvent = eventRepository.findByUserIdAndReviewId(user.getId(), requestDto.getReviewId());
        if(oEvent.isPresent()){
            Event event = oEvent.get();
            int newContentScore = getContentScore(requestDto.getContent().length(), requestDto.getPhotoIds().size());
            if(event.getContentScore() != newContentScore){
                int newLogScore = newContentScore - event.getContentScore();
                Log newLog = logRepository.save(new Log(user, event.getReviewId(), newLogScore, "MOD"));
                event.setContentScore(newContentScore);
                user.addLog(newLog);
            }
        }else{
            throw new IllegalArgumentException("요청하신 Review를 찾을 수가 없습니다");
        }
    }
    @Transactional
    public void delete(EventDto requestDto){
        User user = userRepository.findByUuid(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 리뷰를 남긴 이력이 없습니다"));
        Optional<Event> oEvent = eventRepository.findByUserIdAndReviewId(user.getId(), requestDto.getReviewId());
        if(oEvent.isPresent()){
            Event event = oEvent.get();
            int totalScore = event.getBonusScore() + event.getContentScore();
            logRepository.save(new Log(user,event.getReviewId(), totalScore, "DEL"));
            eventRepository.delete(event);
        }else{
            throw new IllegalArgumentException("요청하신 Review를 찾을 수가 없습니댜");
        }
    }
    public int getContentScore(int contentLength, int photoLength){
        int score = 0;
        if(contentLength >= 1){
            score += 1;
        }
        if(photoLength >= 1){
            score += 1;
        }
        return score;
    }
}
