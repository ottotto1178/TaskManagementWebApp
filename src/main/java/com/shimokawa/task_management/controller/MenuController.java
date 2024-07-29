package com.shimokawa.task_management.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shimokawa.task_management.entity.Platform;
import com.shimokawa.task_management.service.platform.PlatformService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {
  /** Platform Service */
  private final PlatformService platformService;

  @GetMapping("/menu")
  public String view() {
    return "menu";
  }

  @GetMapping("/event/form/{type}")
  public String getEventForm(@PathVariable String type, Model model) {
    // 必要に応じてモデルを設定
    List<Platform> platforms = platformService.findAll();
    model.addAttribute("platforms", platforms);
    return "event/" + type;
  }
}
