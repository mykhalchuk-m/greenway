package com.mmm.grenway.javafx.service;

import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmm.greenway.data.repository.DocumentRepository;
import com.mmm.greenway.entity.Document;
import com.mmm.grenway.javafx.dto.DocumentDto;
import com.mmm.grenway.javafx.service.converter.DocumentConverter;

@Service
public class DocumentService {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	public ObservableList<DocumentDto> findAll() {
		return DocumentConverter.toDocumentDtoList(documentRepository.findAll());
	}
	
	public void save(DocumentDto documentDto) {
		Document document = new Document();
		document.setName(documentDto.getName().get());
		document.setPrice(documentDto.getPrice().get());
		documentRepository.save(document);
	}
	
	public void remove(Long id) {
		documentRepository.delete(id);
	}
}
