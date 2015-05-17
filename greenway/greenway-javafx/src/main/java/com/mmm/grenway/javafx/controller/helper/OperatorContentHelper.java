package com.mmm.grenway.javafx.controller.helper;

import java.util.ResourceBundle;

import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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

		Tab orderDetail = new Tab();
		orderDetail.setText("Order Details");
		orderDetail.setClosable(false);
		orderDetail.setContent(screenConfig.getView(consultDetailsController, "ConsultDetailsPane.fxml"));
		orderDetail.selectedProperty().addListener((ob, ov, nv) -> {
			if (nv) {
				consultDetailsController.refreshTable();
			}
		});

		Tab registration = new Tab();
		registration.setText("Registration");
		registration.setClosable(false);
		registration.setContent(screenConfig.getView(registrationFormController, "RegistrationFormPane.fxml"));

		TabPane operatorTabPane = new TabPane(orderDetail, registration);
		operatorTabPane.setId("operatorTabPane");
		operatorTabPane.setSide(Side.LEFT);

		operatorTab.setContent(operatorTabPane);

		return operatorTab;
	}
}
