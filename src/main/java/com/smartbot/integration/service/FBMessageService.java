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
import com.smartbot.integration.model.Response;
import com.smartbot.integration.util.Constants;
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
      // 1.  Validate Request
      // 2.  Find Page Token based on receiptID
      // 3.  CALL NLP
      final Response response = new Response(); 
      response.setTextMessage("Hello! Welcome to the Smart Bot. We will reply soon. Thanks!!!");
      logger.info("Request:" + request.toString());
      logger.info("Response:"+ response.toString());
      System.out.println("request1:"+request.toString());
      sendTextMessage(request, response);

    };
  }

  private void sendTextMessage(final Request request, final Response response) {
    final Recipient recipient = Recipient.newBuilder().recipientId(request.getSenderId()).build();
    final NotificationType notificationType = NotificationType.REGULAR;
    final String metadata = "DEVELOPER_DEFINED_METADATA";
    try {
      System.out.println("request:"+request.toString());
      final String fbPageAccessToken=Constants.fbPageMapper.get(request.getReceiptId());
      System.out.println("fbPageAccessToken:"+fbPageAccessToken);
      MessengerPlatform.newSendClientBuilder(fbPageAccessToken).build().sendTextMessage(recipient,
          notificationType, response.getTextMessage(), metadata);
    } catch (MessengerApiException e) {
      e.printStackTrace();
    } catch (MessengerIOException e) {
      e.printStackTrace();
    }

  }

}
