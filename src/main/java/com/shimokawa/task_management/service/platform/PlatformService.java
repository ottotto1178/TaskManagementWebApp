package com.shimokawa.task_management.service.platform;

import com.shimokawa.task_management.entity.Platform;
import com.shimokawa.task_management.form.PlatformForm;

import java.util.List;
import java.util.Optional;

public interface PlatformService {
  /**
   * プラットフォーム一覧検索
   * 
   * @param userId ユーザーID
   * @return 検索結果
   */
  public List<Platform> findAll();

  /**
   * プラットフォーム登録
   * 
   * @param form プラットフォーム情報
   */
  public Optional<Platform>registerPlatform(PlatformForm form);
}
