package com.shimokawa.task_management.service.user;

import java.time.LocalDateTime;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shimokawa.task_management.entity.UserInfo;
import com.shimokawa.task_management.form.SignupForm;
import com.shimokawa.task_management.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
  /** ユーザー情報テーブルRepositoryクラス */
	private final UserInfoRepository repository;

	/** Dozer Mapper */
	private final Mapper mapper;

	/** Password Encorder */
	private final PasswordEncoder passwordEncoder;

  @Override
  public Optional<UserInfo> registerUserInfo(SignupForm form) {
    var userInfoExistOpt = repository.findById(form.getMailAddress());
    if (userInfoExistOpt.isPresent()) {
      return Optional.empty();
    } 

    var userInfo = mapper.map(form, UserInfo.class);
    var encodedPassword = passwordEncoder.encode(form.getPassword());
    userInfo.setPassword(encodedPassword);
    userInfo.setStatus(userInfo.isStatus());
    userInfo.setCreateTime(LocalDateTime.now());
    userInfo.setUpdateTime(LocalDateTime.now());

    return Optional.of(repository.save(userInfo));
  }
}
