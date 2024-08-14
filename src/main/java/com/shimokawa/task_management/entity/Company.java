package com.shimokawa.task_management.entity;

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

  /** ユーザーID */
  @Column(name = "user_id")
  private Integer userId;

  /** プラットフォームID */
  @Column(name = "platform_id")
  private Integer platformId;

  /** ステータスID */
  @Column(name = "status_id")
  private Integer statusId;

  /** デフォルトコンストラクタ */
  public Company() {
  }
}
