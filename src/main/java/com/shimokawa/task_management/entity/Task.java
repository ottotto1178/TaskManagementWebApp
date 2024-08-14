package com.shimokawa.task_management.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
public class Task {
  /** タスクID */
  @Id
  @Column(name = "id")
  private int taskId;

  /** 企業ID */
  @Column(name = "company_id")
  private int companyId;

  /** タスク */
  @Column(name = "task")
  private String task;

  /** 備考 */
  @Column(name = "notes")
  private String notes;

  /** 次回日程 */
  @Column(name = "next_schedule_date")
  private Date nextScheduleDate;

  /** 優先度 */
  @Column(name = "priority")
  private int priority;

  /** デフォルトコンストラクタ */
  public Task() {
  }
}
