package com.mmm.grenway.javafx.controller;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
import com.mmm.grenway.javafx.dto.BaseOrderFilterDto;
import com.mmm.grenway.javafx.service.BaseOrderService;

@Component
public class ConsultDetailsController {

	private static final String OFFICE_VALUE = "office";
	private BaseOrderFilterDto baseOrderFilterDto = new BaseOrderFilterDto();
	private Long currentItemId = null;

	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private BaseOrderService baseOrderService;
	@Autowired
	private RegistrationFormController registrationFormController;

	@FXML
	private void initialize() {
		System.out.println("ConsultDetailsController");
		initTableColumns();
		initTableRowDoubleClick();
		setDefaultOperator();

		supplierField.initValidator(resourceBundle.getString("main.tab.operator.od.supplier.validation"));
		clientName.initValidator(resourceBundle.getString("main.tab.operator.od.clientName.validation"));
		phoneCodes.initValidator(resourceBundle.getString("main.tab.operator.od.phonecode.validation"), "\\d{3}",
				resourceBundle.getString("main.tab.operator.od.phonecode.regex"));
		clientPhone.initValidator(resourceBundle.getString("main.tab.operator.od.phone.validation"), "\\d{7}$",
				resourceBundle.getString("main.tab.operator.od.phone.regex"));
		phoneCodes.textProperty().addListener((ob, ov, nv) -> {
			if (nv.length() == 3 && phoneCodes.isValid()) {
				clientPhone.requestFocus();
			}
		});
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

		baseOrderFilterDto.getClientNameFilter().bind(filterClientName.textProperty());
		baseOrderFilterDto.getPhoneNumberFilter().bind(filterPhone.textProperty());
		initFiltersListeners();
	}

	public void refreshTable() {
		baseOrderTab.setItems(baseOrderService.findOrderDetails(baseOrderFilterDto));
	}

	@FXML
	private void doConsulting(ActionEvent event) {
		if (isFormValid()) {
			baseOrderService.save(prepateOrderDetail());
			refreshTable();
			doCancel();
		}
	}

	@FXML
	private void doRegistration(ActionEvent event) {
		if (isFormValid()) {
			registrationFormController.setBaseOrder(prepateOrderDetail());
			Scene scene = ((Node) event.getTarget()).getScene();
			TabPane operatorTabPane = (TabPane) scene.lookup("#operatorTabPane");
			operatorTabPane.getSelectionModel().select(1);
			doCancel();
		}
	}

	@FXML
	private void doCancel() {
		supplierField.clear();
		supplierCheckBox.setSelected(false);
		clientName.clear();
		clientPhone.clear();
		phoneCodes.clear();
		setDefaultOperator();
		noteArea.clear();
		currentItemId = null;
	}

	private void setDefaultOperator() {
		operatorField.setText(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername());
	}

	private void initTableColumns() {
		baseOrderTab.setItems(baseOrderService.findOrderDetails(baseOrderFilterDto));

		clientNameColumn.setCellValueFactory(value -> value.getValue().getClientName());
		clientNameColumn.setSortable(false);
		clientPhoneColumn.setCellValueFactory(value -> value.getValue().getPhoneNumber());
		clientPhoneColumn.setSortable(false);
		supplierColumn.setCellValueFactory(value -> value.getValue().getSupplierName());
		supplierColumn.setSortable(false);
		dateColumn.setCellValueFactory(value -> value.getValue().getDate());
		dateColumn.setSortable(false);
		orderTypeColumn.setCellValueFactory(value -> value.getValue().getOrderType());
		orderTypeColumn.setSortable(false);
		documentsColumn.setCellValueFactory(value -> value.getValue().getInvitation());
		documentsColumn.setSortable(false);
		registrationColumn.setCellValueFactory(value -> value.getValue().getRegistration());
		registrationColumn.setSortable(false);
		operatorColumn.setCellValueFactory(value -> value.getValue().getOperator());
		operatorColumn.setSortable(false);
		noteColumn.setCellValueFactory(value -> value.getValue().getNote());
		noteColumn.setSortable(false);
	}

	private void initTableRowDoubleClick() {
		baseOrderTab.setRowFactory(tr -> {
			TableRow<BaseOrderDto> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && !row.isEmpty()) {
					BaseOrderDto baseOrderDto = row.getItem();
					currentItemId = baseOrderDto.getId().get();
					if (baseOrderDto.getSupplierName().get().equals(OFFICE_VALUE)) {
						supplierField.setText("");
						supplierCheckBox.setSelected(true);
					} else {
						supplierField.setText(baseOrderDto.getSupplierName().get());
						supplierCheckBox.setSelected(false);
					}
					clientName.setText(baseOrderDto.getClientName().get());
					phoneCodes.setText(baseOrderDto.getPhoneNumber().get().substring(4, 7));
					clientPhone.setText(baseOrderDto.getPhoneNumber().get().substring(8));
					operatorField.setText(baseOrderDto.getOperator().get());
					noteArea.setText(baseOrderDto.getNote().get());
				}
			});
			return row;
		});
	}

	private BaseOrder prepateOrderDetail() {
		BaseOrder baseOrder = new BaseOrder();
		if (currentItemId != null) {
			baseOrder.setId(currentItemId);
		}
		baseOrder.setClientName(clientName.getText());
		if (supplierCheckBox.isSelected()) {
			baseOrder.setSupplierName(OFFICE_VALUE);
		} else {
			baseOrder.setSupplierName(supplierField.getText());
		}
		baseOrder.setPhoneNumber(new StringBuilder(phoneCountryCode.getText()).append("(").append(phoneCodes.getText())
				.append(")").append(clientPhone.getText()).toString());
		baseOrder.setNote(noteArea.getText());
		baseOrder.setDate(LocalDateTime.now());
		return baseOrder;
	}

	private boolean isFormValid() {
		return supplierField.isValid() && clientName.isValid() && phoneCodes.isValid() && clientPhone.isValid();
	}

	private void initFiltersListeners() {
		filterClientName.textProperty().addListener((ob, ov, nv) -> {
			baseOrderTab.setItems(baseOrderService.findOrderDetails(baseOrderFilterDto));
		});

		filterPhone.textProperty().addListener((ob, ov, nv) -> {
			baseOrderTab.setItems(baseOrderService.findOrderDetails(baseOrderFilterDto));
		});
	}

	@FXML
	private TextField filterClientName;
	@FXML
	private TextField filterPhone;
	@FXML
	private TableView<BaseOrderDto> baseOrderTab;
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
	private TableColumn<BaseOrderDto, String> documentsColumn;
	@FXML
	private TableColumn<BaseOrderDto, String> registrationColumn;
	@FXML
	private TableColumn<BaseOrderDto, String> operatorColumn;
	@FXML
	private TableColumn<BaseOrderDto, String> noteColumn;
	@FXML
	private TableColumn<BaseOrderDto, String> orderTypeColumn;
	@FXML
	private TextFieldValidatable phoneCodes;
	@FXML
	private TextField phoneCountryCode;
}
