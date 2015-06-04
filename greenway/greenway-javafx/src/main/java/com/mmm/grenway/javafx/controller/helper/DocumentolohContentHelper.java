package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
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

	public Tab genetateDocumentolohTab(ProgressIndicator progressIndicator) {
		Tab docTab = new Tab(resourceBundle.getString("main.tab.documetoloh.general.title"));

		docTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				docTab.setContent(generatreTabPane(progressIndicator));
			}
		});
		docTab.setClosable(false);

		return docTab;
	}

	public Node genetateDocumentolohContent(ProgressIndicator progressIndicator) {
		Node node = generatreTabPane(progressIndicator);
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
		return node;
	}

	private TabPane generatreTabPane(ProgressIndicator progressIndicator) {
		progressIndicator.setVisible(true);

		Tab docsListTab = new Tab(resourceBundle.getString("main.tab.documetoloh.title"));
		docsListTab.setClosable(false);

		Tab docFormTab = new Tab();
		docFormTab.setText(resourceBundle.getString("main.tab.documetoloh.form.title"));
		docFormTab.setClosable(false);
		docFormTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (ov && !nv) {
				registrationFormController.clearForm();
			}
		});

		TabPane tabPane = new TabPane();
		tabPane.setId("decumentolohTabPane");
		tabPane.setSide(Side.LEFT);

		new Thread(new Runnable() {

			@Override
			public void run() {
				docFormTab.setContent(screenConfig.getView(registrationFormController, "RegistrationFormPane.fxml"));
				registrationFormController.initDocumentolohButtonBar();

				docsListTab.setContent(screenConfig.getView(documentolohController, "DocumentolohPane.fxml"));
				documentolohController.refreshTable();

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						tabPane.getTabs().addAll(docsListTab, docFormTab);
						progressIndicator.setVisible(false);
						docsListTab.selectedProperty().addListener((ob, ov, nv) -> {
							if (nv) {
								documentolohController.refreshTable();
							}
						});
					}
				});
			}
		}).start();

		return tabPane;
	}
}
