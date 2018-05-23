package com.smartbot.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skypewebhook")
public class SkypeServiceIntegrationController {

  private static final Logger logger =
      LoggerFactory.getLogger(SkypeServiceIntegrationController.class);

}
