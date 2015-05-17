package com.mmm.greenway.entity.converter;


//@Converter(autoApply = true)
public class LocalDateConverter /*implements AttributeConverter<LocalDate, Date>*/ {
/*
	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		Instant instant = attribute.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		Instant instant = Instant.ofEpochMilli(dbData.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}
*/
}
