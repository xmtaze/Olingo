package com.ite.taze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.entry.ODataEntryImpl;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

public class Order {

	private int OrderID;
	private String CustomerID;
	private int EmployeeID;
	private int ShipVia;
	private double Freight;
	private String ShipName;
	private String ShipAddress;
	private String ShipCity;
	private String ShipRegion;
	private String ShipPostalCode;
	private String ShipCountry;

	List<Order_Detail> order_DetailList = new ArrayList<Order_Detail>();

	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public int getShipVia() {
		return ShipVia;
	}
	public void setShipVia(int shipVia) {
		ShipVia = shipVia;
	}
	public double getFreight() {
		return Freight;
	}
	public void setFreight(double freight) {
		Freight = freight;
	}
	public String getShipName() {
		return ShipName;
	}
	public void setShipName(String shipName) {
		ShipName = shipName;
	}
	public String getShipAddress() {
		return ShipAddress;
	}
	public void setShipAddress(String shipAddress) {
		ShipAddress = shipAddress;
	}
	public String getShipCity() {
		return ShipCity;
	}
	public void setShipCity(String shipCity) {
		ShipCity = shipCity;
	}
	public String getShipRegion() {
		return ShipRegion;
	}
	public void setShipRegion(String shipRegion) {
		ShipRegion = shipRegion;
	}
	public String getShipPostalCode() {
		return ShipPostalCode;
	}
	public void setShipPostalCode(String shipPostalCode) {
		ShipPostalCode = shipPostalCode;
	}
	public String getShipCountry() {
		return ShipCountry;
	}
	public void setShipCountry(String shipCountry) {
		ShipCountry = shipCountry;
	}

	public void setOrderAttributes(String key, Object value) 
	{
		if (key.equals("OrderID")) {
			this.setOrderID((int) value);
		} else if (key.equals("CustomerID")) {
			this.setCustomerID((String) value);
		} else if (key.equals("EmployeeID")) {
			this.setEmployeeID((int) value); 
		} else if (key.equals("ShipVia")) {
			this.setShipVia((int) value);
		} else if (key.equals("Freight")) {
			this.setFreight(new Double(value.toString()));
		} else if (key.equals("ShipName")) {
			this.setShipName((String) value);
		} else if (key.equals("ShipAddress")) {
			this.setShipAddress((String) value);
		} else if (key.equals("ShipCity")) {
			this.setShipCity((String) value);
		} else if (key.equals("ShipRegion")) {
			this.setShipRegion((String) value);
		} else if (key.equals("ShipPostalCode")) {
			this.setShipPostalCode((String) value);
		} else if (key.equals("ShipCountry")) {
			this.setShipCountry((String) value);
		} else if(key.equals("Order_Details")) {
			List<ODataEntry> arrlist = new ArrayList<ODataEntry>();
			Map<String, Object> hmap = new HashMap<String, Object>();
			Order_Detail order_detail = new Order_Detail();
			ODataDeltaFeedImpl orderEntry = (ODataDeltaFeedImpl) value;
			arrlist = orderEntry.getEntries();
			
			for (int i = 0; i < arrlist.size(); i++) {
				hmap = arrlist.get(i).getProperties();
				
				for (Entry<String, Object> entry : hmap.entrySet()) {
					String key1 = entry.getKey();
					Object value1 = entry.getValue();
					order_detail.setOrder_DetailAttribute(key1, value1);
					order_DetailList.add(order_detail);
				}
			}
		} 	
	}
}
