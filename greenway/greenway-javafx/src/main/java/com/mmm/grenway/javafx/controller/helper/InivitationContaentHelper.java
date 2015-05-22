package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.InvitationDeliveryController;

import javafx.scene.control.Tab;

@Component
public class InivitationContaentHelper {
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private InvitationDeliveryController invitationDeliveryController;
	
	public Tab genetateInivitationContent() {
		Tab inivitationTab = new Tab(resourceBundle.getString("main.tab.inv.title"));
		inivitationTab.setClosable(false);
		inivitationTab.setContent(screenConfig.getView(invitationDeliveryController, "InvitationDeliveryPane.fxml"));
		return inivitationTab;
	}
}
