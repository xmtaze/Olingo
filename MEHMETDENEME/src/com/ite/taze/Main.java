package com.ite.taze;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.edm.Edm;
import org.apache.olingo.odata2.api.edm.EdmEntityContainer;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderReadProperties;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataDeltaFeed;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;
import org.apache.olingo.odata2.api.exception.ODataException;

public class Main {

	public static void main(String[] args) throws MalformedURLException, IOException, ODataException {

		ServicesForInvoice servicesForInvoice = new ServicesForInvoice();
		Map<String, Object> hmapForInvoice = new HashMap<String, Object>();
		List<ODataEntry> arrlistForInvoice = new ArrayList<ODataEntry>();
		List<Invoice> InvoiceList = new ArrayList<Invoice>();
		String serviceUrl = "http://services.odata.org/V2/Northwind/Northwind.svc";
		String usedFormat = ServicesForInvoice.APPLICATION_JSON;
		Edm edm = servicesForInvoice.readEdm(serviceUrl);
		ODataFeed feedForInvoice = servicesForInvoice.readFeed(edm, serviceUrl, usedFormat, "Invoices");
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
