package com.shimokawa.task_management.service.user;

import java.util.Optional;

import com.shimokawa.task_management.entity.UserInfo;
import com.shimokawa.task_management.form.SignupForm;

public interface SignupService {
  public Optional<UserInfo> registerUserInfo(SignupForm form);
}
