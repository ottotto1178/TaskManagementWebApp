package com.shimokawa.task_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CompanyController {
  @GetMapping("/company")
  public String view() {
    return "company/index";
  }
}
