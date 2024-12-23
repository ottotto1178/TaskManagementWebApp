package com.shimokawa.task_management.service.event;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.shimokawa.task_management.entity.Event;
import com.shimokawa.task_management.form.EventForm;

public interface EventService {
  /**
   * イベント検索(直近5件)
   * 
   * @param userId ユーザID
   * @return 検索結果
   */
  public List<Event> findByUserId(Integer userId);

  /**
   * イベント検索(日程)
   * 
   * @param userId ユーザID
   * @param date 日程
   * @return 検索結果
   */
  public List<Event> findByDate(Integer userId, Date date);

  /**
   * イベント登録
   * 
   * @param form イベント情報
   */
  public Optional<Event> registerEvent(EventForm form);
}
