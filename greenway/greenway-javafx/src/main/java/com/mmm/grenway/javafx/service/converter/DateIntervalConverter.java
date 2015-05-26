package com.mmm.grenway.javafx.service.converter;

import com.mmm.greenway.entity.DateInterval;
import com.mmm.grenway.javafx.dto.DateIntervalDto;

public class DateIntervalConverter {
	public static void toDateIntervalDto(DateIntervalDto dateIntervalDto, DateInterval dateInterval) {
		dateIntervalDto.getFrom().set(dateInterval.getFromDate());
		dateIntervalDto.getTo().set(dateInterval.getToDate());
	}
	
	public static DateInterval toDateInterval(DateIntervalDto dateIntervalDto) {
		if (dateIntervalDto.getFrom().get() == null && dateIntervalDto.getTo().get() == null) {
			return null;
		}
		DateInterval dateInterval = new DateInterval();
		dateInterval.setFromDate(dateIntervalDto.getFrom().get());
		dateInterval.setToDate(dateIntervalDto.getTo().get());
		return dateInterval;
	}
	
}
