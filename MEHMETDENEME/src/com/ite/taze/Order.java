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

	List<Order_Detail> orderDetails;
	public Invoice invoice = null;

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
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public void setOrderAttribute(String key, Object value, boolean selectFromOdata, Invoice invoice) throws IOException, ODataException {

		if (key.equals("OrderID")) {
			this.setOrderID((int) value);
			
			boolean retrieveFromDB;
			if (invoice!=null) {
				retrieveFromDB = false;
			}
			else{
				retrieveFromDB = true;
			}
			
			if (retrieveFromDB == true) {
				ServicesForOrder servicesForInvoice = new ServicesForOrder();
				Map<String, Object> hmapForInvoice = new HashMap<String, Object>();
				List<ODataEntry> arrlistForInvoice = new ArrayList<ODataEntry>();
				String serviceUrl = "http://services.odata.org/V2/Northwind/Northwind.svc";
				String usedFormat = ServicesForOrder.APPLICATION_JSON;
				Edm edm = servicesForInvoice.readEdm(serviceUrl);

				ODataFeed feedForOrder = servicesForInvoice.readFeed(edm, serviceUrl, usedFormat, "Invoices", null, null, (int) value);
				arrlistForInvoice = feedForOrder.getEntries();

				for (int i = 0; i < arrlistForInvoice.size(); i++) {
					hmapForInvoice = arrlistForInvoice.get(i).getProperties();
					Invoice InvoiceInstance = new Invoice();
					for (Entry<String, Object> entry : hmapForInvoice.entrySet()) {
						String key1 = entry.getKey();
						Object value1 = entry.getValue();
						InvoiceInstance.setInvoiceAttribute(key1, value1, selectFromOdata);
					}
					this.setInvoice(InvoiceInstance);
				}
			}
			else {
				
				this.setInvoice(invoice);
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
		} else if (key.equals("ShiptCity")) {
			this.setShipCity((String) value);
		} else if (key.equals("ShipRegion")) {
			this.setShipRegion((String) value);
		} else if (key.equals("ShipPostalCode")) {
			this.setShipPostalCode((String) value);
		} else if (key.equals("ShipCountry")) {
			this.setShipCountry((String) value);
		} else if (key.equals("Order_Details")) {
			
			Order_Detail orderDetail = new Order_Detail();
			ODataDeltaFeedImpl orderDetailFeed = (ODataDeltaFeedImpl) value;
			Map<String, Object> orderDetailProperties = new HashMap<String, Object>();
			List<ODataEntry> orderDetailEntry = orderDetailFeed.getEntries();

			this.orderDetails = new ArrayList<Order_Detail>();

			for (int i = 0; i < orderDetailEntry.size(); i++) {
				orderDetailProperties = orderDetailEntry.get(i).getProperties();
				for (Entry<String, Object> entry : orderDetailProperties.entrySet()) {
					orderDetail.setOrder_DetailAttribute(entry.getKey(), entry.getValue());
					this.orderDetails.add(orderDetail);
				}
			}

		}
	}

}

