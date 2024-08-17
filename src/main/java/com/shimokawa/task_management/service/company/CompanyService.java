package com.shimokawa.task_management.service.company;

import java.util.List;
import java.util.Optional;

import com.shimokawa.task_management.entity.Company;
import com.shimokawa.task_management.form.CompanyForm;

public interface CompanyService {
  /**
   * 会社一覧検索
   * 
   * @param userId ユーザID
   * @return 検索結果
   */
  public List<Company> findByUserId(Integer userId);

  /**
   * 会社登録
   * 
   * @param form 会社情報
   */
  public Optional<Company> registerCompany(CompanyForm form);

  /**
   * 既存のものか確認
   * 
   * @param form 入力情報
   * @return 既存のものがあればtrue、なければfalse
   */
  public boolean isExistCompany(CompanyForm form);
}
