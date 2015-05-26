package com.mmm.greenway.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "docs_per_order")
public class DocumentPerOrder {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Document document;
	@Enumerated(EnumType.STRING)
	private ProcessingStatus processingStatus = ProcessingStatus.NONE;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "order_id")
	private DetailedOrder order;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public ProcessingStatus getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(ProcessingStatus processingStatus) {
		this.processingStatus = processingStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DetailedOrder getOrder() {
		return order;
	}

	public void setOrder(DetailedOrder order) {
		this.order = order;
	}
}
