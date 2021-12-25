/**
 * Copyright (c) 2000 Objectry.com
 * File Name:   SaleManager.java
 * @date        Apr 07 2001
 * @author      Siomara Pantarotto
 * Description: Definition of Class SaleManager
 **/

import java.io.*;
import java.sql.*;
import java.util.*;

public class SaleManager {

  private DBConnection dbConnection;

  /**
   * Constructors
   */
  public SaleManager() {
    this.connectDB();
  }

  /************************************************************
   * Establish a connection with the database
   ************************************************************/
  public void connectDB() {
    try {
      Properties props = new Properties();
      FileInputStream dbPropFile = new FileInputStream("DBGguProperties.txt");
      props.load(dbPropFile);
      this.dbConnection = new DBConnection(props.getProperty("jdbc.driver"),
        props.getProperty("jdbc.driverType"), props.getProperty("jdbc.host"),
        props.getProperty("jdbc.port"), props.getProperty("jdbc.sid"),
        props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));

    } catch (Exception e) {
        System.out.println(e);
		}
  }

  /************************************************************
   * Disconnect from the database
   ************************************************************/
  public void disconnectDB() {
    dbConnection.disconnect();
  }

  /************************************************************
   * Query table SALES and retrieve all rows
   ************************************************************/
	public Vector findAll() {
    Vector vecSales = new Vector();
		String sql = "select * from Sales";
    this.connectDB();
		try {
      ResultSet rs = dbConnection.getStatement().executeQuery(sql);
      while (rs.next()) {
        Sale sale = new Sale();
        sale.setSaleID(Integer.parseInt(rs.getString(1)));
        sale.setDate(rs.getString(2));
        sale.setFullName(rs.getString(3));
        sale.setStreet(rs.getString(4));
        sale.setCity(rs.getString(5));
        sale.setState(rs.getString(6));
        sale.setZipCode(rs.getString(7));
        sale.setCountry(rs.getString(8));
        sale.setProduct(rs.getString(9));
        sale.setQuantity(Integer.parseInt(rs.getString(10)));
        sale.setPrice(Double.parseDouble(rs.getString(11)));
        vecSales.addElement(sale);
			}
      rs.close();
    } catch (SQLException sqle) {
			handleSQLException(sqle);
		}
    this.disconnectDB();
    return vecSales;
  }

  /************************************************************
   * Add a new sale into the database
   ************************************************************/
	public void add(Sale sale){
    String sql = "insert into SALES values (SalesIDSeq.NextVal,'" +
    					sale.getDate()      + "','" +
    					sale.getFullName()  + "','" +
    					sale.getStreet()    + "','" +
    					sale.getCity()      + "','" +
    					sale.getState()     + "','" +
    					sale.getZipCode()   + "','" +
                        sale.getCountry()   + "','" +
    					sale.getProduct()   + "'," +
    					sale.getQuantity()  + "," +
    					sale.getPrice()     + ")";
	//System.out.println(sql);
    this.connectDB();
		try {
			dbConnection.getStatement().executeUpdate(sql);
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
    this.disconnectDB();
	}


  /************************************************************
   * Update sale information into the database
   ************************************************************/
	public void update(Sale sale){
    // Implement update here.
    // Remember !!! This is a manager class that could be used by
    // other classes too. Although this method is not used by the
    // simple DWSAXParser it would be useful for other situations.
	}

  /************************************************************
   * Delete a sale from the database
   ************************************************************/
	public void delete(int saleID) {
    // Implement deletion here
    // Remember !!! This is a manager class that could be used by
    // other classes too. Although this method is not used by the
    // simple DWSAXParser it would be useful for other situations.
	}

  /************************************************************
   * Inform detail information about SQLException
   ************************************************************/
	static void handleSQLException(SQLException sqle) {
    String sqlMessage = sqle.getMessage();
    String sqlState = sqle.getSQLState();
    int vendorCode = sqle.getErrorCode();
    String out = "Attention!!! An Exception Ocurred\n" + "\nMessage : " +
    				sqlMessage +"\nSQL State : " + sqlState +
                    "\nVendor Code : " + vendorCode;
    System.out.println(out);
  }
}
