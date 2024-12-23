package com.shimokawa.task_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shimokawa.task_management.service.event.EventService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {

  /** Event Service */
  private final EventService eventService;

  @GetMapping("/menu")
  public String view(Model model) {
    int userId = (int) model.getAttribute("userId");
    model.addAttribute("events", eventService.findByUserId(userId));
    return "menu";
  }
}
