package com.mmm.grenway.javafx.controller;

import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.controller.helper.TextFieldValidatable;
import com.mmm.grenway.javafx.dto.DocumentDto;
import com.mmm.grenway.javafx.service.DocumentService;

@Component
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	@Autowired
	private ResourceBundle resourceBundle;

	@FXML
	private void initialize() {
		System.out.println("DocumentController");
		initTable();
	}

	private void initTable() {
		docsTable.setItems(documentService.findAll());

		nameColumn.setCellValueFactory(value -> value.getValue().getName());
		nameColumn.prefWidthProperty().bind(docsTable.widthProperty().multiply(0.8));
		priceColumn.setCellValueFactory(value -> value.getValue().getPrice());

		initDeleteColumn();

		nameField.initValidator(resourceBundle.getString("main.tab.admin.doc.name.validation"));
		priceField.initValidator(resourceBundle.getString("main.tab.admin.doc.price.validate"), "^\\d*(\\.\\d{1,2})?",
				resourceBundle.getString("main.tab.admin.doc.price.validateFormat"));
	}

	@FXML
	public void addNewDocument(ActionEvent event) {
		if (nameField.isValid() && priceField.isValid()) {
			DocumentDto documentDto = new DocumentDto();
			documentDto.getName().set(nameField.textProperty().get());
			documentDto.setPrice(new SimpleDoubleProperty(Double.parseDouble(priceField.getText())));
			// TODO: add unique constraint
			documentService.save(documentDto);
			docsTable.getItems().add(documentDto);
			nameField.clear();
			priceField.clear();
		}
	}

	private void initDeleteColumn() {
		deleteColumn.setCellFactory(value -> {
			return new TableCell<DocumentDto, DocumentDto>() {
				@Override
				protected void updateItem(DocumentDto item, boolean empty) {
					if (item != null && !empty) {
						ImageView removeImage = new ImageView(new Image(getClass().getResourceAsStream(
								"/img/Remove-icon.png")));
						removeImage.setFitHeight(20);
						removeImage.setFitWidth(20);
						Button button = new Button("", removeImage);
						button.setPrefSize(20, 20);
						button.setPadding(new Insets(0));
						button.setOnAction(action -> {
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle(resourceBundle.getString("main.tab.admin.doc.comfirm.title"));
							alert.setHeaderText(String.format("%s\n%s",
									resourceBundle.getString("main.tab.admin.doc.comfirm.header"), item.getName().get()));
							alert.setContentText("Are you ok with it?");
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK) {
								documentService.remove(item.getDocumentId().get());
								docsTable.getItems().removeIf(
										p -> p.getDocumentId().get() == item.getDocumentId().get());
							}
						});
						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						setGraphic(button);
					} else {
						setGraphic(null);
					}
				}
			};
		});
		deleteColumn.setCellValueFactory(value -> new SimpleObjectProperty<DocumentDto>(value.getValue()));
	}

	@FXML
	private TableView<DocumentDto> docsTable;
	@FXML
	private TableColumn<DocumentDto, String> nameColumn;
	@FXML
	private TableColumn<DocumentDto, Number> priceColumn;
	@FXML
	private TableColumn<DocumentDto, DocumentDto> deleteColumn;
	@FXML
	private TextFieldValidatable nameField;
	@FXML
	private TextFieldValidatable priceField;

}
