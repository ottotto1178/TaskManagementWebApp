package com.example.TaskManagementWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニューコントローラー
 * @author ottotto
 * 
 */
@Controller
public class MenuController {
  @GetMapping("/main")
  public String view(){
    return "main";
  }
}
