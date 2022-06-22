package com.example.triple.model.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByPlaceId(String reviewId);
    Optional<Event> findByUserIdAndReviewId(long userId, String reviewId);
    Optional<Event> findByPlaceIdAndUserId(String placeId, String userId);
    List<Event> findAllByUserIdAndReviewId(String userId, String reviewId);
    List<Event> findAllByUserId(String userId);
}
