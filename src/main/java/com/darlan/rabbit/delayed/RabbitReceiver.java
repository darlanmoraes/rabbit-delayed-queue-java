package com.darlan.rabbit.delayed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitReceiver.class);

  public void receiveMessage(String message) {
    LOGGER.info("Received <{}>", message);
  }

}