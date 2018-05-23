package com.smartbot.integration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.receive.handlers.TextMessageEventHandler;
import com.github.messenger4j.send.NotificationType;
import com.github.messenger4j.send.Recipient;
import com.smartbot.integration.model.Request;
import com.smartbot.integration.util.MessageType;
import com.smartbot.integration.util.Platform;

@Service
public class FBMessageService {
  private static final Logger logger = LoggerFactory.getLogger(FBMessageService.class);

  @Value("${messenger4j.pageAccessToken}")
  private String pageAccessToken;

  public TextMessageEventHandler newTextMessageEventHandler() {

    return event -> {
      logger.debug("Received TextMessageEvent: {}", event);

      final Request request = new Request();
      request.setMessageId(event.getMid());
      request.setMessageText(event.getText());
      request.setSenderId(event.getSender().getId());
      request.setMessageTimestamp(event.getTimestamp());
      request.setReceiptId(event.getRecipient().getId());
      request.setMessageType(MessageType.TEXT_MESSAGE);
      request.setPlatform(Platform.FB);
      // Validate Request
      // CALL NLP
      logger.info("Request:" + request.toString());
      sendTextMessage(request, "Hello I am a Chat BOT. Thanks for inquiry..");

    };
  }

  private void sendTextMessage(final Request request, String text) {
    final Recipient recipient = Recipient.newBuilder().recipientId(request.getSenderId()).build();
    final NotificationType notificationType = NotificationType.REGULAR;
    final String metadata = "DEVELOPER_DEFINED_METADATA";
    try {
      MessengerPlatform.newSendClientBuilder(pageAccessToken).build().sendTextMessage(recipient,
          notificationType, text, metadata);
    } catch (MessengerApiException e) {
      e.printStackTrace();
    } catch (MessengerIOException e) {
      e.printStackTrace();
    }

  }

}
