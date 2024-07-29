package com.shimokawa.task_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shimokawa.task_management.entity.Platform;

/**
 * プラットフォーム情報リポジトリ
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {
  
}
