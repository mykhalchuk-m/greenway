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
import com.mmm.grenway.javafx.controller.RegistratorController;

@Component
public class RegistratorContentHelper {
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private RegistratorController registratorController;

	public Tab genetateRegistratorTab(ProgressIndicator progressIndicator) {
		progressIndicator.setVisible(true);

		Tab registratorTab = new Tab(resourceBundle.getString("main.tab.registrator.title"));
		registratorTab.setClosable(false);

		new Thread(new Runnable() {

			@Override
			public void run() {
				registratorTab.setContent(screenConfig.getView(registratorController, "RegistratorPane.fxml"));
				registratorController.refreshTable();

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						progressIndicator.setVisible(false);
						registratorTab.selectedProperty().addListener((ob, ov, nv) -> {
							if (nv) {
								registratorController.refreshTable();
							}
						});
					}
				});

			}
		}).start();
		return registratorTab;
	}

	public Node genetateRegistratorContent(ProgressIndicator progressIndicator) {
		progressIndicator.setVisible(true);
		
		Node node = screenConfig.getView(registratorController, "RegistratorPane.fxml");
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				registratorController.refreshTable();

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
