package com.mmm.grenway.javafx.service.converter;

import com.mmm.greenway.entity.Document;
import com.mmm.grenway.javafx.dto.DocumentDto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DocumentConverter {
	public static ObservableList<DocumentDto> toDocumentDtoList(Iterable<Document> documents) {
		ObservableList<DocumentDto> result = FXCollections.observableArrayList();
		documents.forEach(d -> result.add(new DocumentDto(d)));
		return result;
	}
	
	public static Document toDocument(DocumentDto documentDto) {
		Document document = new Document();
		document.setId(documentDto.getDocumentId().get());
		document.setName(documentDto.getName().get());
		document.setPrice(documentDto.getPrice().get());
		return document;
	}
}
