package com.shimokawa.task_management.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyForm {
  @NotNull(message = "{company.invalidName}")
  @Size(min = 1, message = "{company.invalidName}")
  private String name;

  private Integer userId;

  @NotNull(message = "{company.invalidPlatformId}")
  private Integer platformId;

  @NotNull(message = "{company.invalidStatusId}")
  private Integer statusId;
}
