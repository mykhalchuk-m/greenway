package com.mmm.grenway.javafx.controller;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mmm.greenway.entity.ProcessingStatus;
import com.mmm.grenway.javafx.controller.converter.ProcessingStatusConverter;
import com.mmm.grenway.javafx.controller.helper.TextFieldValidatable;
import com.mmm.grenway.javafx.controller.utils.DocumentsUtils;
import com.mmm.grenway.javafx.dto.BaseOrderFilterDto;
import com.mmm.grenway.javafx.dto.DetailedOrderDto;
import com.mmm.grenway.javafx.dto.DocumentDto;
import com.mmm.grenway.javafx.dto.DocumentPerOrderDto;
import com.mmm.grenway.javafx.dto.InvitationDto;
import com.mmm.grenway.javafx.service.DetailedOrderService;
import com.mmm.grenway.javafx.service.DocumentService;

@Component
@Scope("prototype")
public class DocumentolohController {

	@Autowired
	private DocumentService documentService;
	@Autowired
	private DetailedOrderService detailedOrderService;
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private ResourceBundle enumBundle;
	@Autowired
	private RegistrationFormController registrationFormController;
	private DetailedOrderDto currentItem = new DetailedOrderDto();
	private DocumentDto invDocDto = new DocumentDto();
	private BaseOrderFilterDto orderFilterDto = new BaseOrderFilterDto();
	private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	private ScheduledFuture<?> result = null;
	
