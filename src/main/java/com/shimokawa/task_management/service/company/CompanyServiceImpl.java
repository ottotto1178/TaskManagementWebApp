package com.shimokawa.task_management.service.company;

import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.shimokawa.task_management.entity.Company;
import com.shimokawa.task_management.form.CompanyForm;
import com.shimokawa.task_management.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
  /** Company Repository */
  private final CompanyRepository repository;

  /** Dozer Mapper */
	private final Mapper mapper;

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
  public Optional<Company> registerCompany(CompanyForm form) {
    var company = mapper.map(form, Company.class);
    return Optional.of(repository.save(company));
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
