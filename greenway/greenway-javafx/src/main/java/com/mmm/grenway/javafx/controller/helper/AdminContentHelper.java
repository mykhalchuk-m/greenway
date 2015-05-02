package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.CreateUserController;
import com.mmm.grenway.javafx.controller.ShowUsersController;

@Component
public class AdminContentHelper {
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private CreateUserController createUserController;
	@Autowired
	private ShowUsersController showUsersController;

	public Tab generateAdminTab() {
		Tab adminTab = new Tab();
		adminTab.setText(resourceBundle.getString("main.tab.admin.title"));

		Tab generateReportTab = new Tab();
		generateReportTab.setText(resourceBundle.getString("main.tab.admin.report.title"));
		generateReportTab.setClosable(false);

		Tab createUserTab = new Tab();
		createUserTab.setText(resourceBundle.getString("main.tab.admin.createuser.title"));
		createUserTab.setClosable(false);
		createUserTab.setContent(screenConfig.getView(createUserController, "CreateUserPane.fxml"));

		Tab showAllUsersTab = new Tab();
		showAllUsersTab.setText(resourceBundle.getString("main.tab.admin.showusers.title"));
		showAllUsersTab.setClosable(false);
		showAllUsersTab.setOnSelectionChanged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (showAllUsersTab.isSelected()) {
					showAllUsersTab.setContent(screenConfig.getView(showUsersController, "ShowUsersPane.fxml"));
				}
			}

		});

		TabPane adminTabPane = new TabPane(generateReportTab, createUserTab, showAllUsersTab);
		adminTabPane.setId("adminTabPane");
		adminTabPane.setSide(Side.LEFT);
		adminTab.setContent(adminTabPane);

		return adminTab;
	}
}
