package com.shimokawa.task_management.form;

import java.util.Date;

import lombok.Data;

@Data
public class TaskForm {
  private int companyId;
  private String task;
  private String notes;
  private Date nextScheduleDate;
  private int priority;
}
