/**
 * Copyright (c) 2000 Objectry.com
 * File Name:   SaleManager.java
 * @date        Apr 02 2001
 * @author      Siomara Pantarotto
 * Description: Definition of Class Sale
 **/

public class Sale {

    private int saleID;
	  private String date;
  	private String fullName;
  	private String street;
  	private String city;
  	private String state;
  	private String zipCode;
  	private String country;
  	private String product;
  	private int quantity;
  	private double price;

	/**
	 * Constructors
	 */
	public Sale() {
	}

  public Sale(String SaleID, String date, String fullName, String street,
              String city, String state, String zipCode, String country,
              String product, String quantity, String price) {
	}

    public void setSaleID(int saleID)			{ this.saleID	= saleID;	}
  	public void setDate(String date)			{ this.date	 	= date;		}
  	public void setFullName(String fullName) 	{ this.fullName	= fullName;	}
  	public void setStreet(String street)		{ this.street	= street;	}
  	public void setCity(String city)  			{ this.city	 	= city;		}
  	public void setState(String state)			{ this.state 	= state;	}
  	public void setZipCode(String zipCode)		{ this.zipCode	= zipCode;	}
  	public void setCountry(String country)		{ this.country	= country;	}
  	public void setProduct(String product)		{ this.product	= product;	}
  	public void setQuantity(int quantity)		{ this.quantity = quantity;	}
  	public void setPrice(double price)			{ this.price  	= price;	}

    public int getSaleID()		{ return this.saleID;	}
  	public String getDate()		{ return this.date;	  	}
  	public String getFullName()	{ return this.fullName;	}
  	public String getStreet()	{ return this.street; 	}
  	public String getCity()		{ return this.city;	  	}
  	public String getState()	{ return this.state;	}
  	public String getZipCode()	{ return this.zipCode;	}
  	public String getCountry()	{ return this.country;	}
  	public String getProduct()	{ return this.product;	}
  	public int getQuantity()	{ return this.quantity;	}
  	public double getPrice()	{ return this.price;	}
    
}
