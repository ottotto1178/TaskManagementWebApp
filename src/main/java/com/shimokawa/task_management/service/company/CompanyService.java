package com.shimokawa.task_management.service.company;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;

import com.shimokawa.task_management.entity.Company;
import com.shimokawa.task_management.form.CompanyForm;

public interface CompanyService {
  /**
   * 企業一覧検索
   * 
   * @param userId ユーザID
   * @return 検索結果
   */
  public List<Company> findByUserId(Integer userId);

  /**
   * 画面表示前処理
   * 
   * @param companyId 企業ID
   * @param model Model
   * @param viewName 表示画面名
   * @return 表示画面名
   */
  public String viewDisplay(Integer companyId, Model model, String viewName);

  /**
   * 企業登録
   * 
   * @param form 企業情報
   */
  public Optional<Company> registerCompany(CompanyForm form);

  /**
   * 企業情報更新
   * 
   * @param companyId 企業ID
   * @param form 企業情報
   */
  public Optional<Company> updateCompany(Integer companyId, CompanyForm form);

  /**
   * 既存のものか確認
   * 
   * @param form 入力情報
   * @return 既存のものがあればtrue、なければfalse
   */
  public boolean isExistCompany(CompanyForm form);
}
