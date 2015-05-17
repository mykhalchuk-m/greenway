package com.mmm.greenway.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "date_interval")
public class DateInterval {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private LocalDate fromDate;
	@Column
	private LocalDate toDate;

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate from) {
		this.fromDate = from;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate to) {
		this.toDate = to;
	}

}
