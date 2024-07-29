package com.shimokawa.task_management.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignupMessage {
  SUCCEED("signup.registerSucceed", false),
  EXISTED_MAIL_ADDRESS("signup.existedMailAddress", true);
  
  private String message;
  private boolean isError;
}
