package com.mmm.grenway.javafx.controller;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmm.grenway.javafx.dto.InvitaitionOrderDto;
import com.mmm.grenway.javafx.service.DetailedOrderService;

@Component
public class InvitationDeliveryController {
	
	@Autowired
	private DetailedOrderService detailedOrderService;
	
	@FXML
	private void initialize() {
		System.out.println("InvitationDeliveryController");
		initTableData();
	}
	
	private void initTableData() {
		usersTable.setItems(detailedOrderService.findAllForInvitationProcess());
		userNameColumn.setCellValueFactory(value -> value.getValue().getClientName());
		birthDayColumn.setCellValueFactory(value -> value.getValue().getBirthDay());
		foringPassportNumberColumn.setCellValueFactory(value -> value.getValue().getForingPassportNumber());
		regionColumn.setCellValueFactory(value -> value.getValue().getRegion());
		registrationDateColumn.setCellValueFactory(value -> value.getValue().getRegistrationDate());
		costColumn.setCellValueFactory(value -> value.getValue().getCost());
		invitationSupplierColumn.setCellValueFactory(value -> value.getValue().getInvitationSupplier());
		statusColumn.setCellValueFactory(value -> value.getValue().getStatus());
	}
	
	@FXML
	private TextField filterUserName;
	@FXML
	private TableView<InvitaitionOrderDto> usersTable;
	@FXML
	private TableColumn<InvitaitionOrderDto, String> userNameColumn;
	@FXML
	private TableColumn<InvitaitionOrderDto, LocalDate> birthDayColumn;
	@FXML
	private TableColumn<InvitaitionOrderDto, String> foringPassportNumberColumn;
	@FXML
	private TableColumn<InvitaitionOrderDto, String> regionColumn;
	@FXML
	private TableColumn<InvitaitionOrderDto, LocalDate> registrationDateColumn;
	@FXML
	private TableColumn<InvitaitionOrderDto, Number> costColumn;
	@FXML
	private TableColumn<InvitaitionOrderDto, String> invitationSupplierColumn;
	@FXML
	private TableColumn<InvitaitionOrderDto, String> statusColumn;
}
