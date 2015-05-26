package com.mmm.grenway.javafx.dto;

import com.mmm.greenway.entity.DocumentPerOrder;
import com.mmm.greenway.entity.ProcessingStatus;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DocumentPerOrderDto {
	private LongProperty id;
	private DocumentDto document;
	private StringProperty processingStatus;

	public DocumentPerOrderDto() {
		id = new SimpleLongProperty();
		document = new DocumentDto();
		processingStatus = new SimpleStringProperty();
	}
	
	public DocumentPerOrderDto(DocumentPerOrder documentPerOrder) {
		id = new SimpleLongProperty(documentPerOrder.getId());
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

	public LongProperty getId() {
		return id;
	}

	public void setId(LongProperty id) {
		this.id = id;
	}
}
