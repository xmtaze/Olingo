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

public class MainForOrder {


	public static void main(String[] args) throws MalformedURLException, IOException, ODataException {

		UsedFunctionsForOrder usedFunctionsForOrder = new UsedFunctionsForOrder();
		Map<String, Object> hmapForOrder = new HashMap<String, Object>();
		List<ODataEntry> arrlistForOrder = new ArrayList<ODataEntry>();
		List<Order> orderList = new ArrayList<Order>();

		String serviceUrl = "http://services.odata.org/V2/Northwind/Northwind.svc";
		String usedFormat = usedFunctionsForOrder.APPLICATION_JSON;
		Edm edm = usedFunctionsForOrder.readEdm(serviceUrl);
		
		ODataFeed feedForOrder = usedFunctionsForOrder.readFeed(edm, serviceUrl, usedFormat, "Orders","Order_Details", "Product");
		arrlistForOrder = feedForOrder.getEntries();
		
		for (int i = 0; i < arrlistForOrder.size(); i++) {
			Order order = new Order();
			hmapForOrder = arrlistForOrder.get(i).getProperties();

			for (Entry<String, Object> entry : hmapForOrder.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				order.setOrder(key, value);
			}
			orderList.add(order);
		}
	}
	
}
