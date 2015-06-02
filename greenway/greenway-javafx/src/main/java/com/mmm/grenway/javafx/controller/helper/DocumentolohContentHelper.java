package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

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

	public Tab genetateDocumentolohTab() {
		Tab docTab = new Tab(resourceBundle.getString("main.tab.documetoloh.general.title"));

		docTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				docTab.setContent(generatreTabPane());
			}
		});
		docTab.setClosable(false);

		return docTab;
	}

	public Node genetateDocumentolohContent() {
		Node node = generatreTabPane();
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
		return node;
	}

	private TabPane generatreTabPane() {
		Tab docsListTab = new Tab(resourceBundle.getString("main.tab.documetoloh.title"));
		docsListTab.setClosable(false);
		docsListTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				docsListTab.setContent(screenConfig.getView(documentolohController, "DocumentolohPane.fxml"));
			}
		});

		Tab docFormTab = new Tab();
		docFormTab.setText(resourceBundle.getString("main.tab.documetoloh.form.title"));
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

		return tabPane;
	}
}
