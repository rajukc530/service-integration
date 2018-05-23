package com.smartbot.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.exceptions.MessengerVerificationException;
import com.github.messenger4j.receive.MessengerReceiveClient;
import com.smartbot.integration.service.FBMessageService;

@RestController
@RequestMapping("/facebookwebhook")
public class FBServiceIntegrationController {

  private final MessengerReceiveClient receiveClient;

  @Autowired
  public FBServiceIntegrationController(@Value("${messenger4j.appSecret}") final String appSecret,
      @Value("${messenger4j.verifyToken}") final String verifyToken,
      final FBMessageService messageService) {
    this.receiveClient = MessengerPlatform.newReceiveClientBuilder(appSecret, verifyToken)
        .onTextMessageEvent(messageService.newTextMessageEventHandler()).build();
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") final String mode,
      @RequestParam("hub.verify_token") final String verifyToken,
      @RequestParam("hub.challenge") final String challenge) {
    try {
      return ResponseEntity.ok(this.receiveClient.verifyWebhook(mode, verifyToken, challenge));
    } catch (MessengerVerificationException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> handleCallback(@RequestBody final String payload,
      @RequestHeader("X-Hub-Signature") final String signature, String recpientId) {
    try {
      this.receiveClient.processCallbackPayload(payload, signature);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (MessengerVerificationException e) {
      return ResponseEntity.status(HttpStatus.OK).build();
    }
  }

}
