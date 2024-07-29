package com.shimokawa.task_management.service.user;


import java.time.LocalDateTime;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.shimokawa.task_management.entity.UserInfo;
import com.shimokawa.task_management.form.UserEditForm;
import com.shimokawa.task_management.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  /** ユーザー状態テーブルDAO */
  private final UserInfoRepository repository;

  /** Password Encorder */
	private final PasswordEncoder passwordEncoder;

  /**
   * @inheritDoc
   */
  @Override
  public UserInfo getUserInfo(Integer requestedUserId, Integer loginUserId) {
    if (!requestedUserId.equals(loginUserId)) {
      throw new AccessDeniedException("ユーザーIDが一致しません。");
    }

    return repository.findByUserId(loginUserId)
      .orElseThrow(() -> new UsernameNotFoundException("指定されたユーザーが見つかりません。"));
  }

  /**
   * @inheritDoc
   */
  @Override
  public String viewDisplay(Integer userId, Model model, String viewName) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
    Integer loginUserId = userDetails.getUserId();
    String viewPath = "user/" + viewName;

    try {
      UserInfo user = this.getUserInfo(userId, loginUserId);
      model.addAttribute("user", user);
    } catch (AccessDeniedException e) {
      return "redirect:/user/" + loginUserId;
    }

    return viewPath;
  }

  /**
   * @inheritDoc
   */
  @Transactional
  @Override
  public void updateUserInfo(Integer userId, UserEditForm form) {
    UserInfo userInfo = repository.findByUserId(userId)
                                  .orElseThrow(() -> new UsernameNotFoundException("指定されたユーザーが見つかりません。"));
    userInfo.setName(form.getName());
    userInfo.setMailAddress(form.getMailAddress());
    // フォームのパスワードが空欄ではない場合のみパスワードを更新
    if (form.getPassword() != null && !form.getPassword().isEmpty()) {
      var encodedPassword = passwordEncoder.encode(form.getPassword());
      userInfo.setPassword(encodedPassword);
    }
    userInfo.setUpdateTime(LocalDateTime.now());

    repository.save(userInfo);
  }
}
