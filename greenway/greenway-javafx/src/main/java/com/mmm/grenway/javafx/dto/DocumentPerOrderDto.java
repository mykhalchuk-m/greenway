package com.mmm.grenway.javafx.dto;

import com.mmm.greenway.entity.DocumentPerOrder;
import com.mmm.greenway.entity.ProcessingStatus;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DocumentPerOrderDto {
	private DocumentDto document;
	private StringProperty processingStatus;

	public DocumentPerOrderDto() {
		document = new DocumentDto();
		processingStatus = new SimpleStringProperty();
	}
	
	public DocumentPerOrderDto(DocumentPerOrder documentPerOrder) {
		document = new DocumentDto(documentPerOrder.getDocument());
		processingStatus = new SimpleStringProperty(documentPerOrder.getProcessingStatus().name());
	}
	
	public DocumentPerOrderDto(DocumentDto documentDto) {
		document = documentDto;
		processingStatus = new SimpleStringProperty(ProcessingStatus.NEW.name());
	}	
	
	public DocumentDto getDocument() {
		return document;
	}

	public void setDocument(DocumentDto document) {
		this.document = document;
	}

	public StringProperty getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(StringProperty processingStatus) {
		this.processingStatus = processingStatus;
	}
}