	@FXML
	private void initialize() {
		System.out.println("DocumentolohController");
		buttonDone.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/task-completed-icon.png"), 30,
				30, true, true)));
		buttonDone.setVisible(false);
		initTableColumns();
		initTableRowDoubleClick();
		initFiltersListeners();

		addDocumentLink.setOnAction(event -> {
			if (allDocuments.isDisable()) {
				allDocuments.setDisable(false);
				allDocuments.setPrefHeight(200);
			} else {
				allDocuments.setDisable(true);
				allDocuments.setPrefHeight(50);
			}
		});
		addDocumentLink.setDisable(true);
		initSelectedDocumentsProcessing();

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
		orderFilterDto.getClientNameFilter().bind(filterClientName.textProperty());
		orderFilterDto.getPhoneNumberFilter().bind(filterPhone.textProperty());
	}

	protected ObservableList<DetailedOrderDto> getData() {
		return detailedOrderService.findDetailedOrdersRegisterd(orderFilterDto);
	}

	@FXML
	private void doSendToInvitationDelivery() {
		if (currentItem.getInvitationDocument() == null
				|| currentItem.getInvitationDocument().getStatus().get().equals(ProcessingStatus.NONE)) {
			populateChangedProperties();
			InvitationDto invitationDocument = new InvitationDto();
			invitationDocument.getTitle().set(resourceBundle.getString("main.tab.admin.doc.invdocname"));
			invitationDocument.getStatus().set(ProcessingStatus.NEW);
			currentItem.setInvitationDocument(invitationDocument);
			doSave();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(resourceBundle.getString("main.tab.documetoloh.warn.title"));
			alert.setHeaderText(resourceBundle.getString("main.tab.documetoloh.warn.header"));
			alert.setContentText(resourceBundle.getString("main.tab.documetoloh.warn.content"));
			alert.showAndWait();
		}
	}

	@FXML
	private void doSendToRegistrator() {
		if (currentItem.getRegistration().get().equals(ProcessingStatus.NONE)) {
			currentItem.getRegistration().set(ProcessingStatus.NEW);
			doSave();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(resourceBundle.getString("main.tab.documetoloh.warn1.title"));
			alert.setHeaderText(resourceBundle.getString("main.tab.documetoloh.warn1.header"));
			alert.setContentText(resourceBundle.getString("main.tab.documetoloh.warn1.content"));
			alert.showAndWait();
		}
	}

	@FXML
	private void doAddDocumentToTheOrder() {
		List<DocumentDto> toRemove = allDocuments.getSelectionModel().getSelectedItems();
		allDocuments.getSelectionModel().getSelectedItems().forEach(e -> {
			currentItem.getDocumentPerOrder().add(new DocumentPerOrderDto(e));
			selecterDocument.getItems().add(e);
		});
		allDocuments.getItems().removeAll(toRemove);
		allDocuments.setDisable(true);
		allDocuments.setPrefHeight(50);
	}

	@FXML
	private void doCancel() {
		currentItem = new DetailedOrderDto();
		supplierField.clear();
		clientName.clear();
		phoneCodes.clear();
		clientPhone.clear();
		operatorField.clear();
		noteArea.clear();

		selecterDocument.getItems().clear();
		allDocuments.setItems(documentService.findAll());
		allDocuments.setDisable(true);
		allDocuments.setPrefHeight(50);
		addDocumentLink.setDisable(true);
	}

	@FXML
	private void doSave() {
		populateChangedProperties();
		DocumentsUtils.calculateDocumentsStatus(currentItem);
		detailedOrderService.save(currentItem);
		doCancel();
		refreshTable();
	}

	@FXML
	private void doEdit(ActionEvent event) {
		if (currentItem.getId().get() != 0) {
			registrationFormController.setOrder(currentItem);
			Scene scene = ((Node) event.getTarget()).getScene();
			TabPane decumentolohTabPane = (TabPane) scene.lookup("#decumentolohTabPane");
			decumentolohTabPane.getSelectionModel().select(1);
			doCancel();
		}
	}

	@FXML
	private void doRemoveDocumentFromOrder() {
		selecterDocument
				.getSelectionModel()
				.getSelectedItems()
				.forEach(
						e -> {
							currentItem.getDocumentPerOrder().removeIf(
									d -> d.getDocument().getDocumentId().get() == e.getDocumentId().get());
							selecterDocument.getItems().remove(e);
							if (e != invDocDto) {
								allDocuments.getItems().add(e);
							}
						});
	}

	@FXML
	private void doDone() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(resourceBundle.getString("main.tab.documetoloh.done.alert.title"));
		alert.setHeaderText(resourceBundle.getString("main.tab.documetoloh.done.alert.header"));
		alert.setContentText(resourceBundle.getString("main.tab.documetoloh.done.alert.content"));
		ButtonType ok = new ButtonType(resourceBundle.getString("common.button.ok"), ButtonData.OK_DONE);
		ButtonType cancel = new ButtonType(resourceBundle.getString("common.button.cancel"), ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(ok, cancel);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get().equals(ok)) {
			currentItem.getIsDone().set(true);
			detailedOrderService.save(currentItem);
			doCancel();
			refreshTable();
		}
	}

	private void populateChangedProperties() {
		currentItem.getSupplierName().set(supplierField.getText());
		currentItem.getClientName().set(clientName.getText());
		currentItem.getOperator().set(operatorField.getText());
		currentItem.getNote().set(noteArea.getText());
		currentItem.getPhoneNumber().set(
				new StringBuilder(phoneCountryCode.getText()).append("(").append(phoneCodes.getText()).append(")")
						.append(clientPhone.getText()).toString());
	}

	private void initSelectedDocumentsProcessing() {
		selecterDocument.setOnMouseClicked(event -> {
			DocumentDto selectedDocument = selecterDocument.getSelectionModel().getSelectedItem();
			if (selectedDocument == null) {
				return;
			}
			DocumentPerOrderDto selectedDocumentPerOrder = null;
			ProcessingStatus processingStatus = null;
			if (currentItem.getDocumentPerOrder() != null && currentItem.getDocumentPerOrder().size() != 0) {
				FilteredList<DocumentPerOrderDto> fl = currentItem.getDocumentPerOrder().filtered(
						p -> p.getDocument().getDocumentId() == selectedDocument.getDocumentId());
				if (fl.size() != 0) {
					selectedDocumentPerOrder = fl.get(0);
				}
			}
			if (selectedDocumentPerOrder != null) {
				processingStatus = currentItem.getDocumentPerOrder()
						.filtered(p -> p.getDocument().getDocumentId() == selectedDocument.getDocumentId()).get(0)
						.getProcessingStatus().get();
			} else {
				processingStatus = currentItem.getInvitationDocument().getStatus().get();
			}
			if (event.getClickCount() == 2 && selectedDocument != null) {
				Dialog<Pair<DocumentDto, ProcessingStatus>> dialog = new Dialog<>();
				dialog.setTitle(resourceBundle.getString("main.tab.documetoloh.docstatus.title"));
				dialog.setHeaderText(resourceBundle.getString("main.tab.documetoloh.docstatus.header"));
				ButtonType changeButtonType = new ButtonType("Change", ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().addAll(changeButtonType, ButtonType.CANCEL);

				GridPane dialogContent = new GridPane();
				dialogContent.setVgap(10);
				dialogContent.setHgap(10);
				dialogContent.setPadding(new Insets(20, 20, 10, 10));

				TextField docName = new TextField(selectedDocument.getName().get());
				docName.setEditable(false);
				docName.setPrefWidth(400);
				dialogContent.add(new Label(resourceBundle.getString("main.tab.documetoloh.docstatus.lable.docname")),
						0, 0);
				dialogContent.add(docName, 1, 0);

				dialogContent.add(
						new Label(resourceBundle.getString("main.tab.documetoloh.docstatus.lable.docstatus")), 0, 1);
				if (selectedDocumentPerOrder != null) {
					ComboBox<ProcessingStatus> processingStatusNode = new ComboBox<>(FXCollections
							.observableArrayList(ProcessingStatus.values()));
					processingStatusNode.setVisibleRowCount(ProcessingStatus.values().length + 1);
					processingStatusNode.setConverter(new ProcessingStatusConverter(enumBundle));
					processingStatusNode.getSelectionModel().select(processingStatus);
					dialogContent.add(processingStatusNode, 1, 1);

					dialog.setResultConverter(dialogButton -> {
						if (dialogButton == changeButtonType) {
							return new Pair<DocumentDto, ProcessingStatus>(selectedDocument, processingStatusNode
									.getSelectionModel().getSelectedItem());
						}
						return null;
					});
				} else {
					TextField processingStatusNode = new TextField(enumBundle.getString(processingStatus.name()));
					processingStatusNode.setEditable(false);
					dialogContent.add(processingStatusNode, 1, 1);
					dialog.setResultConverter(dialogButton -> {
						return null;
					});
				}

				dialog.getDialogPane().setContent(dialogContent);

				Optional<Pair<DocumentDto, ProcessingStatus>> result = dialog.showAndWait();

				if (result.isPresent()) {
					Pair<DocumentDto, ProcessingStatus> res = result.get();
					if (res.getValue().equals(ProcessingStatus.NONE)) {
						DocumentDto selectedDoc = res.getKey();
						currentItem.getDocumentPerOrder().removeIf(
								d -> d.getDocument().getDocumentId().get() == selectedDoc.getDocumentId().get());
						selecterDocument.getItems().remove(selectedDoc);
						if (selectedDoc != invDocDto) {
							allDocuments.getItems().add(selectedDoc);
						}
					}
					selectedDocumentPerOrder.getProcessingStatus().set(res.getValue());
				}
			}
		});
	}

	public void refreshTable() {
		baseOrderTab.setItems(getData());
		
		allDocuments.setItems(documentService.findAll());
		allDocuments.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		allDocuments.setDisable(true);
	}

	private void initTableColumns() {
		clientNameColumn.setCellValueFactory(value -> value.getValue().getClientName());
		clientNameColumn.setSortable(false);
		clientPhoneColumn.setCellValueFactory(value -> value.getValue().getPhoneNumber());
		clientPhoneColumn.setSortable(false);
		supplierColumn.setCellValueFactory(value -> value.getValue().getSupplierName());
		supplierColumn.setSortable(false);
		dateColumn.setCellValueFactory(value -> value.getValue().getDate());
		dateColumn.setSortable(false);
		documentsColumn.setCellValueFactory(value -> new SimpleStringProperty(enumBundle.getString(value.getValue()
				.getDocumnentsStatus().get().name())));
		documentsColumn.setSortable(false);
		registrationColumn.setCellValueFactory(value -> new SimpleStringProperty(enumBundle.getString(value.getValue()
				.getRegistration().get().name())));
		registrationColumn.setSortable(false);
		operatorColumn.setCellValueFactory(value -> value.getValue().getOperator());
		operatorColumn.setSortable(false);
		noteColumn.setCellValueFactory(value -> value.getValue().getNote());
		noteColumn.setSortable(false);
	}

	private void initFiltersListeners() {
		filterClientName.textProperty().addListener((ob, ov, nv) -> {
			if (result != null) {
				result.cancel(false);
			}
			result = scheduler.schedule(new Runnable() {

				@Override
				public void run() {
					baseOrderTab.setItems(getData());
				}
			}, 1, TimeUnit.SECONDS);

		});
		
		filterPhone.textProperty().addListener((ob, ov, nv) -> {
			if (result != null) {
				result.cancel(false);
			}
			result = scheduler.schedule(new Runnable() {

				@Override
				public void run() {
					baseOrderTab.setItems(getData());
				}
			}, 1, TimeUnit.SECONDS);
		});;
	}

	private void initTableRowDoubleClick() {
		baseOrderTab.setRowFactory(tr -> {
			TableRow<DetailedOrderDto> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && !row.isEmpty()) {
					currentItem = row.getItem();
					supplierField.setText(currentItem.getSupplierName().get());
					clientName.setText(currentItem.getClientName().get());
					phoneCodes.setText(currentItem.getPhoneNumber().get().substring(4, 7));
					clientPhone.setText(currentItem.getPhoneNumber().get().substring(8));
					operatorField.setText(currentItem.getOperator().get());
					noteArea.setText(currentItem.getNote().get());

					ObservableList<DocumentDto> currentItemDocs = FXCollections.observableArrayList();
					currentItem.getDocumentPerOrder().forEach(d -> currentItemDocs.add(d.getDocument()));
					selecterDocument.setItems(currentItemDocs);
					if (currentItem.isInvitationPresent()) {
						invDocDto.getName().bind(currentItem.getInvitationDocument().getTitle());
						selecterDocument.getItems().add(invDocDto);
					}
					allDocuments.getItems().removeAll(currentItemDocs);
					addDocumentLink.setDisable(false);
					shouldShowDoneButton();
				}
			});
			return row;
		});
	}

	private void shouldShowDoneButton() {
		if (ProcessingStatus.DONE.equals(currentItem.getDocumnentsStatus().get())
				&& ProcessingStatus.DONE.equals(currentItem.getRegistration().get())) {
			buttonDone.setVisible(true);
		}
	}

	@FXML
	protected TextFieldValidatable supplierField;
	@FXML
	protected TextFieldValidatable clientName;
	@FXML
	private TextField phoneCountryCode;
	@FXML
	protected TextFieldValidatable phoneCodes;
	@FXML
	protected TextFieldValidatable clientPhone;
	@FXML
	protected TextField operatorField;
	@FXML
	protected TextArea noteArea;

	@FXML
	protected TextField filterClientName;
	@FXML
	protected TextField filterPhone;
	@FXML
	protected TableView<DetailedOrderDto> baseOrderTab;
	@FXML
	protected TableColumn<DetailedOrderDto, String> clientNameColumn;
	@FXML
	protected TableColumn<DetailedOrderDto, String> clientPhoneColumn;
	@FXML
	protected TableColumn<DetailedOrderDto, String> supplierColumn;
	@FXML
	protected TableColumn<DetailedOrderDto, String> dateColumn;
	@FXML
	protected TableColumn<DetailedOrderDto, String> documentsColumn;
	@FXML
	protected TableColumn<DetailedOrderDto, String> registrationColumn;
	@FXML
	protected TableColumn<DetailedOrderDto, String> operatorColumn;
	@FXML
	protected TableColumn<DetailedOrderDto, String> noteColumn;

	@FXML
	private ListView<DocumentDto> allDocuments;
	@FXML
	private ListView<DocumentDto> selecterDocument;
	@FXML
	private Hyperlink addDocumentLink;
	@FXML
	private Button buttonDone;
}
