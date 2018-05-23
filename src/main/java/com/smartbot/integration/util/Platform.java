package com.smartbot.integration.util;

public enum Platform {
  FB("Facebook Messenger"), SLACK("Slack"), SKYPE("Skype"), VIBER("Viber");

  private String channel;

  Platform(String channel) {
    this.channel = channel;
  }

  public String channel() {
    return channel;
  }
}
