package com.mmm.grenway.javafx.dto;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import com.mmm.greenway.entity.DateInterval;

public class DateIntervalDto {
	private ObjectProperty<LocalDate> from;
	private ObjectProperty<LocalDate> to;

	public DateIntervalDto() {
		from = new SimpleObjectProperty<>();
		to = new SimpleObjectProperty<>();
	}

	public DateIntervalDto(DateInterval dateInterval) {
		from = new SimpleObjectProperty<>(dateInterval.getFromDate());
		to = new SimpleObjectProperty<>(dateInterval.getToDate());
	}

	public ObjectProperty<LocalDate> getFrom() {
		return from;
	}

	public void setFrom(ObjectProperty<LocalDate> from) {
		this.from = from;
	}

	public ObjectProperty<LocalDate> getTo() {
		return to;
	}

	public void setTo(ObjectProperty<LocalDate> to) {
		this.to = to;
	}

}
