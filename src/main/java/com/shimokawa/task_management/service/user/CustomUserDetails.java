package com.shimokawa.task_management.service.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shimokawa.task_management.entity.UserInfo;

public class CustomUserDetails implements UserDetails {
  /** User Info */
  private UserInfo userInfo;

  public CustomUserDetails(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  // アカウントがロックされているかどうかを設定するメソッド
  public CustomUserDetails withAccountLocked(boolean isLocked) {
    if (isLocked) {
      userInfo.setAccountLockedTime(LocalDateTime.now());
    } else {
      userInfo.setAccountLockedTime(null);
    }
    return this;
  }

  // アカウントが無効かどうかを設定するメソッド
  public CustomUserDetails withDisabled(boolean isDisabled) {
    userInfo.setStatus(!isDisabled); // isDisabledがtrueの場合、statusをfalseに設定
    return this;
  }

  public Integer getUserId() {
    return userInfo.getUserId();
  }

  public String getMailAddress() {
    return userInfo.getMailAddress();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // この例では、ユーザーに関連付けられた権限がないと仮定しています。
    // 実際のアプリケーションでは、ユーザーの権限に基づいてGrantedAuthorityのリストを返す必要があります。
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return userInfo.getPassword();
  }

  @Override
  public String getUsername() {
    return userInfo.getName();
  }

  @Override
  public boolean isAccountNonLocked() {
    // アカウントがロックされているかどうかを判断します。
    // 例えば、accountLockedTimeがnullでない場合はアカウントがロックされていると判断できます。
    return userInfo.getAccountLockedTime() == null;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // 資格情報（パスワード）の有効期限に関するロジックを実装します。
    // この例では、資格情報が有効期限切れにならないと仮定しています。
    return true;
  }

  @Override
  public boolean isEnabled() {
    // ユーザーのアカウントが有効かどうかを判断します。
    return !userInfo.isStatus();
  }

}
