package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.InvitationDeliveryController;

@Component
public class InivitationContentHelper {
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private InvitationDeliveryController invitationDeliveryController;

	public Tab genetateInivitationTab(ProgressIndicator progressIndicator) {
		progressIndicator.setVisible(true);
		
		Tab inivitationTab = new Tab(resourceBundle.getString("main.tab.inv.title"));
		inivitationTab.setContent(screenConfig.getView(invitationDeliveryController, "InvitationDeliveryPane.fxml"));
		inivitationTab.setClosable(false);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				invitationDeliveryController.refreshTable();

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						progressIndicator.setVisible(false);
						inivitationTab.selectedProperty().addListener((ob, ov, nv) -> {
							if (nv) {
								invitationDeliveryController.refreshTable();
							}
						});
					}
				});

			}
		}).start();
		return inivitationTab;
	}

	public Node genetateInivitationContent(ProgressIndicator progressIndicator) {
		progressIndicator.setVisible(true);
		
		Node node = screenConfig.getView(invitationDeliveryController, "InvitationDeliveryPane.fxml");
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				invitationDeliveryController.refreshTable();

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						progressIndicator.setVisible(false);
					}
				});

			}
		}).start();
		return node;
	}
}
