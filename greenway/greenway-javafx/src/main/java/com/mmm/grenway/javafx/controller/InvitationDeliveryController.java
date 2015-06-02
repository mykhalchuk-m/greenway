package com.mmm.grenway.javafx.controller;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.greenway.entity.ProcessingStatus;
import com.mmm.grenway.javafx.controller.converter.ProcessingStatusConverter;
import com.mmm.grenway.javafx.controller.utils.DocumentsUtils;
import com.mmm.grenway.javafx.dto.BaseOrderFilterDto;
import com.mmm.grenway.javafx.dto.DetailedOrderDto;
import com.mmm.grenway.javafx.service.DetailedOrderService;

@Component
public class InvitationDeliveryController {

	@Autowired
	private DetailedOrderService detailedOrderService;
	@Autowired
	protected ResourceBundle resourceBundle;
	@Autowired
	protected ResourceBundle enumBundle;
	private DetailedOrderDto currentItem = new DetailedOrderDto();
	private BaseOrderFilterDto orderFilterDto = new BaseOrderFilterDto();

	@FXML
	private void initialize() {
		System.out.println("InvitationDeliveryController");
		initTableData();
		initTableRowDoubleClick();
		initFiltersListeners();
		invitationStatus.setItems(FXCollections.observableArrayList(ProcessingStatus.values()));
		invitationStatus.getSelectionModel().select(0);
		invitationStatus.setConverter(new ProcessingStatusConverter(enumBundle));
		clientNameField.setEditable(false);
	}

	@FXML
	private void doSave() {
		populateChangedProperties();
		DocumentsUtils.calculateDocumentsStatus(currentItem);
		detailedOrderService.save(currentItem);
		refreshTable();
		doCancel();
	}

	@FXML
	private void doCancel() {
		for (Field field : getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(FXML.class)) {
				field.setAccessible(true);
				try {
					if (field.getName().endsWith("Field") && field.getType().equals(TextField.class)) {
						TextField textField = (TextField) field.get(this);
						textField.setText("");
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		invitationStatus.setValue(ProcessingStatus.NONE);
	}

	private void initTableRowDoubleClick() {
		usersTable
				.setRowFactory(tr -> {
					TableRow<DetailedOrderDto> row = new TableRow<>();
					row.setOnMouseClicked(event -> {
						if (event.getClickCount() == 2 && !row.isEmpty()) {
							currentItem = row.getItem();
							clientNameField.setText(currentItem.getClientName().get());
							invitationPriceField.setText(Double.toString(currentItem.getInvitationDocument().getPrice()
									.get()));
							issuerField.setText(currentItem.getOrderClinetHostDto().getHostData().get());
							countryField.setText(currentItem.getOrderClinetHostDto().getCountry().get());
							localityField.setText(currentItem.getOrderClinetHostDto().getLocality().get());
							zipcodeField.setText(currentItem.getOrderClinetHostDto().getZipCode().get());
							phoneField.setText(currentItem.getOrderClinetHostDto().getPhone().get());
							streetField.setText(currentItem.getOrderClinetHostDto().getStreet().get());
							emailField.setText(currentItem.getOrderClinetHostDto().getEmail().get());
							invitationStatus.setValue(currentItem.getInvitationDocument().getStatus().get());
						}
					});
					return row;
				});
	}

	private void initFiltersListeners() {
		filterUserName.textProperty().addListener((ob, ov, nv) -> {
			usersTable.setItems(getData());
		});

		filterPhoneNumber.textProperty().addListener((ob, ov, nv) -> {
			usersTable.setItems(getData());
		});
	}

	public void refreshTable() {
		usersTable.setItems(getData());
	}
	
	private ObservableList<DetailedOrderDto> getData() {
		return detailedOrderService.findDetailedOrdersForInvitator(orderFilterDto);
	}

	private void populateChangedProperties() {
		currentItem.getClientName().set(clientNameField.getText());
		currentItem.getInvitationDocument().getPrice().set(Double.parseDouble(invitationPriceField.getText()));
		currentItem.getOrderClinetHostDto().getHostData().set(issuerField.getText());
		currentItem.getOrderClinetHostDto().getCountry().set(countryField.getText());
		currentItem.getOrderClinetHostDto().getLocality().set(localityField.getText());
		currentItem.getOrderClinetHostDto().getZipCode().set(zipcodeField.getText());
		currentItem.getOrderClinetHostDto().getPhone().set(phoneField.getText());
		currentItem.getOrderClinetHostDto().getStreet().set(streetField.getText());
		currentItem.getOrderClinetHostDto().getEmail().set(emailField.getText());
		currentItem.getInvitationDocument().getStatus().set(invitationStatus.getValue());
	}

	private void initTableData() {
		usersTable.setItems(getData());

		userNameColumn.setCellValueFactory(value -> value.getValue().getClientName());
		birthDayColumn.setCellValueFactory(value -> value.getValue().getOrderGeneralInfoDto().getBirthDay());
		foringPassportNumberColumn.setCellValueFactory(value -> value.getValue().getOrderPassportDataDto()
				.getForingPassportNumber());
		regionColumn.setCellValueFactory(value -> value.getValue().getOrderClientAddressDto().getRegion());
		registrationDateColumn.setCellValueFactory(value -> value.getValue().getRegistrationDate());
		statusColumn.setCellValueFactory(value -> new SimpleStringProperty(getEnumStringValue(value.getValue()
				.getInvitationDocument().getStatus().get())));
	}

	private String getEnumStringValue(ProcessingStatus processingStatus) {
		return enumBundle.getString(processingStatus.name());
	}

	@FXML
	private TextField clientNameField;
	@FXML
	private TextField invitationPriceField;
	@FXML
	private TextField issuerField;
	@FXML
	private TextField countryField;
	@FXML
	private TextField localityField;
	@FXML
	private TextField zipcodeField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField filterUserName;
	@FXML
	private TextField filterPhoneNumber;
	@FXML
	private ComboBox<ProcessingStatus> invitationStatus;
	@FXML
	private TableView<DetailedOrderDto> usersTable;
	@FXML
	private TableColumn<DetailedOrderDto, String> userNameColumn;
	@FXML
	private TableColumn<DetailedOrderDto, LocalDate> birthDayColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> foringPassportNumberColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> regionColumn;
	@FXML
	private TableColumn<DetailedOrderDto, LocalDate> registrationDateColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> statusColumn;
}
