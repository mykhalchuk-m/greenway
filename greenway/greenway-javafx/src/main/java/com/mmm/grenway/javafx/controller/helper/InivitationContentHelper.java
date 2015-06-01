package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import javafx.scene.Node;
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

	public Tab genetateInivitationTab() {
		Tab inivitationTab = new Tab(resourceBundle.getString("main.tab.inv.title"));
		inivitationTab.setClosable(false);
		inivitationTab.selectedProperty().addListener(
				(ob, ov, nv) -> {
					if (nv) {
						inivitationTab.setContent(screenConfig.getView(invitationDeliveryController,
								"InvitationDeliveryPane.fxml"));
					}
				});
		return inivitationTab;
	}

	public Node genetateInivitationContent() {
		Node node = screenConfig.getView(invitationDeliveryController, "InvitationDeliveryPane.fxml");
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
		return node;
	}
}
