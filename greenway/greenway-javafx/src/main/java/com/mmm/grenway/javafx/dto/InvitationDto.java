package com.mmm.grenway.javafx.dto;

import com.mmm.greenway.entity.Invitation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InvitationDto {
	private LongProperty id;
	private StringProperty title;
	private DoubleProperty price;
	private StringProperty status;

	public InvitationDto() {
		id = new SimpleLongProperty();
		title = new SimpleStringProperty();
		price = new SimpleDoubleProperty();
		status = new SimpleStringProperty();
	}

	public InvitationDto(Invitation invitation) {
		this();
		if (invitation != null) {
			id.set(invitation.getId());
			title.set(invitation.getTitle());
			price.set(invitation.getPrice());
			status.set(invitation.getProcessingStatus().name());
		}
	}

	public LongProperty getId() {
		return id;
	}

	public void setId(LongProperty id) {
		this.id = id;
	}

	public StringProperty getTitle() {
		return title;
	}

	public void setTitle(StringProperty title) {
		this.title = title;
	}

	public DoubleProperty getPrice() {
		return price;
	}

	public void setPrice(DoubleProperty price) {
		this.price = price;
	}

	public StringProperty getStatus() {
		return status;
	}

	public void setStatus(StringProperty status) {
		this.status = status;
	}
}
