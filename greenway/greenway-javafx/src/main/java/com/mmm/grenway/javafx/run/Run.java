package com.mmm.grenway.javafx.run;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mmm.grenway.javafx.cfg.AppConfig;
import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.LoginController;

public class Run extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		initLoginView(applicationContext, primaryStage);
	}
	
	private void initLoginView(ApplicationContext applicationContext, Stage primaryStage) {
		ScreenConfig screenConfig = applicationContext.getBean(ScreenConfig.class);
		LoginController loginController = applicationContext.getBean(LoginController.class);
		screenConfig.setPrimaryStage(primaryStage);
		screenConfig.loadView(loginController, "LoginPane.fxml");
	}
}
