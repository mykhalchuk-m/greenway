package com.mmm.grenway.javafx.dto;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.Document;

public class DocumentDto {
	private LongProperty documentId;
	private StringProperty name;
	private DoubleProperty price;

	public DocumentDto() {
		documentId = new SimpleLongProperty();
		name = new SimpleStringProperty();
		price = new SimpleDoubleProperty();
	}

	public DocumentDto(Document document) {
		documentId = new SimpleLongProperty(document.getId());
		name = new SimpleStringProperty(document.getName());
		price = new SimpleDoubleProperty(document.getPrice());
	}

	public LongProperty getDocumentId() {
		return documentId;
	}

	public void setDocumentId(LongProperty documentId) {
		this.documentId = documentId;
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public DoubleProperty getPrice() {
		return price;
	}

	public void setPrice(DoubleProperty price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return getName().get();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof DocumentDto))
			return false;
		return ((DocumentDto) obj).getDocumentId().get() == this.getDocumentId().get();
	}
}
