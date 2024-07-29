package com.shimokawa.task_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimokawa.task_management.entity.UserInfo;

/**
 * ユーザー情報リポジトリ
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
  public Optional<UserInfo> findByUserId(int userId);
}
