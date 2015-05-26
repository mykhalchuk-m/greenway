package com.mmm.grenway.javafx.controller;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

		initRowContextMenu();
		
//		nameField.initValidator(resourceBundle.getString("main.tab.admin.doc.name.validation"));
//		priceField.initValidator(resourceBundle.getString("main.tab.admin.doc.price.validate"), "^\\d*(\\.\\d{1,2})?",
//				resourceBundle.getString("main.tab.admin.doc.price.validateFormat"));
	}

	@FXML
	public void addNewDocument(ActionEvent event) {
//		if (nameField.isValid() && priceField.isValid()) {
			DocumentDto documentDto = new DocumentDto();
			documentDto.getName().set(nameField.textProperty().get());
			documentDto.setPrice(new SimpleDoubleProperty(Double.parseDouble(priceField.getText())));
			// TODO: add unique constraint
			documentService.save(documentDto);
			docsTable.getItems().add(documentDto);
			nameField.clear();
			priceField.clear();
//		}
	}
	
	private void initRowContextMenu() {
	}

	@FXML
	private TableView<DocumentDto> docsTable;
	@FXML
	private TableColumn<DocumentDto, String> nameColumn;
	@FXML
	private TableColumn<DocumentDto, Number> priceColumn;
//	@FXML
//	private TextFieldValidatable nameField;
//	@FXML
//	private TextFieldValidatable priceField;
	@FXML
	private TextField nameField;
	@FXML
	private TextField priceField;

}
