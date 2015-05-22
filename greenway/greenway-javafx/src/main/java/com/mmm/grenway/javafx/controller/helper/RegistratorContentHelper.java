package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import javafx.scene.control.Tab;

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
	
	public Tab genetateRegistratorContent() {
		Tab registratorTab = new Tab(resourceBundle.getString("main.tab.registrator.title"));
		registratorTab.setClosable(false);
		registratorTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				registratorTab.setContent(screenConfig.getView(registratorController, "RegistratorPane.fxml"));
			}
		});
		return registratorTab;
	}
}
