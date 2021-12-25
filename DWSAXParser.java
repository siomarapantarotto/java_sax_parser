/**
 * Copyright (c) 2000 Objectry.com
 * A java SAX Parser to extract information from CDSales.xml file
 * and populate table SALES.
 * @author	Siomara Pantarotto
 * @date	April, 2, 2001.
 **/

import java.util.*;
import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.ParserFactory;
import org.xml.sax.helpers.AttributeListImpl;

import com.sun.xml.tree.*;
import com.sun.xml.parser.Resolver;

public class DWSAXParser
{
	// These are the nodes of the XML file, passed as parameter, to be
	// read and have information parsed.
	static boolean saleNode		= false;
	static boolean dateNode		= false;
	static boolean fullNameNode	= false;
	static boolean streetNode	= false;
	static boolean cityNode		= false;
	static boolean stateNode	= false;
	static boolean zipCodeNode	= false;
	static boolean countryNode	= false;
	static boolean productNode	= false;
	static boolean quantityNode	= false;
	static boolean priceNode	= false;

	// Declare and initialize variables to store temporarely the information
	// that is parsed from XML file.
	static String date		= "";
	static String fullName	= "";
	static String street    = "";
	static String city		= "";
	static String zipCode	= "";
	static String state		= "";
	static String country	= "";
	static String product	= "";
	static String quantity	= "";
	static String price		= "";

	// Define a vector to store all the sales extracted from XML file
	static Vector vSales = new Vector();

    // Main method
	public static void main(String argv []) throws IOException
    {
  		InputSource input;

		if (argv.length != 1)
        {
    		System.err.println("Usage: cmd filename");
    	  	System.exit (1);
	    }
    	try
        {
    		input = Resolver.createInputSource(new File(argv [0]));
      		Parser  parser;
      		parser = ParserFactory.makeParser();
      		parser.setDocumentHandler(new MyDocHandler());
      		parser.setErrorHandler(new MyErrorHandler ());
      		parser.parse(input);
    	}
        catch(SAXParseException err)
        {
			System.out.println ("** Parsing error, line " +
            			err.getLineNumber() + ", uri " + err.getSystemId ());
      		System.out.println	("   " + err.getMessage ());
    	}
    	catch(SAXException e)
        {
    		Exception   x = e;
      		if (e.getException() != null)
            {
            }
        	x = e.getException();
      		x.printStackTrace();
    	}
    	catch(Throwable t) {
    		t.printStackTrace();
    	}
    	System.exit (0);
	}

   	// This is a private class
	static class MyDocHandler extends HandlerBase
    {
		public void startElement(String name, AttributeList attributes)
        			throws SAXException
        {
  			if (name.equals("sale")) {
    			saleNode = true;
    		} else if (name.equals("date")) {
    			dateNode = true;
    		} else if (name.equals("fullname")) {
    			fullNameNode = true;
    		} else if (name.equals("street")) {
    			streetNode = true;
    		} else if (name.equals("city")) {
    			cityNode = true;
    		} else if (name.equals("state")) {
    			stateNode = true;
    		} else if (name.equals("zipcode")) {
    			zipCodeNode = true;
    		} else if (name.equals("country")) {
    			countryNode = true;
    		} else if (name.equals("product")) {
    			productNode = true;
    		} else if (name.equals("quantity")) {
    			quantityNode = true;
    		} else if (name.equals("price")) {
    			priceNode = true;
    		}
		}

		public void startDocument() throws SAXException
        {
  			System.out.println("\nDocument processing started...");
  		}

