package com.example.TaskManagementWebApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TaskManagementWebApp.entity.UserInfo;

/**
 * ユーザー情報テーブルDAO
 * 
 * @author ottotto
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String>{
  
}
