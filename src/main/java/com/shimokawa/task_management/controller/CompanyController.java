package com.shimokawa.task_management.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shimokawa.task_management.constant.StatusConst;
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

  /** Message Source */
  private final MessageSource messageSource;

  /** Status */
  private final StatusConst statusConst;

  @GetMapping("/company")
  public String view(Model model, CompanyForm form) {
    setModel(model);
    return "company/index";
  }

  @GetMapping("/company/{companyId}")
  public String companyInfo(@PathVariable("companyId") Integer companyId, Model model) {
    setModel(model);
    return companyService.viewDisplay(companyId, model, "detail");
  }

  @PostMapping("/registerCompany") 
  public String registerCompany(@Validated CompanyForm form, BindingResult bdResult, Model model) {
    if (bdResult.hasErrors()) { // バリデーションチェック
      setModel(model);
      return "company/index";
    } else {
      if (companyService.isExistCompany(form)) {
        setModel(model);
        String errorMessage = messageSource.getMessage("company.isExist", null, null);
        model.addAttribute("isExist", errorMessage);
        return "company/index";
      }
    }
    companyService.registerCompany(form);
    return "redirect:/company";
  }

  @PostMapping("/company/update/{companyId}")
  public String updateCompany(@PathVariable("companyId") Integer companyId, @Validated CompanyForm form, Model model) {
    companyService.updateCompany(companyId, form);
    return "redirect:/company/" + companyId;
  }

  private void setModel(Model model) {
    Integer userId = (Integer) model.getAttribute("userId");
    model.addAttribute("platforms", platformService.findAll());
    model.addAttribute("companies", companyService.findByUserId(userId));
    model.addAttribute("statusList", statusConst.getStatusList());
  }
}
