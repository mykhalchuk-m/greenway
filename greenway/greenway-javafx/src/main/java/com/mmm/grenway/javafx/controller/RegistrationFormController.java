package com.mmm.grenway.javafx.controller;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mmm.greenway.entity.OrderType;
import com.mmm.grenway.javafx.controller.helper.DatePickerCellFactory;
import com.mmm.grenway.javafx.dto.BaseOrderDto;
import com.mmm.grenway.javafx.dto.DateIntervalDto;
import com.mmm.grenway.javafx.dto.DetailedOrderDto;
import com.mmm.grenway.javafx.service.BaseOrderService;
import com.mmm.grenway.javafx.service.DetailedOrderService;
import com.mmm.grenway.javafx.service.converter.BaseOrderConverter;

@Component
public class RegistrationFormController {

	@Autowired
	private DetailedOrderService detailedOrderService;
	@Autowired
	private BaseOrderService baseOrderService;
	@Autowired
	private ResourceBundle resourceBundle;

	private DetailedOrderDto detailedOrderDto = new DetailedOrderDto();

	@FXML
	private void initialize() {
		System.out.println("RegistrationFormController");

		preparePropertiesBindings();
		initDynamicPrevVisasFieldAdding();

		operator.textProperty().set(
				((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		date.valueProperty().set(LocalDate.now());

		sexmail.selectedProperty().addListener((obv, ov, nw) -> {
			if (nw && sexmail.getUserData() != null) {
				detailedOrderDto.getOrderGeneralInfoDto().getSex().set(sexmail.getUserData().toString());
			}
		});
		sexfemail.selectedProperty().addListener((obv, ov, nw) -> {
			if (nw && sexfemail.getUserData() != null) {
				detailedOrderDto.getOrderGeneralInfoDto().getSex().set(sexfemail.getUserData().toString());
			}
		});

		startDateProviding.setDayCellFactory(new DatePickerCellFactory(endDateProviding, false));
		endDateProviding.setDayCellFactory(new DatePickerCellFactory(startDateProviding, true));

		prevVisaFrom0.setDayCellFactory(new DatePickerCellFactory(prevVisaTo0, false));
		prevVisaTo0.setDayCellFactory(new DatePickerCellFactory(prevVisaFrom0, true));
	}

	public void initOperatorButtonBar() {
		if (!detailedOrderDto.getIsDone().get()) {
			HBox hBox = new HBox(10);
			hBox.setPadding(new Insets(20, 0, 0, 0));
			hBox.setAlignment(Pos.TOP_RIGHT);

			Button saveConsulting = new Button(
					resourceBundle.getString("main.tab.operator.reg.form.button.saveConsulting"));
			saveConsulting.setOnAction(v -> doSaveConsulting(v));

			Button saveRegistration = new Button(resourceBundle.getString("main.tab.operator.reg.form.button.save"));
			saveRegistration.setOnAction(v -> doSaveRegistration(v));

			hBox.getChildren().addAll(saveRegistration, saveConsulting);
			root.setBottom(hBox);
		}
	}

	public void initDocumentolohButtonBar() {
		if (!detailedOrderDto.getIsDone().get()) {
			HBox hBox = new HBox(10);
			hBox.setPadding(new Insets(20, 0, 0, 0));
			hBox.setAlignment(Pos.TOP_RIGHT);

			Button save = new Button(resourceBundle.getString("main.tab.documetoloh.edit"));
			save.setOnAction(v -> doSaveConsulting(v));

			Button cancel = new Button(resourceBundle.getString("main.tab.documetoloh.cancel"));
			cancel.setOnAction(v -> doSaveConsulting(v));

			hBox.getChildren().addAll(save, cancel);
			root.setBottom(hBox);
		}
	}

	// TODO: change it to use DTO
	public void setOrder(BaseOrderDto baseOrder) {
		if (baseOrder.getId().get() != 0) {
			detailedOrderService.findById(baseOrder.getId().get(), detailedOrderDto);
			populatePrevVisasFields(detailedOrderDto.getPreviousVisasDates());
		} else {
			detailedOrderDto.getClientName().set(baseOrder.getClientName().get());
			detailedOrderDto.getSupplierName().set(baseOrder.getSupplierName().get());
			detailedOrderDto.getPhoneNumber().set(baseOrder.getPhoneNumber().get());
			detailedOrderDto.getNote().set(baseOrder.getNote().get());
		}
	}

	public void clearForm() {
		doCancel();
	}

	private void preparePropertiesBindings() {
		bindBaseOrder();
		bindOrderGeneralInfo();
		bindOrderPassportData();
		bindOrderClientAddress();
		bindOrderClientWorkingPlace();
		bindOrderClinetHost();
		bindTravelData();
	}

	private void bindTravelData() {
		detailedOrderDto.getTravelPurpose().bindBidirectional(travelPurpose.textProperty());
		detailedOrderDto.getSuitableInOutDatesDto().getFrom().bindBidirectional(sutableOutDate.valueProperty());
		detailedOrderDto.getSuitableInOutDatesDto().getTo().bindBidirectional(sutableInDate.valueProperty());
	}

	private void bindOrderClinetHost() {
		detailedOrderDto.getOrderClinetHostDto().getCountry().bindBidirectional(hostCountry.textProperty());
		detailedOrderDto.getOrderClinetHostDto().getEmail().bindBidirectional(hostEmail.textProperty());
		detailedOrderDto.getOrderClinetHostDto().getHostData().bindBidirectional(hostData.textProperty());
		detailedOrderDto.getOrderClinetHostDto().getLocality().bindBidirectional(hostLocality.textProperty());
		detailedOrderDto.getOrderClinetHostDto().getPhone().bindBidirectional(hostPhone.textProperty());
		detailedOrderDto.getOrderClinetHostDto().getStreet().bindBidirectional(hostStreet.textProperty());
		detailedOrderDto.getOrderClinetHostDto().getZipCode().bindBidirectional(hostZipCode.textProperty());
	}

	private void bindOrderClientWorkingPlace() {
		detailedOrderDto.getOrderClientWorkingPlaceDto().getCountry()
				.bindBidirectional(workPlaceCountry.textProperty());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getDistrict()
				.bindBidirectional(workPlaceDistrict.textProperty());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getEmail().bindBidirectional(workPlaceEmail.textProperty());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getInstructureName()
				.bindBidirectional(workPlaceInstructureName.textProperty());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getLocality()
				.bindBidirectional(workPlaceLocality.textProperty());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getPhoneNumber()
				.bindBidirectional(workPlacePhoneNumber.textProperty());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getProfession()
				.bindBidirectional(workPlaceProfession.textProperty());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getRegion().bindBidirectional(workPlaceRegion.textProperty());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getStreet().bindBidirectional(workPlaceStreet.textProperty());
		detailedOrderDto.getOrderClientWorkingPlaceDto().getZipCode()
				.bindBidirectional(workPlaceZipCode.textProperty());
	}

	private void bindOrderClientAddress() {
		detailedOrderDto.getOrderClientAddressDto().getCountry().bindBidirectional(clientCountry.textProperty());
		detailedOrderDto.getOrderClientAddressDto().getDistrict().bindBidirectional(clientDistrict.textProperty());
		detailedOrderDto.getOrderClientAddressDto().getEmail().bindBidirectional(clientEmail.textProperty());
		detailedOrderDto.getOrderClientAddressDto().getLocality().bindBidirectional(clientLocality.textProperty());
		detailedOrderDto.getOrderClientAddressDto().getOrderReceiverPhoneNumber()
				.bindBidirectional(orderReceiverPhoneNumber.textProperty());
		detailedOrderDto.getOrderClientAddressDto().getRegion().bindBidirectional(clientRegion.textProperty());
		detailedOrderDto.getOrderClientAddressDto().getStreet().bindBidirectional(clientStreet.textProperty());
		detailedOrderDto.getOrderClientAddressDto().getZipCode().bindBidirectional(clientZipCode.textProperty());
	}

	private void bindOrderPassportData() {
		detailedOrderDto.getOrderPassportDataDto().getSocialId().bindBidirectional(socialId.textProperty());
		detailedOrderDto.getOrderPassportDataDto().getForingPassportExpDate()
				.bindBidirectional(foringPassportExpDate.valueProperty());
		detailedOrderDto.getOrderPassportDataDto().getForingPassportIssuedBy()
				.bindBidirectional(foringPassportIssuedBy.textProperty());
		detailedOrderDto.getOrderPassportDataDto().getForingPassportNumber()
				.bindBidirectional(foringPassportNumber.textProperty());
		detailedOrderDto.getOrderPassportDataDto().getForingPassportStartDate()
				.bindBidirectional(foringPassportStartDate.valueProperty());
	}

	private void bindBaseOrder() {
		supplier.textProperty().bindBidirectional(detailedOrderDto.getSupplierName());
		clientName.textProperty().bindBidirectional(detailedOrderDto.getClientName());
		clientPhone.textProperty().bindBidirectional(detailedOrderDto.getPhoneNumber());
		operator.textProperty().bindBidirectional(detailedOrderDto.getOperator());
		note.textProperty().bindBidirectional(detailedOrderDto.getNote());
	}

	private void bindOrderGeneralInfo() {
		detailedOrderDto.getOrderGeneralInfoDto().getBirthCountry().bindBidirectional(birthcountry.textProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getBirthDay().bindBidirectional(birthday.valueProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getBirthNationality()
				.bindBidirectional(birthnationality.textProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getBirthPlace().bindBidirectional(birthplace.textProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getCurrentNationality()
				.bindBidirectional(currentnationality.textProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getDestinationCountry()
				.bindBidirectional(destinationCounty.textProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getEndDateProviding()
				.bindBidirectional(endDateProviding.valueProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getIsReferenceAvailable()
				.bindBidirectional(isrefavailable.selectedProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getMartialStatus().bindBidirectional(maritalstatus.textProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getMaidenName().bindBidirectional(maidenname.textProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getSecondNameInLatin().bindBidirectional(secondname.textProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getStartDocsProviding()
				.bindBidirectional(startDateProviding.valueProperty());
		detailedOrderDto.getOrderGeneralInfoDto().getVisaType().bindBidirectional(visaType.textProperty());
		String sexValue = detailedOrderDto.getOrderGeneralInfoDto().getSex().get();
		if (sexValue != null && !sexValue.isEmpty()) {
			if (sexmail.getUserData().equals(sexValue)) {
				sexmail.setSelected(true);
			} else if (sexfemail.getUserData().equals(sexValue)) {
				sexfemail.setSelected(true);
			}
		}
	}

	@FXML
	private void doSaveRegistration(ActionEvent event) {
		detailedOrderDto.getOrderType().set(OrderType.REGISTER);
		readPrevVisasFields();
		detailedOrderService.save(detailedOrderDto);
		clearAndGoToListPane(event);
	}

	@FXML
	private void doSaveConsulting(ActionEvent event) {
		detailedOrderDto.getOrderType().set(OrderType.CONSULT);
		baseOrderService.save(BaseOrderConverter.getBaseOrderDto(detailedOrderDto));
		clearAndGoToListPane(event);
	}

	private void clearAndGoToListPane(ActionEvent event) {
		// clear form
		doCancel();
		initCurentUser();

		// go to list pane
		Scene scene = ((Node) event.getTarget()).getScene();
		TabPane operatorTabPane = (TabPane) scene.lookup("#operatorTabPane");
		operatorTabPane.getSelectionModel().select(0);
	}

	private void initCurentUser() {
		operator.setText(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername());
	}

	private void doCancel() {
		detailedOrderDto.getId().setValue(null);
		prevVisasBox.getChildren().clear();
		prevVisasBox.getChildren().add(createFromDateContent(0, null));

		for (Field field : getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(FXML.class)) {
				field.setAccessible(true);
				try {
					if (field.getType().equals(TextField.class)) {
						TextField textField = (TextField) field.get(this);
						textField.setText("");
					} else if (field.getType().equals(DatePicker.class)) {
						DatePicker datePicker = (DatePicker) field.get(this);
						datePicker.setValue(null);
					} else if (field.getType().equals(CheckBox.class)) {
						CheckBox checkBox = (CheckBox) field.get(this);
						checkBox.setSelected(false);
					} else if (field.getType().equals(TextArea.class)) {
						TextArea textArea = (TextArea) field.get(this);
						textArea.setText("");
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void readPrevVisasFields() {
		ObservableList<Node> rows = prevVisasBox.getChildren();
		IntStream.range(0, rows.size()).forEach(idx -> {
			DatePicker dateFrom = (DatePicker) rows.get(idx).lookup("#prevVisaFrom" + idx);
			DatePicker dateTo = (DatePicker) rows.get(idx).lookup("#prevVisaTo" + idx);

			if (dateFrom != null && dateTo != null && dateFrom.getValue() != null && dateTo.getValue() != null) {
				DateIntervalDto dateIntervalDto = new DateIntervalDto();
				dateIntervalDto.getFrom().set(dateFrom.getValue());
				dateIntervalDto.getTo().set(dateTo.getValue());
				detailedOrderDto.getPreviousVisasDates().add(dateIntervalDto);
			}
		});
	}

	private void populatePrevVisasFields(ObservableList<DateIntervalDto> prevVisas) {
		if (prevVisas == null || prevVisas.size() == 0) {
			return;
		}
		// Clear one existed row
		prevVisasBox.getChildren().remove(0);

		IntStream.range(0, prevVisas.size()).forEach(idx -> {
			prevVisasBox.getChildren().add(createFromDateContent(idx, prevVisas.get(idx)));
		});
	}

	private void initDynamicPrevVisasFieldAdding() {
		int prevVisasCount = prevVisasBox.getChildren().size();
		if (prevVisasCount > 0) {
			HBox rowElement = (HBox) prevVisasBox.getChildren().get(prevVisasCount - 1);
			DatePicker dateFrom = (DatePicker) rowElement.lookup("#prevVisaFrom" + (prevVisasCount - 1));
			dateFrom.valueProperty().addListener((ob, ov, nv) -> {
				if (nv != null && prevVisasBox.lookup("#prevVisaFrom" + prevVisasCount) == null) {
					prevVisasBox.getChildren().add(createFromDateContent(prevVisasCount, null));
				}
			});
			DatePicker dateTo = (DatePicker) rowElement.lookup("#prevVisaTo" + (prevVisasCount - 1));
			dateTo.valueProperty().addListener((ob, ov, nv) -> {
				if (nv != null && prevVisasBox.lookup("#prevVisaTo" + prevVisasCount) == null) {
					prevVisasBox.getChildren().add(createFromDateContent(prevVisasCount, null));
				}
			});
		}
	}

	private Node createFromDateContent(int index, DateIntervalDto dateIntervalDto) {
		HBox hBox = new HBox(10);
		Label labelFrom = new Label(resourceBundle.getString("main.tab.operator.reg.form.prevVisaFrom"));
		DatePicker dateFrom = new DatePicker();
		dateFrom.setId("prevVisaFrom" + index);
		if (dateIntervalDto != null) {
			dateFrom.setValue(dateIntervalDto.getFrom().get());
		}
		dateFrom.valueProperty().addListener((ob, ov, nv) -> {
			if (nv != null && prevVisasBox.lookup("#prevVisaFrom" + (index + 1)) == null) {
				prevVisasBox.getChildren().add(createFromDateContent(index + 1, null));
			}
		});
		Label labelTo = new Label(resourceBundle.getString("main.tab.operator.reg.form.prevVisaTo"));
		DatePicker dateTo = new DatePicker();
		dateTo.setId("prevVisaTo" + index);
		if (dateIntervalDto != null) {
			dateTo.setValue(dateIntervalDto.getTo().get());
		}
		dateTo.valueProperty().addListener((ob, ov, nv) -> {
			if (nv != null && prevVisasBox.lookup("#prevVisaTo" + (index + 1)) == null) {
				prevVisasBox.getChildren().add(createFromDateContent(index + 1, null));
			}
		});
		dateFrom.setDayCellFactory(new DatePickerCellFactory(dateTo, false));
		dateTo.setDayCellFactory(new DatePickerCellFactory(dateFrom, true));

		hBox.getChildren().add(0, labelFrom);
		hBox.getChildren().add(1, dateFrom);
		hBox.getChildren().add(2, labelTo);
		hBox.getChildren().add(3, dateTo);
		hBox.setAlignment(Pos.CENTER_LEFT);
		return hBox;
	}

	@FXML
	private BorderPane root;

	@FXML
	private TextField supplier;
	@FXML
	private TextField clientName;
	@FXML
	private TextField clientPhone;
	@FXML
	private TextField operator;
	@FXML
	private TextArea note;
	@FXML
	private DatePicker date;

	@FXML
	private TextField destinationCounty;
	@FXML
	private TextField visaType;
	@FXML
	private CheckBox isrefavailable;
	@FXML
	private TextField cost;
	@FXML
	private TextField secondname;
	@FXML
	private TextField maidenname;
	@FXML
	private DatePicker birthday;
	@FXML
	private TextField birthplace;
	@FXML
	private TextField birthcountry;
	@FXML
	private TextField currentnationality;
	@FXML
	private TextField birthnationality;
	@FXML
	private RadioButton sexmail;
	@FXML
	private RadioButton sexfemail;
	@FXML
	private TextField maritalstatus;
	@FXML
	private DatePicker endDateProviding;
	@FXML
	private DatePicker startDateProviding;
	@FXML
	private TextField socialId;
	@FXML
	private TextField foringPassportNumber;
	@FXML
	private DatePicker foringPassportStartDate;
	@FXML
	private DatePicker foringPassportExpDate;
	@FXML
	private TextField foringPassportIssuedBy;
	@FXML
	private TextField clientCountry;
	@FXML
	private TextField clientRegion;
	@FXML
	private TextField clientDistrict;
	@FXML
	private TextField clientLocality;
	@FXML
	private TextField clientZipCode;
	@FXML
	private TextField clientStreet;
	@FXML
	private TextField clientEmail;
	@FXML
	private TextField orderReceiverPhoneNumber;
	@FXML
	private TextField workPlaceProfession;
	@FXML
	private TextField workPlaceCountry;
	@FXML
	private TextField workPlaceRegion;
	@FXML
	private TextField workPlaceDistrict;
	@FXML
	private TextField workPlaceLocality;
	@FXML
	private TextField workPlaceZipCode;
	@FXML
	private TextField workPlaceStreet;
	@FXML
	private TextField workPlacePhoneNumber;
	@FXML
	private TextField workPlaceInstructureName;
	@FXML
	private TextField workPlaceEmail;
	@FXML
	private TextField hostData;
	@FXML
	private TextField hostCountry;
	@FXML
	private TextField hostLocality;
	@FXML
	private TextField hostZipCode;
	@FXML
	private TextField hostPhone;
	@FXML
	private TextField hostStreet;
	@FXML
	private TextField hostEmail;
	@FXML
	private TextField travelPurpose;
	@FXML
	private DatePicker sutableInDate;
	@FXML
	private DatePicker sutableOutDate;
	@FXML
	private DatePicker prevVisaFrom0;
	@FXML
	private DatePicker prevVisaTo0;

	@FXML
	private VBox prevVisasBox;
}
