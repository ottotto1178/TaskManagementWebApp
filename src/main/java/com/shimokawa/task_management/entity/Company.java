package com.shimokawa.task_management.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "company")
@Data
@AllArgsConstructor
public class Company {
  /** 企業ID */
  @Id
  @Column(name = "id")
  private int companyId;

  /** 企業名 */
  @Column(name = "name")
  private String name;

  /** プラットフォームID */
  @Column(name = "platform_id")
  private int platformId;

  /** ステータスID */
  @Column(name = "status_id")
  private int statusId;

  /** 次回日程 */
  @Column(name = "next_schedule_date")
  private Date nextScheduleDate;

  /** 優先度 */
  @Column(name = "priority")
  private int priority;

  /** デフォルトコンストラクタ */
  public Company() {
  }
}
