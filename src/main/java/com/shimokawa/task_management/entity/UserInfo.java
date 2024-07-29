package com.shimokawa.task_management.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
public class UserInfo {
  /** ユーザーID */
  @Column(name = "id")
  private int userId;

  /** 氏名 */
  private String name;

  /** メールアドレス */
  @Id
  @Column(name = "mail_address")
  private String mailAddress;

  /** パスワード */
  private String password;

  /** ログイン失敗回数 */
  @Column(name = "login_failur_count")
  private int loginFailurCount = 0;
  
  /** アカウントロック日時 */
  @Column(name = "account_locked_time")
  private  LocalDateTime accountLockedTime;
  
  /** 利用可不可 */
  @Column(name = "is_disabled")
  private boolean status;

  /** 登録日時 */
  @Column(name = "create_time")
  private LocalDateTime createTime;

  /** 最終更新日時 */
  @Column(name = "update_time")
  private LocalDateTime updateTime;
  
  /** デフォルトコンストラクタ */
  public UserInfo(){
  }
  
  /**
   * ログイン失敗回数を加算する
   * 
   * @return ログイン失敗回数が加算されたUserInfo
   */
  public UserInfo incrementFailurCount(){
    return new UserInfo(userId, name, mailAddress, password, ++loginFailurCount, accountLockedTime, status, createTime, updateTime);
  }
  
  /**
   * ログイン失敗回数をリセットする
   * 
   * @return ログイン失敗回数がリセットされたUserInfo
   */
  public UserInfo resetLoginFailurInfo(){
    return new UserInfo(userId, name, mailAddress, password, 0, null, status, createTime, updateTime);
  }

  /**
	 * アカウントロック状態に更新する
	 * 
	 * @return ログイン失敗回数、アカウントロック日時が更新されたUserInfo
	 */
  public UserInfo updateAccountLocked(){
    return new UserInfo(userId, name, mailAddress, password, 0, LocalDateTime.now(), status, createTime, updateTime);
  }
}
