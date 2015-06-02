package com.mmm.grenway.javafx.dto;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.mmm.greenway.entity.Invitation;
import com.mmm.greenway.entity.ProcessingStatus;

public class InvitationDto {
	private LongProperty id;
	private StringProperty title;
	private DoubleProperty price;
	private ObjectProperty<ProcessingStatus> status;

	public InvitationDto() {
		id = new SimpleLongProperty();
		title = new SimpleStringProperty();
		price = new SimpleDoubleProperty();
		status = new SimpleObjectProperty<ProcessingStatus>();
	}

	public InvitationDto(Invitation invitation) {
		this();
		if (invitation != null) {
			id.set(invitation.getId());
			title.set(invitation.getTitle());
			price.set(invitation.getPrice());
			status.set(invitation.getProcessingStatus());
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

	public ObjectProperty<ProcessingStatus> getStatus() {
		return status;
	}

	public void setStatus(ObjectProperty<ProcessingStatus> status) {
		this.status = status;
	}
}
