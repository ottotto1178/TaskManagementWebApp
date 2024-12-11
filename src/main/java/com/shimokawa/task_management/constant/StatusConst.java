package com.shimokawa.task_management.constant;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StatusConst {
  private final List<Status> statusList = Arrays.asList(
      new Status(1, "エントリー済み"),
      new Status(2, "選考中"),
      new Status(3, "選考辞退"),
      new Status(4, "不採用"),
      new Status(5, "内定"),
      new Status(6, "内定辞退")
    );

  public List<Status> getStatusList() {
    return statusList;
  }
}
