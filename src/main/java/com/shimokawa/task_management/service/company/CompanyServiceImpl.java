package com.shimokawa.task_management.service.company;

import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.shimokawa.task_management.constant.Status;
import com.shimokawa.task_management.constant.StatusConst;
import com.shimokawa.task_management.entity.Company;
import com.shimokawa.task_management.entity.Platform;
import com.shimokawa.task_management.form.CompanyForm;
import com.shimokawa.task_management.repository.CompanyRepository;
import com.shimokawa.task_management.repository.PlatformRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
  /** Company Repository */
  private final CompanyRepository repository;

  /** Platform Repository */
  private final PlatformRepository platformRepository;

  /** Dozer Mapper */
	private final Mapper mapper;

  /** Status Const */
  private final StatusConst statusConst;

  /**
   * @inhrtitDoc
   */
  @Override
  public List<Company> findByUserId(Integer userId) {
    return repository.findByUserId(userId);
  }

  /**
   * @inhrtitDoc
   */
  @Override
  public String viewDisplay(Integer companyId, Model model, String viewName) {
    try {
      Optional<Company> companyOpt = repository.findById(companyId);
      if (companyOpt.isPresent()) {
        Company company = companyOpt.get();
        Optional<Platform> platformOpt = platformRepository.findById(company.getPlatformId());
        if (platformOpt.isPresent()) {
          model.addAttribute("registerPlatform", platformOpt.get());
        } else {
          model.addAttribute("registerPlatform", null);
        }
        model.addAttribute("company", company);
        CompanyForm companyForm = new CompanyForm();
        companyForm.setName(company.getName());
        companyForm.setUserId(company.getUserId());
        companyForm.setPlatformId(company.getPlatformId());
        companyForm.setStatusId(company.getStatusId());
        model.addAttribute("companyForm", companyForm);
        model.addAttribute("statusList", statusConst.getStatusList());

        // ステータスIDに基づいてステータスを取得し、モデルに追加
        Status status = statusConst.getStatusList().stream()
          .filter(s -> s.getStatusId().equals(company.getStatusId()))
          .findFirst()
          .orElse(null);
        if (status != null) {
          model.addAttribute("status", status);
        }
      } else {
        return "redirect:/company";
      }
    } catch (Exception e) {
      return "redirect:/company";
    }

    return "company/" + viewName;
  }
  
  /**
   * @inhrtitDoc
   */
  @Override
  public Optional<Company> registerCompany(CompanyForm form) {
    var company = mapper.map(form, Company.class);
    return Optional.of(repository.save(company));
  }

  /**
   * @inhrtitDoc
   */
  @Transactional
  @Override
  public Optional<Company> updateCompany(Integer companyId, CompanyForm form) {
    Optional<Company> company = repository.findById(companyId);
    if (company.isPresent()) {
      Company targetCompany = company.get();
      // 登録情報の更新
      targetCompany.setName(form.getName());
      targetCompany.setUserId(form.getUserId());
      targetCompany.setPlatformId(form.getPlatformId());
      targetCompany.setStatusId(form.getStatusId());
      return Optional.of(repository.save(targetCompany));
    } else {
      return Optional.empty();
    }
  }

  /**
   * @inhrtitDoc
   */
  @Override
  public boolean isExistCompany(CompanyForm form) {
    // 既に登録されているならtrueを、そうでなければfalseを返す
    return repository.findByNameAndUserId(form.getName(), form.getUserId()).isPresent();
  }
}
