package com.shimokawa.task_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shimokawa.task_management.form.CompanyForm;
import com.shimokawa.task_management.service.company.CompanyService;
import com.shimokawa.task_management.service.platform.PlatformService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CompanyController {
  /** Platform Service */
  private final PlatformService platformService;

  /** Company Service */
  private final CompanyService companyService;

  @GetMapping("/company")
  public String view(Model model, CompanyForm form) {
    model.addAttribute("platforms", platformService.findAll());
    return "company/index";
  }

  @PostMapping("/registerCompany") 
  public String registerCompany(CompanyForm form, Model model) {
    companyService.registerCompany(form);
    return "redirect:/company";
  }
}
