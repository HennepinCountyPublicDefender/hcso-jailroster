package com.hennepin.hcso.jailroster.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.mn.hennepin.co.justice.silsservicemessage.ObjectFactory;
import us.mn.hennepin.co.justice.silsservicemessage.RequestSearchPersonID;


public class SilsRequestCreator implements Processor {

	
	private static final Logger LOG = LoggerFactory
			.getLogger(SilsRequestCreator.class.getName());

	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		try {
			LOG.debug("Entering the DocumentServiceRequestCreatorProcessor!");
			RequestSearchPersonID request = createRequest(exchange);
			exchange.getOut().setBody(request);
			LOG.debug("Exiting the DocumentServiceRequestCreatorProcessor!");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}


	private RequestSearchPersonID createRequest(Exchange exchange) {
		
    	ObjectFactory factory = new ObjectFactory();
    	RequestSearchPersonID request = factory.createRequestSearchPersonID();
    	
    	Integer bookingNumber = (Integer) exchange.getIn().getBody();
    	
		LOG.info("Boooking # : " + bookingNumber.toString());

		
    	request.setID(bookingNumber.toString() + "-001");
    	request.setIDTypeText("JMS - Booking");
    	
		
		return request;
	}

}
