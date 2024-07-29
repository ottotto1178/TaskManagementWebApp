package com.shimokawa.task_management.controller.user;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shimokawa.task_management.form.LoginForm;
import com.shimokawa.task_management.service.user.LoginService;
import com.shimokawa.task_management.util.Apputil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
  /** ログイン画面Service */
  private final LoginService service;

  /** PasswordEncorder */
  private final PasswordEncoder passwordEncoder;

  /** MessageSource */
  private final MessageSource messageSource;

  /** セッション情報 */
  private final HttpSession session;

  @GetMapping("/login")
  public String view(Model model, LoginForm form) {
    return "user/login";
  }

  @GetMapping(value = "/login", params = "error")
  public String viewWithError(Model model, LoginForm form) {
    var errorInfo = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    model.addAttribute("errorMsg", errorInfo.getMessage());
    return "user/login";
  }

  @PostMapping("/login")
  public String login(Model model, LoginForm form){
    var userInfo = service.searchUserBymailAddress(form.getMailAddress());
    var isCorrectUserAuth = userInfo.isPresent() && passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
    if (isCorrectUserAuth){
      return "redirect:/menu";
    }else{
      var errorMsg = Apputil.getMessage(messageSource, "common.formError");
      model.addAttribute("errorMsg", errorMsg);
      return "user/login";
    }
  }
}
