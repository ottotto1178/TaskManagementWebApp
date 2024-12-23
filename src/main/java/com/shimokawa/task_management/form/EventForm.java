package com.shimokawa.task_management.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EventForm {
  private int userId;

  @NotNull(message = "{company.invalidName}")
  private int companyId;

  @NotNull(message = "{date.invalidName}")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date date;
  
  @NotNull(message = "{event.invalidName}")
  @Size(min = 1, max = 50, message = "{event.max}")
  private String event;

  @Size(max = 200, message = "{note.max}")
  private String note;
}
