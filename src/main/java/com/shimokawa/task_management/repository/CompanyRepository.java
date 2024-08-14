package com.shimokawa.task_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shimokawa.task_management.entity.Company;

/**
 * 企業情報リポジトリ
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
  // ユーザーIDで企業を検索
  List<Company> findByUserId(Integer userId);
}
