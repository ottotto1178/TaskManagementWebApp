package com.example.TaskManagementWebApp.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.TaskManagementWebApp.entity.UserInfo;
import com.example.TaskManagementWebApp.Repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
  private final UserInfoRepository repository;
  public Optional<UserInfo> searchUserById(String loginId){
    return repository.findById(loginId);
  }
}
