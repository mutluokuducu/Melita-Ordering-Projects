package com.melitaorder.utils;

import com.meltaorder.services.ConsumerService;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@RabbitListenerTest(spy = false, capture = true)
public class Config {

}

