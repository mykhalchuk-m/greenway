package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.RegistratorController;

@Component
public class RegistratorContentHelper {
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private RegistratorController registratorController;
	
	public Tab genetateRegistratorTab() {
		Tab registratorTab = new Tab(resourceBundle.getString("main.tab.registrator.title"));
		registratorTab.setClosable(false);
		registratorTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				registratorTab.setContent(screenConfig.getView(registratorController, "RegistratorPane.fxml"));
			}
		});
		return registratorTab;
	}
	
	public Node genetateRegistratorContent() {
		Node node = screenConfig.getView(registratorController, "RegistratorPane.fxml");
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
		return node;
	}
}
