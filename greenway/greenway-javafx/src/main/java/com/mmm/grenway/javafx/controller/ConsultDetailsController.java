package com.mmm.grenway.javafx.controller;

import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mmm.greenway.entity.BaseOrder;
import com.mmm.grenway.javafx.controller.helper.TextFieldValidatable;
import com.mmm.grenway.javafx.dto.BaseOrderDto;
import com.mmm.grenway.javafx.service.BaseOrderService;

@Component
public class ConsultDetailsController {

	private static final String OFFICE_VALUE = "office";
	
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private BaseOrderService orderDetailService;

	@FXML
	private void initialize() {
		System.out.println("ConsultDetailsController");
		initTableColumns();

		supplierField.initValidator(resourceBundle.getString("main.tab.operator.od.supplier.validation"));
		clientName.initValidator(resourceBundle.getString("main.tab.operator.od.clientName.validation"));
		clientPhone.initValidator(resourceBundle.getString("main.tab.operator.od.phone.validation"), "\\d{7}$",
				resourceBundle.getString("main.tab.operator.od.phone.regex"));
		root.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				supplierField.hideValidationAlert();
				clientName.hideValidationAlert();
				clientPhone.hideValidationAlert();
			}

		});

		supplierCheckBox.selectedProperty().addListener((obserbebl, oldValue, newValue) -> {
			if (newValue) {
				supplierField.setText("");
				supplierField.setDisable(true);
				supplierField.disableValidation();
			} else {
				supplierField.setDisable(false);
				supplierField.enableValidation();
			}
		});

		setDefaultOperator();
		// TODO: move phone codes into DB
		phoneCodes.getItems().addAll("099", "098", "097", "096", "095", "093", "067", "066", "063");
		phoneCodes.setValue("099");

		orderDetailTab.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> initializeFormWithData(newValue));
	}

	@FXML
	private void doConsulting(ActionEvent event) {
		if (isFormValid()) {
			BaseOrder baseOrder = orderDetailService.save(prepateOrderDetail());
			BaseOrderDto baseOrderDto = new BaseOrderDto(baseOrder);
			orderDetailTab.itemsProperty().getValue().add(baseOrderDto);
		}
		doCancel();
	}

	@FXML
	private void doCancel() {
		supplierField.clear();
		supplierCheckBox.setSelected(false);
		clientName.clear();
		clientPhone.clear();
		setDefaultOperator();
		noteArea.clear();
	}
	
	private void initializeFormWithData(BaseOrderDto orderDetailDto) {
		if (orderDetailDto.getSupplierProp().get().equals(OFFICE_VALUE)) {
			supplierField.setDisable(true);
			supplierCheckBox.setSelected(false);
		} else {
			supplierField.setText(orderDetailDto.getSupplierProp().get());
		}
		clientName.setText(orderDetailDto.getClientNameProp().get());
		clientPhone.setText(orderDetailDto.getClientPhoneProp().get());
		operatorField.setText(orderDetailDto.getOperatorProp().get());
		noteArea.setText(orderDetailDto.getNoteProp().get());
	}

	private void setDefaultOperator() {
		operatorField.setText(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername());
	}

	private void initTableColumns() {
		orderDetailTab.setItems(orderDetailService.findOrderDetails());

		clientNameColumn.setCellValueFactory(value -> value.getValue().getClientNameProp());
		clientPhoneColumn.setCellValueFactory(value -> value.getValue().getClientPhoneProp());
		supplierColumn.setCellValueFactory(value -> value.getValue().getSupplierProp());
		dateColumn.setCellValueFactory(value -> value.getValue().getDateProp());
		statusColumn.setCellValueFactory(value -> value.getValue().getStatusProp());
		operatorColumn.setCellValueFactory(value -> value.getValue().getOperatorProp());
		noteColumn.setCellValueFactory(value -> value.getValue().getNoteProp());
	}

	private BaseOrder prepateOrderDetail() {
		BaseOrder baseOrder = new BaseOrder();
		baseOrder.setClientName(clientName.getText());
		if (supplierCheckBox.isSelected()) {
			baseOrder.setSupplierName(OFFICE_VALUE);
		} else {
			baseOrder.setSupplierName(supplierField.getText());
		}
		baseOrder.setPhoneNumber(new StringBuilder(phoneCountryCode.getText()).append("(")
				.append(phoneCodes.getSelectionModel().getSelectedItem()).append(")").append(clientPhone.getText())
				.toString());
		baseOrder.setNote(noteArea.getText());
		baseOrder.setDate(LocalDate.now());
		return baseOrder;
	}

	private boolean isFormValid() {
		clientName.validate();
		clientPhone.validate();
		supplierField.validate();
		return clientName.isValid() && clientPhone.isValid() && supplierField.isValid();
	}

	@FXML
	private TextField filterClientName;
	@FXML
	private TextField filterPhone;
	@FXML
	private TableView<BaseOrderDto> orderDetailTab;
	@FXML
	private TextFieldValidatable supplierField;
	@FXML
	private CheckBox supplierCheckBox;
	@FXML
	private TextFieldValidatable clientName;
	@FXML
	private TextFieldValidatable clientPhone;
	@FXML
	private TextField operatorField;
	@FXML
	private TextArea noteArea;
	@FXML
	private Button buttonCancel;
	@FXML
	private Button registerButton;
	@FXML
	private Button consultBuppon;
	@FXML
	private AnchorPane root;
	@FXML
	private TableColumn<BaseOrderDto, String> clientNameColumn;
	@FXML
	private TableColumn<BaseOrderDto, String> clientPhoneColumn;
	@FXML
	private TableColumn<BaseOrderDto, String> supplierColumn;
	@FXML
	private TableColumn<BaseOrderDto, String> dateColumn;
	@FXML
	private TableColumn<BaseOrderDto, String> statusColumn;
	@FXML
	private TableColumn<BaseOrderDto, String> operatorColumn;
	@FXML
	private TableColumn<BaseOrderDto, String> noteColumn;
	@FXML
	private ComboBox<String> phoneCodes;
	@FXML
	private TextField phoneCountryCode;
}
