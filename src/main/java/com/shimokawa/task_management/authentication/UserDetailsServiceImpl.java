package com.shimokawa.task_management.authentication;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.shimokawa.task_management.repository.UserInfoRepository;
import com.shimokawa.task_management.service.user.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  /** ユーザー情報テーブルRepository */
  private final UserInfoRepository repository;

  /** ログイン失敗回数制限 */
  @Value("${security.locking-border-count}")
  private int lockingBorderCount;

  /** ロック時間 */
  @Value("${security.locking-time}")
  private int lockingTime;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var userInfo = repository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
    
    var accountLockedTime = userInfo.getAccountLockedTime();
    var isAccountLocked = accountLockedTime != null && accountLockedTime.plusHours(lockingTime).isAfter(LocalDateTime.now());
    
    CustomUserDetails userDetails = new CustomUserDetails(userInfo);
    userDetails.withAccountLocked(isAccountLocked);
    userDetails.withDisabled(!userInfo.isStatus());

    return userDetails;
  }

  @EventListener
  public void handle(AuthenticationFailureBadCredentialsEvent event){
    var mailAddress = event.getAuthentication().getName();
    repository.findById(mailAddress).ifPresent(userInfo -> {
      repository.save(userInfo.incrementFailurCount());
      
      var isReachFailurCount = userInfo.getLoginFailurCount() == lockingBorderCount;
      if(isReachFailurCount){
        repository.save(userInfo.updateAccountLocked());
      }
    });
  }

  @EventListener
  public void handle(AuthenticationSuccessEvent event){
    CustomUserDetails userDetails = (CustomUserDetails) event.getAuthentication().getPrincipal();
    var mailAddress = userDetails.getMailAddress();
    repository.findById(mailAddress).ifPresent(userInfo -> {
      repository.save(userInfo.resetLoginFailurInfo());
    });
  }
}
