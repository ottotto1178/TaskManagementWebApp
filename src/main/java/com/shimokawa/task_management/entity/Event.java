package com.shimokawa.task_management.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "event")
@Data
@AllArgsConstructor
public class Event {
  /** イベントID */
  @Id
  @Column(name = "id")
  private int id;

  /** ユーザーID */
  @Column(name = "user_id")
  private int userId;

  /** 企業ID */
  @Column(name = "company_id")
  private int companyId;

  /** イベント日程 */
  @Column(name = "date")
  private Date date;
  
  /** イベント名 */
  @Column(name = "event")
  private String event;

  /** 備考 */
  @Column(name = "note")
  private String note;

  /** 企業 */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id", insertable = false, updatable = false)
  private Company company;

  /** デフォルトコンストラクタ */
  public Event() {
  }
}
