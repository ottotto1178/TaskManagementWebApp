package com.shimokawa.task_management.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shimokawa.task_management.form.UserEditForm;
import com.shimokawa.task_management.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
  /** UserServie */
  private final UserService service;

  @GetMapping("/user/{userId}")
  public String userInfo(@PathVariable("userId") Integer userId, Model model) {
    return service.viewDisplay(userId, model, "user");
  }

  @GetMapping("/user/{userId}/edit")
  public String userInfoEdit(@PathVariable("userId") Integer userId, Model model) {
    return service.viewDisplay(userId, model, "edit");
  }

  @PostMapping("/user/{userId}/edit")
  public String updateUser(@PathVariable("userId") Integer userId, UserEditForm form, Model model) {
    service.updateUserInfo(userId, form);
    return "redirect:/user/" + userId;
  }
}
