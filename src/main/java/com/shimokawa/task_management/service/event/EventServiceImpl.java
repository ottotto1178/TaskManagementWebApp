package com.shimokawa.task_management.service.event;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.shimokawa.task_management.entity.Event;
import com.shimokawa.task_management.form.EventForm;
import com.shimokawa.task_management.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
  /** Event Repository */
  private final EventRepository repository;

  /** Dozer Mapper */
  private final Mapper mapper;

  /**
   * @inhrtitDoc
   */
  @Override
  public List<Event> findByUserId(Integer userId) {
    return repository.findTop5ByUserIdAndDateAfterOrderByDateAsc(userId);
  }

  /**
   * @inhrtitDoc
   */
  @Override
  public List<Event> findByDate(Integer userId, Date date) {
    return repository.findByUserIdAndDate(userId, date);
  }

  /**
   * @inhrtitDoc
   */
  @Override
  public Optional<Event> registerEvent(EventForm form) {
    var event = mapper.map(form, Event.class);
    return Optional.of(repository.save(event));
  }
}
