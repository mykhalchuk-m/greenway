package com.mmm.grenway.javafx.cfg;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.mmm.grenway.javafx.controller.helper", "com.mmm.grenway.javafx.service",
		"com.mmm.grenway.javafx.loggin", "com.mmm.grenway.javafx.controller" })
public class ControllerConfig {

}
