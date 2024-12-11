package com.shimokawa.task_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "platform")
@Data
@AllArgsConstructor
public class Platform {
  /** プラットフォームID */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int platformId;

  /** プラットフォーム名 */
  @Column(name = "name")
  private String name;

  /** デフォルトコンストラクタ */
  public Platform(){
  }
}
