package com.mmm.grenway.javafx.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
	private static final String DATE_PATTERN = "dd.MM.yyyy";
	private static final String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

	public static String format(LocalDateTime date) {
		if (date == null) {
			return null;
		}
		return DATE_TIME_FORMATTER.format(date);
	}
	
	public static String format(LocalDate date) {
		if (date == null) {
			return null;
		}
		return DATE_FORMATTER.format(date);
	}

	public static LocalDate parse(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static boolean validDate(String dateString) {
		return DateUtil.parse(dateString) != null;
	}
}
