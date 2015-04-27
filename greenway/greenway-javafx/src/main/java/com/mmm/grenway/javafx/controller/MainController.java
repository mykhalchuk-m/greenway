package com.mmm.grenway.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import org.springframework.stereotype.Component;

@Component
public class MainController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Main controller");
	}
}
