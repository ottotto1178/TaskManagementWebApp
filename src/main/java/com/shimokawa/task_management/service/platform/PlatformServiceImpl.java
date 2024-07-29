package com.shimokawa.task_management.service.platform;

import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.shimokawa.task_management.entity.Platform;
import com.shimokawa.task_management.form.PlatformForm;
import com.shimokawa.task_management.repository.PlatformRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {
  /** プラットフォームテーブルDAO */
  private final PlatformRepository repository;

  /** Dozer Mapper */
	private final Mapper mapper;

  /**
   * @inhrtitDoc
   */
  @Override
  public List<Platform> findAll() {
    return repository.findAll();
  }

  /**
   * @inhrtitDoc
   */
  @Override
  public Optional<Platform>registerPlatform(PlatformForm form) {
    var platform = mapper.map(form, Platform.class);
    return Optional.of(repository.save(platform));
    
  }
}
