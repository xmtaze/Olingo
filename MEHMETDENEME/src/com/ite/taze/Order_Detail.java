package com.ite.taze;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.olingo.odata2.core.ep.entry.ODataEntryImpl;

public class Order_Detail {

	Product product = new Product();

	private int OrderID;
	private int ProductID;
	private double UnitPrice;
	private short Quantity;
	private float Discount;

	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
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

	public void setOrder_DetailAttribute(String key, Object value) 
	{
		if (key.equals("OrderID")) {
			this.setOrderID((int) value);
		} else if (key.equals("ProductID")) {
			this.setProductID((int) value);
		} else if (key.equals("UnitPrice")) {
			this.setUnitPrice(new Double(value.toString()));
		} else if (key.equals("Quantity")) {
			this.setQuantity((short) value);
		} else if (key.equals("Discount")) {
			this.setDiscount((float) value);
		} else if(key.equals("Product")){
			ODataEntryImpl productEntry = (ODataEntryImpl) value;
			Map<String, Object> productProperties = productEntry.getProperties();
			Set<Entry<String, Object>> arrlist = productProperties.entrySet();
			for (Entry<String, Object> entry : arrlist) {
				this.product.setProductAttribute(entry.getKey(), entry.getValue());
			}
		}
	}
}
