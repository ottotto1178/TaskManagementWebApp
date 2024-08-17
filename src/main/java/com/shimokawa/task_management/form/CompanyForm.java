package com.shimokawa.task_management.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompanyForm {
  @NotBlank(message = "{company.invalidName}")
  private String name;

  private Integer userId;

  @NotNull(message = "{company.invalidPlatformId}")
  private Integer platformId;

  @NotNull(message = "{company.invalidStatusId}")
  private Integer statusId;
}
