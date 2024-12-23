package com.shimokawa.task_management.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shimokawa.task_management.entity.Company;
import com.shimokawa.task_management.entity.Platform;
import com.shimokawa.task_management.form.EventForm;
import com.shimokawa.task_management.service.company.CompanyService;
import com.shimokawa.task_management.service.event.EventService;
import com.shimokawa.task_management.service.platform.PlatformService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EventController {
  
  /** Platform Service */
  private final PlatformService platformService;

  /** Company Service */
  private final CompanyService companyService;

  /** Event Service */
  private final EventService eventService;

  @GetMapping("/event/index/{date}")
  public String getEventForm(@PathVariable("date") String date, Model model, EventForm form) {
    int userId = (int) model.getAttribute("userId");
    // 必要に応じてモデルを設定
    List<Platform> platforms = platformService.findAll();
    model.addAttribute("platforms", platforms);
    List<Company> companies = companyService.findByUserId(userId);
    model.addAttribute("companies", companies);
    
    // String型の日付をDate型に変換
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date parsedDate = dateFormat.parse(date);
      form.setDate(parsedDate);
      model.addAttribute("date", parsedDate);
    } catch (ParseException e) {
      e.printStackTrace();
      // エラーハンドリング（例: エラーメッセージを表示する、リダイレクトするなど）
      return "error";
    }
    model.addAttribute("eventForm", form);

    return "event/index";
  }

  @PostMapping("/event/register")
  public String registerEvent(@Validated EventForm form, BindingResult bdResult, Model model) {
    if (bdResult.hasErrors()) {
      System.out.println("error");
      return "menu";
    }
    System.out.println("register");
    eventService.registerEvent(form);
    return "redirect:/menu";
  }
}
