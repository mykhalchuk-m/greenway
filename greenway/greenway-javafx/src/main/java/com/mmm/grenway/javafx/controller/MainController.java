package com.mmm.grenway.javafx.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mmm.greenway.entity.UserRole;
import com.mmm.grenway.javafx.controller.helper.AdminContentHelper;
import com.mmm.grenway.javafx.controller.helper.OperatorContentHelper;

@Component
public class MainController {

	@FXML
	private TabPane tabPane;
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private AdminContentHelper adminContentHelper;
	@Autowired
	private OperatorContentHelper operatorContentHelper;

	@FXML
	private void initialize() {
		System.out.println("Main controller");

		UserRole userRole = UserRole.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.iterator().next().getAuthority());
		List<Tab> tabs = new ArrayList<Tab>();
		switch (userRole) {
		case ROLE_ADMIN:
			tabs.add(adminContentHelper.generateAdminTab());
		case ROLE_REGISTRATOR:
			tabs.add(new Tab(resourceBundle.getString("main.tab.registrator.title")));
		case ROLE_DOKUMENTOLOH:
			tabs.add(new Tab(resourceBundle.getString("main.tab.documetoloh.title")));
		case ROLE_OPERATOR:
			tabs.add(operatorContentHelper.generateOperatorTab());
		}
		Collections.reverse(tabs);
		tabPane.getTabs().addAll(tabs);
	}
}
