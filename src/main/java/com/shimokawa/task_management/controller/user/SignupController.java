package com.shimokawa.task_management.controller.user;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shimokawa.task_management.constant.SignupMessage;
import com.shimokawa.task_management.entity.UserInfo;
import com.shimokawa.task_management.form.SignupForm;
import com.shimokawa.task_management.service.user.SignupServiceImpl;
import com.shimokawa.task_management.util.Apputil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {
  /** ユーザー登録画面Service */
  private final SignupServiceImpl service;

  /** MessageSource */
  private final MessageSource messageSource;

  @GetMapping("/signup")
  public String view(Model model, SignupForm form) {
    return "user/signup";
  }

  @PostMapping("/signup")
  public void signup(Model model, @Validated SignupForm form, BindingResult bdResult){
    if(bdResult.hasErrors()){
      editGuideMessage(model, "common.formError", true);
      return;
    }
    var userInfoOpt = service.registerUserInfo(form);
    var signupMessage = judgeMessageKey(userInfoOpt);
    editGuideMessage(model, signupMessage.getMessage(), signupMessage.isError());
  }

  private void editGuideMessage(Model model, String messageId, boolean isError){
    var message = Apputil.getMessage(messageSource, messageId);
    model.addAttribute("message", message);
    model.addAttribute("isError", isError);
  }

  private SignupMessage judgeMessageKey(Optional<UserInfo> userinfoOpt){
    if(userinfoOpt.isEmpty()){
      return SignupMessage.EXISTED_MAIL_ADDRESS;
    }else{
      return SignupMessage.SUCCEED;
    }
  }
}
