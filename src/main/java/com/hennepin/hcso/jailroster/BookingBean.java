package com.hennepin.hcso.jailroster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class BookingBean implements Booking {
	private static final Logger LOG = Logger.getLogger(BookingBean.class
			.getName());

	private final static String siteUrl = "http://www4.co.hennepin.mn.us/webbooking/resultbycustody.asp";

	@Override
	public ArrayList<Integer> bookings() {

		Document doc;
		ArrayList<Integer> bookingNumbers = new ArrayList<Integer>();

		try {
			doc = Jsoup.connect(siteUrl).get();
			Elements links = doc.select("a[href]");
			Elements media = doc.select("[src]");
			Elements imports = doc.select("link[href]");
			List<Node> nodes = doc.childNodes();
			for (Element link : links) {
				if (isInteger(link.text())) {
					bookingNumbers.add(Integer.parseInt(link.text()));

				}

			}
			LOG.info("There were this many bookings : " + bookingNumbers.size());
			

			HashSet bookingNumbersHashSet = new HashSet<Integer>(bookingNumbers);
			bookingNumbers.clear();
			bookingNumbers = new ArrayList<Integer>(bookingNumbersHashSet);
			LOG.info("There were this many bookings as hashset: " + bookingNumbers.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		


		return bookingNumbers;
	}

	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
