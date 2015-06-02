package com.mmm.grenway.javafx.dto;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

import com.mmm.greenway.entity.DocumentPerOrder;
import com.mmm.greenway.entity.ProcessingStatus;

public class DocumentPerOrderDto {
	private LongProperty id;
	private DocumentDto document;
	private ObjectProperty<ProcessingStatus> processingStatus;

	public DocumentPerOrderDto() {
		id = new SimpleLongProperty();
		document = new DocumentDto();
		processingStatus = new SimpleObjectProperty<ProcessingStatus>();
	}
	
	public DocumentPerOrderDto(DocumentPerOrder documentPerOrder) {
		id = new SimpleLongProperty(documentPerOrder.getId());
		document = new DocumentDto(documentPerOrder.getDocument());
		processingStatus = new SimpleObjectProperty<ProcessingStatus>(documentPerOrder.getProcessingStatus());
	}
	
	public DocumentPerOrderDto(DocumentDto documentDto) {
		document = documentDto;
		processingStatus = new SimpleObjectProperty<ProcessingStatus>(ProcessingStatus.NEW);
	}	
	
	public DocumentDto getDocument() {
		return document;
	}

	public void setDocument(DocumentDto document) {
		this.document = document;
	}

	public ObjectProperty<ProcessingStatus> getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(ObjectProperty<ProcessingStatus> processingStatus) {
		this.processingStatus = processingStatus;
	}

	public LongProperty getId() {
		return id;
	}

	public void setId(LongProperty id) {
		this.id = id;
	}
}
