package com.shimokawa.task_management.service.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shimokawa.task_management.entity.UserInfo;
import com.shimokawa.task_management.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
  /** ユーザー情報テーブルDAO */
  private final UserInfoRepository repository;

  /**
   * ユーザー情報テーブルキー検索
   * @param mailAddress メールアドレス
   * @return 検索結果
   */
  public Optional<UserInfo> searchUserBymailAddress(String mailAddress){
    return repository.findById(mailAddress);
  }
}
