package com.smartbot.integration.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.receive.handlers.TextMessageEventHandler;
import com.github.messenger4j.send.NotificationType;
import com.github.messenger4j.send.Recipient;

public class FBMessageService {
	private static final Logger logger = LoggerFactory.getLogger(FBMessageService.class);
	
	@Value("${messenger4j.pageAccessToken}") 
	private String pageAccessToken;

	public TextMessageEventHandler newTextMessageEventHandler() {

		return event -> {
			logger.debug("Received TextMessageEvent: {}", event);

			final String messageId = event.getMid();
			final String messageText = event.getText();
			final String senderId = event.getSender().getId();
			final Date timestamp = event.getTimestamp();

			//CALL NLP
			logger.info("Received message '{}' with text '{}' from user '{}' at '{}'", messageId, messageText, senderId,
					timestamp);

			switch (messageText.toLowerCase()) {

			case "hello":
				sendTextMessage(senderId, "Hello, What I can do for you ? Type the word you're looking for");
				break;

			case "user":
				sendTextMessage(senderId, "Okay, Make appropriate selection Below.....");
				break;

			default:
				sendTextMessage(senderId, "I do not know what are you talking about");
			}

		};
	}

	private void sendTextMessage(String recipientId, String text) {

		final Recipient recipient = Recipient.newBuilder().recipientId(recipientId).build();
		final NotificationType notificationType = NotificationType.REGULAR;
		final String metadata = "DEVELOPER_DEFINED_METADATA";

		try {
			MessengerPlatform.newSendClientBuilder(pageAccessToken).build().sendTextMessage(recipient, notificationType, text, metadata);
		} catch (MessengerApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessengerIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
