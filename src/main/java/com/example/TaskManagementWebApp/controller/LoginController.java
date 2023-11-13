/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TaskManagementWebApp.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.TaskManagementWebApp.Service.LoginService;
import com.example.TaskManagementWebApp.constant.ErrorMessageConst;

import org.springframework.ui.Model;

import com.example.TaskManagementWebApp.form.LoginForm;
import com.example.TaskManagementWebApp.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 *ログインコントローラー
 * @author ottotto
 */
@Controller
@RequiredArgsConstructor
public class LoginController {
  private final LoginService service;

  /** Password Encorder */
  private final PasswordEncoder passwordEncoder;
  /** MessageSource */
  private final MessageSource messageSource;
  /**
   * 初期表示
   * 
   * @param model モデル
   * @param form 入力情報
   * @return 表示画面
   */
  @GetMapping("/login")
  public String view(Model model, LoginForm form){
    return "login";
  }
  /**
   * ログイン
   * 
   * @param model モデル
   * @param form 入力情報
   * @return 表示画面
   */
  @PostMapping("/login")
  public String login(Model model,LoginForm form){
    var userInfo = service.searchUserById(form.getLoginId());
    boolean isCorrectUserAuth = userInfo.isPresent() && passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
    if(isCorrectUserAuth){
      return "redirect:/main";
    }else{
      //メッセージを取得
      String errorMsg = AppUtil.getMassage(messageSource, ErrorMessageConst.LOGIN_WRONG_INPUT);
      model.addAttribute("errorMsg", errorMsg);
      return "login";
    }
  }
}
