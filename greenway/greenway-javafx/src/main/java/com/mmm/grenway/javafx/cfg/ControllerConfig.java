package com.mmm.grenway.javafx.cfg;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mmm.grenway.javafx.controller.CreateUserController;
import com.mmm.grenway.javafx.controller.LoginController;
import com.mmm.grenway.javafx.controller.MainController;
import com.mmm.grenway.javafx.controller.ShowUsersController;

@Configuration
@ComponentScan({"com.mmm.grenway.javafx.controller.helper", "com.mmm.grenway.javafx.service"})
public class ControllerConfig {

	@Bean
	public ResourceBundle resourceBundle() {
		return ResourceBundle.getBundle("lang", new Locale("ua", "UA"));
	}
	
	@Bean
	public LoginController loginController() {
		return new LoginController();
	}

	@Bean
	public MainController mainController() {
		return new MainController();
	}
	
	@Bean
	public CreateUserController createUserController() {
		return new CreateUserController();
	}
	
	@Bean
	public ShowUsersController showUsersController() {
		return new ShowUsersController();
	}
}
