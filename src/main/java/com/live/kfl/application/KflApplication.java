package com.live.kfl.application;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KflApplication {

  private static final Logger logger = Logger.getLogger(KflApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(KflApplication.class, args);
    logger.info("Application Started");
  }
}
