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
import com.mmm.grenway.javafx.controller.ConsultDetailsController;
import com.mmm.grenway.javafx.controller.RegistrationFormController;

@Component
public class OperatorContentHelper {

	@Autowired
	private ResourceBundle resourceBundle;

	@Autowired
	private ScreenConfig screenConfig;

	@Autowired
	private ConsultDetailsController consultDetailsController;
	@Autowired
	private RegistrationFormController registrationFormController;

	public Tab generateOperatorTab() {
		Tab operatorTab = new Tab();
		operatorTab.setText(resourceBundle.getString("main.tab.operator.title"));

		operatorTab.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				operatorTab.setContent(generateTabPane());
			}
		});
		operatorTab.setClosable(false);

		return operatorTab;
	}

	public Node generateOperatorContent() {
		Node node = generateTabPane();
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
		return node;
	}

	private TabPane generateTabPane() {
		Tab orderDetail = new Tab();
		orderDetail.setText(resourceBundle.getString("main.tab.operator.od.title"));
		orderDetail.setClosable(false);
		orderDetail.setContent(screenConfig.getView(consultDetailsController, "ConsultDetailsPane.fxml"));
		orderDetail.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				consultDetailsController.refreshTable();
			}
		});

		Tab registration = new Tab();
		registration.setText(resourceBundle.getString("main.tab.operator.reg.title"));
		registration.setClosable(false);
		registration.setContent(screenConfig.getView(registrationFormController, "RegistrationFormPane.fxml"));
		registration.selectedProperty().addListener((ob, ov, nv) -> {
			if (ov && !nv) {
				registrationFormController.clearForm();
			}
		});
		registrationFormController.initOperatorButtonBar();

		TabPane operatorTabPane = new TabPane(orderDetail, registration);
		operatorTabPane.setId("operatorTabPane");
		operatorTabPane.setSide(Side.LEFT);

		return operatorTabPane;
	}
}
