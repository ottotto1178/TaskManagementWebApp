package com.shimokawa.task_management.constant;

public class Status {
  private final Integer statusId;
  private final String name;

  public Status(Integer statusId, String name) {
    this.statusId = statusId;
    this.name = name;
  }

  public Integer getStatusId() {
    return statusId;
  }

  public String getName() {
    return name;
  }
}
