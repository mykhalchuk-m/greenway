package com.mmm.grenway.javafx.controller.converter;

import java.util.Collections;
import java.util.ResourceBundle;

import com.mmm.greenway.entity.ProcessingStatus;

import javafx.util.StringConverter;

public class ProcessingStatusConverter extends StringConverter<ProcessingStatus> {

	private ResourceBundle resourceBundler;

	public ProcessingStatusConverter(ResourceBundle resourceBundler) {
		this.resourceBundler = resourceBundler;
	}

	@Override
	public String toString(ProcessingStatus object) {
		return resourceBundler.getString(object.name());
	}

	@Override
	public ProcessingStatus fromString(String string) {
		String key = Collections.list(resourceBundler.getKeys()).stream()
				.filter(p -> resourceBundler.getString(p).equals(string)).findFirst().get();
		if (key != null) {
			return ProcessingStatus.valueOf(key);
		} else {
			return null;
		}
	}

}
