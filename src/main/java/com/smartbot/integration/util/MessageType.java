package com.smartbot.integration.util;

public enum MessageType {
  TEXT("text"), PARAMETER("parameter");

  private String type;

  MessageType(String type) {
    this.type = type;
  }

  public String type() {
    return type;
  }

}
