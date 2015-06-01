package com.mmm.grenway.javafx.controller.helper;

import java.time.LocalDate;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

public class DatePickerCellFactory implements Callback<DatePicker, DateCell> {

	private DatePicker datePickerFromPair;
	private boolean before;

	public DatePickerCellFactory(DatePicker datePickerFromPair, boolean before) {
		this.datePickerFromPair = datePickerFromPair;
		this.before = before;
	}

	@Override
	public DateCell call(DatePicker param) {
		return new DateCell() {
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);
				if (before) {
					if (datePickerFromPair.getValue() != null && item.isBefore(datePickerFromPair.getValue())) {
						setDisable(true);
					}
				} else {
					if (datePickerFromPair.getValue() != null && item.isAfter(datePickerFromPair.getValue())) {
						setDisable(true);
					}
				}
			}
		};
	}

}
