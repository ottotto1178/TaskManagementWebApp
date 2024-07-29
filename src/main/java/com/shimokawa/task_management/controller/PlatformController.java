package com.shimokawa.task_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shimokawa.task_management.form.PlatformForm;
import com.shimokawa.task_management.service.platform.PlatformService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PlatformController {
  /** Platform Service */
  private final PlatformService service;

  @GetMapping("/platform")
  public String pratform(Model model, PlatformForm form) {
    model.addAttribute("platforms", service.findAll());
    return "platform/index";
  }

  @PostMapping("/platform")
  public String create(PlatformForm form, Model model) {
    service.registerPlatform(form);
    return "redirect:/platform";
  }
}
