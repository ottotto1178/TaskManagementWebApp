package com.shimokawa.task_management.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserEditForm {
  @NotBlank(message = "{signup.invalidName}")
  private String name;

  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9]*\\.)+[a-zA-Z]{2,}$", message = "{signup.invalidMailAddress}")
  private String mailAddress;

  @Length(min = 8, max = 20, message = "{signup.invalidPassword}")
  private String password;
}
