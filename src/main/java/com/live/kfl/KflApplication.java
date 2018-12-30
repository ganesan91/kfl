package com.live.kfl;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Base Spring boot Application triggers from here
@SpringBootApplication
public class KflApplication {

  // For Logging
  private static final Logger logger = Logger.getLogger(KflApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(KflApplication.class, args);
    logger.info("Application Started");
  }
}
