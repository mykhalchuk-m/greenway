package com.mmm.grenway.javafx.controller;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mmm.greenway.entity.OrderType;
import com.mmm.grenway.javafx.controller.helper.TextFieldValidatable;
import com.mmm.grenway.javafx.dto.BaseOrderDto;
import com.mmm.grenway.javafx.dto.BaseOrderFilterDto;
import com.mmm.grenway.javafx.service.BaseOrderService;
import com.mmm.grenway.javafx.util.DateUtil;

@Component
@Scope("prototype")
public class ConsultDetailsController {

	private static final String OFFICE_VALUE = "office";
	private BaseOrderFilterDto baseOrderFilterDto = new BaseOrderFilterDto();
	private Long currentItemId = null;

	@Autowired
	protected RegistrationFormController registrationFormController;
	@Autowired
	protected BaseOrderService baseOrderService;
	@Autowired
	protected ResourceBundle resourceBundle;
	@Autowired
	protected ResourceBundle enumBundle;

	protected ObservableList<BaseOrderDto> getData() {
		return baseOrderService.findOrderDetails(baseOrderFilterDto);
	}

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

	@FXML
	protected void doConsulting(ActionEvent event) {
		if (isFormValid()) {
			baseOrderService.save(prepateOrderDetail());
			refreshTable();
			doCancel();
		}
	}

	@FXML
	protected void doRegistration(ActionEvent event) {
		if (isFormValid()) {
			registrationFormController.setOrder(prepateOrderDetail());
			Scene scene = ((Node) event.getTarget()).getScene();
			TabPane operatorTabPane = (TabPane) scene.lookup("#operatorTabPane");
			operatorTabPane.getSelectionModel().select(1);
			doCancel();
		}
	}

	@FXML
	protected void doCancel() {
		supplierField.clear();
		supplierCheckBox.setSelected(false);
		clientName.clear();
		clientPhone.clear();
		phoneCodes.clear();
		setDefaultOperator();
		noteArea.clear();
		currentItemId = null;
	}

	protected void setDefaultOperator() {
		operatorField.setText(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername());
	}

	protected void initTableRowDoubleClick() {
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

	protected BaseOrderDto prepateOrderDetail() {
		BaseOrderDto baseOrderDto = new BaseOrderDto();
		if (currentItemId != null && currentItemId != 0) {
			baseOrderDto.getId().set(currentItemId);
		}
		baseOrderDto.getClientName().set(clientName.getText());
		baseOrderDto.getOperator().set(operatorField.getText());
		if (supplierCheckBox.isSelected()) {
			baseOrderDto.getSupplierName().set(OFFICE_VALUE);
		} else {
			baseOrderDto.getSupplierName().set(supplierField.getText());
		}
		baseOrderDto.getPhoneNumber().set(
				new StringBuilder(phoneCountryCode.getText()).append("(").append(phoneCodes.getText()).append(")")
						.append(clientPhone.getText()).toString());
		baseOrderDto.getNote().set(noteArea.getText());
		baseOrderDto.getDate().set(DateUtil.format(LocalDateTime.now()));
		baseOrderDto.getOrderType().set(OrderType.CONSULT);
		return baseOrderDto;
	}

	protected boolean isFormValid() {
		return supplierField.isValid() && clientName.isValid() && phoneCodes.isValid() && clientPhone.isValid();
	}

	public void refreshTable() {
		baseOrderTab.setItems(getData());
	}

	protected void initTableColumns() {
		baseOrderTab.setItems(getData());

		clientNameColumn.setCellValueFactory(value -> value.getValue().getClientName());
		clientNameColumn.setSortable(false);
		clientPhoneColumn.setCellValueFactory(value -> value.getValue().getPhoneNumber());
		clientPhoneColumn.setSortable(false);
		supplierColumn.setCellValueFactory(value -> value.getValue().getSupplierName());
		supplierColumn.setSortable(false);
		dateColumn.setCellValueFactory(value -> value.getValue().getDate());
		dateColumn.setSortable(false);
		orderTypeColumn.setCellValueFactory(value -> new SimpleStringProperty(enumBundle.getString(value.getValue()
				.getOrderType().get().name())));
		orderTypeColumn.setSortable(false);
		documentsColumn.setCellValueFactory(value -> new SimpleStringProperty(enumBundle.getString(value.getValue()
				.getDocumnentsStatus().get().name())));
		documentsColumn.setSortable(false);
		registrationColumn.setCellValueFactory(value -> new SimpleStringProperty(enumBundle.getString(value.getValue()
				.getRegistration().get().name())));
		registrationColumn.setSortable(false);
		operatorColumn.setCellValueFactory(value -> value.getValue().getOperator());
		operatorColumn.setSortable(false);
		isDoneColumn.setCellValueFactory(value -> value.getValue().getIsDone());
		isDoneColumn.setCellFactory(value -> {
			return new TableCell<BaseOrderDto, Boolean>() {
				@Override
				protected void updateItem(Boolean item, boolean empty) {
					if (item != null && !empty && item) {
						ImageView isDoneImage = new ImageView(new Image(getClass().getResourceAsStream(
								"/img/done-icon.png")));
						isDoneImage.setFitWidth(20);
						isDoneImage.setFitHeight(20);
						setPadding(new Insets(0));
						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						setGraphic(isDoneImage);
					}
				}
			};
		});
		isDoneColumn.setSortable(false);
	}

	protected void initFiltersListeners() {
		filterClientName.textProperty().addListener((ob, ov, nv) -> {
			baseOrderTab.setItems(getData());
		});

		filterPhone.textProperty().addListener((ob, ov, nv) -> {
			baseOrderTab.setItems(getData());
		});
	}

	@FXML
	protected TextField filterClientName;
	@FXML
	protected TextField filterPhone;
	@FXML
	protected TableView<BaseOrderDto> baseOrderTab;
	@FXML
	protected TableColumn<BaseOrderDto, String> clientNameColumn;
	@FXML
	protected TableColumn<BaseOrderDto, String> clientPhoneColumn;
	@FXML
	protected TableColumn<BaseOrderDto, String> supplierColumn;
	@FXML
	protected TableColumn<BaseOrderDto, String> dateColumn;
	@FXML
	protected TableColumn<BaseOrderDto, String> documentsColumn;
	@FXML
	protected TableColumn<BaseOrderDto, String> registrationColumn;
	@FXML
	protected TableColumn<BaseOrderDto, String> operatorColumn;
	@FXML
	protected TableColumn<BaseOrderDto, String> orderTypeColumn;
	@FXML
	protected TableColumn<BaseOrderDto, Boolean> isDoneColumn;

	@FXML
	protected TextFieldValidatable supplierField;
	@FXML
	protected CheckBox supplierCheckBox;
	@FXML
	protected TextFieldValidatable clientName;
	@FXML
	protected TextFieldValidatable clientPhone;
	@FXML
	protected TextField operatorField;
	@FXML
	protected TextArea noteArea;
	@FXML
	protected Button buttonCancel;
	@FXML
	protected Button registerButton;
	@FXML
	protected Button consultBuppon;
	@FXML
	protected AnchorPane root;
	@FXML
	protected TextFieldValidatable phoneCodes;
	@FXML
	protected TextField phoneCountryCode;
}
