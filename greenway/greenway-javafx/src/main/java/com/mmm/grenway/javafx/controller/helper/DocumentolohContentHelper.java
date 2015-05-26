package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.cfg.ScreenConfig;
import com.mmm.grenway.javafx.controller.DocumentolohController;
import com.mmm.grenway.javafx.controller.RegistrationFormController;

@Component
public class DocumentolohContentHelper {
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private ScreenConfig screenConfig;
	@Autowired
	private DocumentolohController documentolohController;
	@Autowired
	private RegistrationFormController registrationFormController;

	public Tab genetateDocumentolohContent() {
		Tab docTab = new Tab("Documentoloh's Tab");

		Tab docsListTab = new Tab(resourceBundle.getString("main.tab.documetoloh.title"));
		docsListTab.setClosable(false);
		docsListTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				docsListTab.setContent(screenConfig.getView(documentolohController, "DocumentolohPane.fxml"));
			}
		});

		Tab docFormTab = new Tab();
		docFormTab.setText("Order Information");
		docFormTab.setClosable(false);
		docFormTab.setContent(screenConfig.getView(registrationFormController, "RegistrationFormPane.fxml"));
		docFormTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (ov && !nv) {
				registrationFormController.clearForm();
			}
		});
		registrationFormController.initDocumentolohButtonBar();

		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(docsListTab, docFormTab);
		tabPane.setId("decumentolohTabPane");
		tabPane.setSide(Side.LEFT);

		docTab.setContent(tabPane);

		return docTab;
	}
}
