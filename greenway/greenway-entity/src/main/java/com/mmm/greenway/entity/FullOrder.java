package com.mmm.greenway.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class FullOrder extends DetailedOrder {

	@ManyToMany
	@JoinTable(name = "docs_per_order", joinColumns = { @JoinColumn(name = "docs_per_order_id") }, inverseJoinColumns = { @JoinColumn(name = "doc_id") })
	private List<DocumentPerOrder> documentPerOrders;
}
