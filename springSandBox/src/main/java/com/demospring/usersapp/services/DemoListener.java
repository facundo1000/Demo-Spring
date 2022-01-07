package com.demospring.usersapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener {
  private static final Logger log = LoggerFactory.getLogger(DemoListener.class);

  /**
   * Si tengo muchos mensajes puedo hacer una lista de topics
   *
   * @param messagge
   */
  @KafkaListener(topics = "demoTopic", groupId = "DemoGroup")
  public void listen(String messagge) {
    log.info("Messagge Received-> {}", messagge);
  }
}
