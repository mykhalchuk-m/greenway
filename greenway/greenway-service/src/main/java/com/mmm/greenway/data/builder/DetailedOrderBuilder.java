package com.mmm.greenway.data.builder;

import java.util.List;

import com.mmm.greenway.entity.BaseOrder;
import com.mmm.greenway.entity.DateInterval;
import com.mmm.greenway.entity.DetailedOrder;
import com.mmm.greenway.entity.OrderClientAddress;
import com.mmm.greenway.entity.OrderClientWorkingPlace;
import com.mmm.greenway.entity.OrderClinetHost;
import com.mmm.greenway.entity.OrderGeneralInfo;
import com.mmm.greenway.entity.OrderPassportData;

public class DetailedOrderBuilder {
	private DetailedOrder detailedOrder;
	
	public DetailedOrderBuilder(BaseOrder baseOrder) {
		detailedOrder = new DetailedOrder();
		detailedOrder.setId(baseOrder.getId());
		detailedOrder.setClientName(baseOrder.getClientName());
		detailedOrder.setSupplierName(baseOrder.getSupplierName());
		detailedOrder.setPhoneNumber(baseOrder.getPhoneNumber());
		detailedOrder.setOperator(baseOrder.getOperator());
		detailedOrder.setNote(baseOrder.getNote());
		detailedOrder.setDate(baseOrder.getDate());
	}
	
	public DetailedOrderBuilder setOrderGeneralInfo(OrderGeneralInfo generalInfo) {
		detailedOrder.setOrderGeneralInfo(generalInfo);
		return this;
	}
	
	public DetailedOrderBuilder setOrderPassportData(OrderPassportData passportData) {
		detailedOrder.setOrderPassportData(passportData);
		return this;
	}
	
	public DetailedOrderBuilder setOrderClientAddress(OrderClientAddress clientAddress) {
		detailedOrder.setOrderClientAddress(clientAddress);
		return this;
	}
	
	public DetailedOrderBuilder setOrderClientWorkingPlace(OrderClientWorkingPlace workingPlace) {
		detailedOrder.setOrderClientWorkingPlace(workingPlace);
		return this;
	}
	
	public DetailedOrderBuilder setOrderClinetHost(OrderClinetHost clinetHost) {
		detailedOrder.setOrderClinetHost(clinetHost);
		return this;
	}
	
	public DetailedOrderBuilder setTravelPurpose(String travelPurpose) {
		detailedOrder.setTravelPurpose(travelPurpose);
		return this;
	}
	
	public DetailedOrderBuilder setSutableInOutDates(DateInterval sutableInOutDates) {
		detailedOrder.setSuitableInOutDates(sutableInOutDates);
		return this;
	}
	
	public DetailedOrderBuilder setPreviousVisasDates(List<DateInterval> previousVisasDates) {
		detailedOrder.setPreviousVisasDates(previousVisasDates);
		return this;
	}
	
	public DetailedOrder build() {
		return detailedOrder;
	}
}
