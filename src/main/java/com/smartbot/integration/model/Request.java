package com.smartbot.integration.model;

import java.util.Date;

public class Request {
    private String senderId;
    private String messageType;
    private String messageText;
    private String platform;
    private String receiptId;
    private String messageId;
    private Date messageTimestamp;

    public Request() {

    }

    public String getSenderId() {
      return senderId;
    }

    public void setSenderId(String senderId) {
      this.senderId = senderId;
    }

    public String getMessageType() {
      return messageType;
    }

    public void setMessageType(String messageType) {
      this.messageType = messageType;
    }

    public String getMessageText() {
      return messageText;
    }

    public void setMessageText(String message) {
      this.messageText = message;
    }

    public String getPlatform() {
      return platform;
    }

    public void setPlatform(String platform) {
      this.platform = platform;
    }

    public String getReceiptId() {
      return receiptId;
    }

    public void setReceiptId(String receiptId) {
      this.receiptId = receiptId;
    }

    public String getMessageId() {
      return messageId;
    }

    public void setMessageId(String messageId) {
      this.messageId = messageId;
    }

    public Date getMessageTimestamp() {
      return messageTimestamp;
    }

    public void setMessageTimestamp(Date messageTimestamp) {
      this.messageTimestamp = messageTimestamp;
    }

    @Override
    public String toString() {
      return "Request [senderId=" + senderId + ", messageType=" + messageType + ", message="
          + messageText + ", platform=" + platform + ", receiptId=" + receiptId + ", messageId="
          + messageId + ", messageTimestamp=" + messageTimestamp + "]";
    }

    
}