package com.shimokawa.task_management.service.user;

import org.springframework.ui.Model;

import com.shimokawa.task_management.entity.UserInfo;
import com.shimokawa.task_management.form.UserEditForm;

public interface UserService {
  /**
   * ユーザー情報検索
   * 
   * @param userId ユーザーID
   * @return 検索結果
   */
  public UserInfo getUserInfo(Integer requestedUserId, Integer loginUserId);

  /**
   * 画面表示前処理
   * 
   * @param userId ユーザーID
   * @param model Model
   * @param viewName 表示画面名
   * @return 表示画面名
   */
  public String viewDisplay(Integer userId, Model model, String viewName);

  /**
   * ユーザー情報更新
   * 
   * @param userId ユーザーID
   * @param form ユーザー情報
   */
  public void updateUserInfo(Integer userId, UserEditForm form);
}
