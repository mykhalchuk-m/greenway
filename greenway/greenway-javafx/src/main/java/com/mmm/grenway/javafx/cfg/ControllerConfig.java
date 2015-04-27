package com.mmm.grenway.javafx.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mmm.grenway.javafx.controller.LoginController;
import com.mmm.grenway.javafx.controller.MainController;

@Configuration
public class ControllerConfig {

	@Bean
	public LoginController loginController() {
		return new LoginController();
	}

	@Bean
	public MainController mainController() {
		return new MainController();
	}
}
