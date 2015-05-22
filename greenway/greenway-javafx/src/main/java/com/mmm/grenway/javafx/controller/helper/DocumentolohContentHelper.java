package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import javafx.scene.control.Tab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.DocumentolohController;

@Component
public class DocumentolohContentHelper {
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	DocumentolohController documentolohController;
	
	public Tab genetateDocumentolohContent() {
		Tab docTab = new Tab(resourceBundle.getString("main.tab.documetoloh.title"));
		docTab.setClosable(false);
		docTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				docTab.setContent(screenConfig.getView(documentolohController, "DocumentolohPane.fxml"));
			}
		});
		return docTab;
	}
}
