package com.shimokawa.task_management.aspect;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shimokawa.task_management.service.user.CustomUserDetails;


@ControllerAdvice
public class GrobalUserameControllerAdvice {
  @ModelAttribute
  public void addAttributes(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
    if (userDetails != null) {
      // ユーザー名を取得
      model.addAttribute("username", userDetails.getUsername());
      // ユーザーIDを取得
      model.addAttribute("userId", userDetails.getUserId());
      
    }
  }
}