		// Everything in this method will be execute after the xml files
        // is completely read. This is a good place to read the vector and
        // insert the objects into the database.
		public void endDocument() throws SAXException
        {
    		System.out.println("\nDocument processing finished");
    		SaleManager saleMgr = new SaleManager();
      		for (int i=0; i<vSales.size(); i++)
            {
				Sale sale = new Sale();
                sale = (Sale) vSales.elementAt(i);
                // Add the object into the database via saleMgr.
                saleMgr.add(sale);
    		}

            // Retrieve all the rows just inseted.
            System.out.println("\nPrinting the sales just added:\n");
            Vector vAddedSales = saleMgr.findAll();
            for (int i=0; i<vAddedSales.size(); i++)
            {
				Sale sale = new Sale();
                sale = (Sale) vAddedSales.elementAt(i);

                // The following printings are just for you to view what was
                // inserted into the database and compare with the xml file
                System.out.println("Sale Date\t:\t"		 + sale.getDate());
                System.out.println("Sale Full Name\t:\t" + sale.getFullName());
                System.out.println("Sale Street\t:\t"	 + sale.getStreet());
                System.out.println("Sale City\t:\t"		 + sale.getCity());
                System.out.println("Sale State\t:\t"	 + sale.getState());
                System.out.println("Sale Zip Code\t:\t"	 + sale.getZipCode());
                System.out.println("Sale Country\t:\t"	 + sale.getCountry());
                System.out.println("Sale Product\t:\t"	 + sale.getProduct());
                System.out.println("Sale Quantity\t:\t"	 + sale.getQuantity());
                System.out.println("Sale Price\t:\t"	 + sale.getPrice());
                System.out.println("\n\n");
    		}
  		}

		// This method allows you to catch what is being fired
        // The printings are just for you to understand the parser behavior.
 	 	public void characters(char ch[], int start, int length)
        {
  			String output = new String(ch, start, length);

			if (dateNode) {
				System.out.println("This is dateNode\t:\t" + output);
                date = output;
                dateNode = false;
    		} else if (fullNameNode) {
				System.out.println("This is fullNameNode\t:\t" + output);
                fullName = output;
				fullNameNode = false;
    		} else if (streetNode) {
				System.out.println("This is streetNode\t:\t" + output);
                street = output;
                streetNode = false;
    		} else if (cityNode) {
				System.out.println("This is cityNode\t:\t" + output);
                city = output;
                cityNode = false;
    		} else if (stateNode) {
				System.out.println("This is stateNode\t:\t" + output);
                state = output;
                stateNode = false;
    		} else if (zipCodeNode) {
				System.out.println("This is zipCodeNode\t:\t" + output);
                zipCode = output;
                zipCodeNode = false;
    		} else if (countryNode) {
				System.out.println("This is countryNode\t:\t" + output);
                country = output;
                countryNode = false;
    		} else if (productNode) {
				System.out.println("This is productNode\t:\t" + output);
                product = output;
                productNode = false;
    		} else if (quantityNode) {
				System.out.println("This is quantityNode\t:\t" + output);
                quantity = output;
                quantityNode = false;
    		} else if (priceNode) {
				System.out.println("This is priceNode\t:\t" + output);
                price = output;
                priceNode = false;

                // Since priceNode is the last node of a sale, here is the
                // location on the code to create a sale object, set its
                // attributes and then insert it into sales vector
                Sale sale = new Sale();
                sale.setSaleID(0);
                sale.setDate(date);
                sale.setFullName(fullName);
                sale.setStreet(street);
                sale.setCity(city);
                sale.setState(state);
                sale.setZipCode(zipCode);
                sale.setCountry(country);
                sale.setProduct(product);
                sale.setQuantity(Integer.parseInt(quantity));
                sale.setPrice(Double.parseDouble(price));

                // Add into the database
                vSales.addElement(sale);
    		}
    	}

      	public void warning(SAXParseException err) throws SAXParseException
      	{
         	System.out.println ("** Warning" + ", line " +
            		 err.getLineNumber() + ", uri " + err.getSystemId());
         	System.out.println("   " + err.getMessage());
      	}
   	}

    // This is a private class
	static class MyErrorHandler extends HandlerBase
   	{
    	public void error(SAXParseException err) throws SAXParseException
      	{
         	System.out.println("** Error" + ", line " +
       					err.getLineNumber() + ", uri " + err.getSystemId());
         	System.out.println("   " + err.getMessage());
      	}

      	// Dump warnings too
      	public void warning(SAXParseException err) throws SAXParseException
      	{
         	System.out.println("** Warning" + ", line " +
            			err.getLineNumber() + ", uri " + err.getSystemId());
         	System.out.println("   " + err.getMessage());
      	}
   	}
}

