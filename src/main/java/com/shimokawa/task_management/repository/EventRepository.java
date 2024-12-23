package com.shimokawa.task_management.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shimokawa.task_management.entity.Event;

/**
 * イベント情報リポジトリ
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
  // 現在の日付から直近5件のイベントを取得
  @Query("SELECT e FROM Event e WHERE e.userId = :userId AND e.date >= CURRENT_DATE ORDER BY e.date ASC")
  List<Event> findTop5ByUserIdAndDateAfterOrderByDateAsc(@Param("userId") Integer userId);

  // 日程でイベントを検索
  List<Event> findByUserIdAndDate(Integer userId, Date date);
}
