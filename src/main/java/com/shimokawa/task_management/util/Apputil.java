package com.shimokawa.task_management.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class Apputil {
  public static String getMessage(MessageSource messageSource, String key, Object... params) {
    return messageSource.getMessage(key, params, Locale.JAPAN);
  }
}
