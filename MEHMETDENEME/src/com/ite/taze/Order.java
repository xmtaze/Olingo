package com.ite.taze;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.olingo.odata2.api.edm.Edm;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;
import org.apache.olingo.odata2.api.exception.ODataException;
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
	Invoice Invoice;

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
	public Invoice getInvoice() {
		return Invoice;
	}
	public void setInvoice(Invoice invoice) {
		Invoice = invoice;
	}

	public void setOrderAttributes(String key, Object value, boolean control) throws IOException, ODataException 
	{
		if(control = true) {
			if (key.equals("OrderID")) {
				this.setOrderID((int) value);

				ServicesForOrder servicesForInvoice = new ServicesForOrder();
				Map<String, Object> hmapForInvoiceInSetOrderAtt = new HashMap<String, Object>();
				List<ODataEntry> arrlistForInvoiceInSetOrderAtt = new ArrayList<ODataEntry>();
				List<Invoice> InvoiceListInSetOrderAtt = new ArrayList<Invoice>();

				String serviceUrl = "http://services.odata.org/V2/Northwind/Northwind.svc";
				String usedFormat = servicesForInvoice.APPLICATION_JSON;
				Edm edm = servicesForInvoice.readEdm(serviceUrl);

				ODataFeed feedForInvoice = servicesForInvoice.readFeed(edm, serviceUrl, usedFormat,"Invoices", null, null, (int) value);
				arrlistForInvoiceInSetOrderAtt = feedForInvoice.getEntries();

				for (int i = 0; i < arrlistForInvoiceInSetOrderAtt.size(); i++) {
					Invoice invoiceInstance = new Invoice();
					hmapForInvoiceInSetOrderAtt = arrlistForInvoiceInSetOrderAtt.get(i).getProperties();
					invoiceInstance.setOrderID(this.OrderID);
					for (Entry<String, Object> entry : hmapForInvoiceInSetOrderAtt.entrySet()) {
						String key1 = entry.getKey();
						Object value1 = entry.getValue();
						//control = false;
						invoiceInstance.setInvoiceAttributes(key1, value1, control);		

					}
					this.Invoice = invoiceInstance;
				}

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
}
