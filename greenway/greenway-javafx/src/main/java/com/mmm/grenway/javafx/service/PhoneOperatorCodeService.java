package com.mmm.grenway.javafx.service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmm.greenway.data.repository.PhoneOperatorCodeRepository;

@Service
public class PhoneOperatorCodeService {
	@Autowired
	private PhoneOperatorCodeRepository codeRepository;

	public ObservableList<String> getPhoneOperatorCode() {
		return FXCollections.observableArrayList(StreamSupport.stream(codeRepository.findAll().spliterator(), false)
				.map(p -> p.getCode()).collect(Collectors.toList()));
	}
}
