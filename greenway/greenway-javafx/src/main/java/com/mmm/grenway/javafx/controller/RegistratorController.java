package com.mmm.grenway.javafx.controller;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.greenway.entity.ProcessingStatus;
import com.mmm.grenway.javafx.controller.converter.ProcessingStatusConverter;
import com.mmm.grenway.javafx.dto.BaseOrderFilterDto;
import com.mmm.grenway.javafx.dto.DetailedOrderDto;
import com.mmm.grenway.javafx.service.DetailedOrderService;
import com.mmm.grenway.javafx.util.DateUtil;

@Component
public class RegistratorController {

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
		System.out.println("RegistratorController");
		initTableColumns();
		initFiltersListeners();
		initTableRowDoubleClick();
		registrationStatus.setItems(FXCollections.observableArrayList(ProcessingStatus.values()));
		registrationStatus.getSelectionModel().select(0);
		registrationStatus.setConverter(new ProcessingStatusConverter(enumBundle));
		userName.setEditable(false);
	}

	@FXML
	private void doSave() {
		populateChangedProperties();
		detailedOrderService.save(currentItem);
		doCancel();
	}

	@FXML
	private void doCancel() {
		userName.clear();
		registrationDate.getEditor().clear();
		registrationStatus.getSelectionModel().select(0);
	}

	private void initFiltersListeners() {
		clientNameFilter.textProperty().addListener((ob, ov, nv) -> {
			clientsTable.setItems(getData());
		});

		phoneFilter.textProperty().addListener((ob, ov, nv) -> {
			clientsTable.setItems(getData());
		});
	}

	private void initTableColumns() {
		clientsTable.setItems(getData());

		clientNameColumn.setSortable(false);
		clientNameColumn.setCellValueFactory(value -> value.getValue().getClientName());
		bithdayColumn.setSortable(false);
		bithdayColumn.setCellValueFactory(value -> new SimpleStringProperty(DateUtil.format(value.getValue()
				.getOrderGeneralInfoDto().getBirthDay().get())));
		phoneColumn.setSortable(false);
		phoneColumn.setCellValueFactory(value -> value.getValue().getPhoneNumber());
		registrationStatusColumn.setSortable(false);
		registrationStatusColumn.setCellValueFactory(value -> new SimpleStringProperty(enumBundle.getString(value
				.getValue().getRegistration().get().name())));
		passportExpDateColumn.setSortable(false);
		passportExpDateColumn.setCellValueFactory(value -> new SimpleStringProperty(DateUtil.format(value.getValue()
				.getOrderPassportDataDto().getForingPassportExpDate().get())));
		passportNumberColumn.setSortable(false);
		passportNumberColumn.setCellValueFactory(value -> value.getValue().getOrderPassportDataDto()
				.getForingPassportNumber());
		dateProvidingColumn.setSortable(false);
		startDateColumn.setSortable(false);
		startDateColumn.setCellValueFactory(value -> new SimpleStringProperty(DateUtil.format(value.getValue()
				.getOrderGeneralInfoDto().getStartDocsProviding().get())));
		endDateColumn.setSortable(false);
		endDateColumn.setCellValueFactory(value -> new SimpleStringProperty(DateUtil.format(value.getValue()
				.getOrderGeneralInfoDto().getEndDateProviding().get())));
	}

	private void initTableRowDoubleClick() {
		clientsTable.setRowFactory(tr -> {
			TableRow<DetailedOrderDto> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && !row.isEmpty()) {
					currentItem = row.getItem();
					userName.setText(currentItem.getClientName().get());
					registrationDate.setValue(currentItem.getRegistrationDate().get());
					registrationStatus.setValue(currentItem.getRegistration().get());
				}
			});
			return row;
		});
	}

	private void populateChangedProperties() {
		ProcessingStatus processingStatus = registrationStatus.getValue();
		currentItem.getRegistration().set(processingStatus != null ? processingStatus : ProcessingStatus.NONE);
		currentItem.getRegistrationDate().set(registrationDate.getValue());
	}

	private ObservableList<DetailedOrderDto> getData() {
		return detailedOrderService.findDetailedOrdersForRegistrator(orderFilterDto);
	}

	@FXML
	private TextField clientNameFilter;
	@FXML
	private TextField phoneFilter;
	@FXML
	private TableView<DetailedOrderDto> clientsTable;
	@FXML
	private TableColumn<DetailedOrderDto, String> clientNameColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> bithdayColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> phoneColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> registrationStatusColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> passportExpDateColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> passportNumberColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> dateProvidingColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> startDateColumn;
	@FXML
	private TableColumn<DetailedOrderDto, String> endDateColumn;
	@FXML
	private TextField userName;
	@FXML
	private DatePicker registrationDate;
	@FXML
	private ComboBox<ProcessingStatus> registrationStatus;
}
