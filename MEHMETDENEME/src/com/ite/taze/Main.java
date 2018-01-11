package com.ite.taze;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.olingo.odata2.api.edm.Edm;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;
import org.apache.olingo.odata2.api.exception.ODataException;

public class Main {

	// Kod kurgusu bi-directional class yapılarının iç içe sürekli bir birinin içerisinde var olması.
	// İlk olarak fatura oluşturulup, oluşturulan fatura içerisine sipariş ve alt yapıları eklenip(siparişi detayı ve ürün)
	// sonrasında aynı zamanda bu orderın içerisine id leri aynı olan faturanın eklenmesi şeklinde devam eden recursive yapının kurulması.
	
	public static void main(String[] args) throws MalformedURLException, IOException, ODataException {

		ServiceFunctions servicesForInvoice = new ServiceFunctions();
		Map<String, Object> hmapForInvoice = new HashMap<String, Object>();
		List<ODataEntry> arrlistForInvoice = new ArrayList<ODataEntry>();
		List<Invoice> InvoiceList = new ArrayList<Invoice>();
		String serviceUrl = "http://services.odata.org/V2/Northwind/Northwind.svc";
		String usedFormat = ServiceFunctions.APPLICATION_JSON;
		Edm edm = servicesForInvoice.readEdm(serviceUrl);
		ODataFeed feedForInvoice = servicesForInvoice.readFeed(edm, serviceUrl, usedFormat, "Invoices", null, null, 0);
		arrlistForInvoice = feedForInvoice.getEntries();

		for (int i = 0; i < arrlistForInvoice.size(); i++) {
			Invoice Invoice = new Invoice();
			hmapForInvoice = arrlistForInvoice.get(i).getProperties();

			for (Entry<String, Object> entry : hmapForInvoice.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				Invoice.setInvoiceAttribute(key, value, true);
			}
			InvoiceList.add(Invoice);
		}
	}
}
