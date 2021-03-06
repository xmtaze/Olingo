package com.ite.taze;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.olingo.odata2.api.edm.Edm;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;
import org.apache.olingo.odata2.api.exception.ODataException;

public class Invoice {

	public Order order = null;

	private String ShipName;
	private String ShipAddress;
	private String ShipCity;
	private String ShipRegion;
	private String ShipPostalCode;
	private String ShipCountry;
	private String CustomerID;
	private String CustomerName;
	private String Address;
	private String City;
	private String Region;
	private String PostalCode;
	private String Country;
	private String Salesperson;
	private int OrderID;
	private String ShipperName;
	private int ProductID;
	private String ProductName;
	private double UnitPrice;
	private short Quantity;
	private float Discount;
	private double ExtendedPrice;
	private double Freight;

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

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getSalesperson() {
		return Salesperson;
	}

	public void setSalesperson(String salesperson) {
		Salesperson = salesperson;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public String getShipperName() {
		return ShipperName;
	}

	public void setShipperName(String shipperName) {
		ShipperName = shipperName;
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		UnitPrice = unitPrice;
	}

	public short getQuantity() {
		return Quantity;
	}

	public void setQuantity(short quantity) {
		Quantity = quantity;
	}

	public float getDiscount() {
		return Discount;
	}

	public void setDiscount(float discount) {
		Discount = discount;
	}

	public double getExtendedPrice() {
		return ExtendedPrice;
	}

	public void setExtendedPrice(double extendedPrice) {
		ExtendedPrice = extendedPrice;
	}

	public double getFreight() {
		return Freight;
	}

	public void setFreight(double freight) {
		Freight = freight;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setInvoiceAttribute(String key, Object value, boolean selectFromOdata) throws IOException, ODataException {

		if (key.equals("ShipName")) {
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
		} else if (key.equals("CustomerID")) {
			this.setCustomerID((String) value);
		} else if (key.equals("CustomerName")) {
			this.setCustomerName((String) value);
		} else if (key.equals("Address")) {
			this.setAddress((String) value);
		} else if (key.equals("City")) {
			this.setCity((String) value);
		} else if (key.equals("Region")) {
			this.setRegion((String) value);
		} else if (key.equals("PostalCode")) {
			this.setPostalCode((String) value);
		} else if (key.equals("Country")) {
			this.setCountry((String) value);
		} else if (key.equals("Salesperson")) {
			this.setSalesperson((String) value);
		} else if (key.equals("OrderID")) {
			this.setOrderID((int) value);

			ServiceFunctions servicesForOrder = new ServiceFunctions();
			Map<String, Object> hmapForOrder = new HashMap<String, Object>();
			List<ODataEntry> arrlistForOrder = new ArrayList<ODataEntry>();
			String serviceUrl = "http://services.odata.org/V2/Northwind/Northwind.svc";
			String usedFormat = ServiceFunctions.APPLICATION_JSON;
			Edm edm = servicesForOrder.readEdm(serviceUrl);

			ODataFeed feedForOrder = servicesForOrder.readFeed(edm, serviceUrl, usedFormat, "Orders",
					"Order_Details", "Product", (int) value);
			arrlistForOrder = feedForOrder.getEntries();

			for (int i = 0; i < arrlistForOrder.size(); i++) {
				hmapForOrder = arrlistForOrder.get(i).getProperties();
				Order orderInstance = new Order();

				for (Entry<String, Object> entry : hmapForOrder.entrySet()) {
					String key1 = entry.getKey();
					Object value1 = entry.getValue();

					orderInstance.setOrderAttribute(key1, value1, selectFromOdata, this);
				}
				this.setOrder(order);
				this.order= orderInstance;
			}
		} else if (key.equals("ShipperName")) {
			this.setShipperName((String) value);
		} else if (key.equals("ProductID")) {
			this.setProductID((int) value);
		} else if (key.equals("ProductName")) {
			this.setProductName((String) value);
		} else if (key.equals("UnitPrice")) {
			this.setUnitPrice(new Double(value.toString()));
		} else if (key.equals("Quantity")) {
			this.setQuantity((Short) value);
		} else if (key.equals("Discount")) {
			this.setDiscount((float) value);
		} else if (key.equals("ExtendedPrice")) {
			this.setExtendedPrice(new Double(value.toString()));
		} else if (key.equals("Freight")) {
			this.setFreight(new Double(value.toString()));
		}
	}
}


