/**
 * Copyright (c) 2000 Objectry.com
 * File Name:   DBConnection.java
 * @date        October 2001
 * @author      Siomara Pantarotto
 * Description: Definition of DBConnection class.This class is
 *              design to access Oracle database via drivers and
 *              to acces SQL Server database via ODBC bridge.
 **/

import java.io.*;
import java.sql.*;
import java.util.*;

public class DBConnection implements Serializable
{
  private Connection conn;
  private Statement stmt;
  private String driver;
  private String driverType;
  private String host;
  private String port;
  private String sid;
  private String username;
  private String password;

  /////////////////////////////////////////////////////////////////////////////
  // Constructors
  //

  // No argument constructor for Java Bean requirement
  public DBConnection() {
  }
  // For accessing Oracle via drivers
  public DBConnection(String driver, String driverType,
                      String host, String port, String sid,
                      String username, String password) {
    setDriver(driver);
    setDriverType(driverType);
    setHost(host);
    setPort(port);
    setSid(sid);
    setUsername(username);
    setPassword(password);
    connect();
  }
  // For accessing SQL Server via ODBC
  public DBConnection(String driver, String driverType) {
    setDriver(driver);
    setDriverType(driverType);
    setHost("");
    setPort("");
    setSid("");
    setUsername("");
    setPassword("");
    connect();
  }

  /////////////////////////////////////////////////////////////////////////////
  // Establish connection to the database
  //
  public void connect() {
    String url;
    url = getDriverType();
    
    if (getHost().length() > 0)     { url = url + getHost(); }
    if (getPort().length() > 0)     { url = url + ":" + getPort(); }
    if (getSid().length() > 0)      { url = url + ":" + getSid();  }

    try {
			Class.forName(driver);
		}
		catch(Exception e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
    try {
			this.conn = DriverManager.getConnection(url,username, password);
			this.stmt = conn.createStatement();
		}
		catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
  }

  /////////////////////////////////////////////////////////////////////////////
  // Disconnect from the database
  //
  public void disconnect() {
    try {
      this.stmt.close();
			this.conn.close();
		}
		catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
  }

  /////////////////////////////////////////////////////////////////////////////
  // Get connection
  //
  public Connection getConnection() {
	  return conn;
  }
  
  /////////////////////////////////////////////////////////////////////////////
  // Get statement
  //
  public Statement getStatement() {
	  return stmt;  
  }

  /////////////////////////////////////////////////////////////////////////////
  // Set and get DRIVER attribute
  //
  public void setDriver(String newDriver) {
    this.driver = newDriver;
  }
  public String getDriver() {
    return driver;
  }

  /////////////////////////////////////////////////////////////////////////////
  // Set and get DRIVER TYPE attribute
  //
  public void setDriverType(String newDriverType) {
    this.driverType = newDriverType;
  }
  public String getDriverType() {
    return driverType;
  }

  /////////////////////////////////////////////////////////////////////////////
  // Set and get HOST attribute
  //
  public void setHost(String newHost) {
    this.host = newHost;
  }
  public String getHost() {
    return host;
  }

  /////////////////////////////////////////////////////////////////////////////
  // Set and get PORT attribute
  //
  public void setPort(String newPort) {
    this.port = newPort;
  }
  public String getPort() {
    return port;
  }

  /////////////////////////////////////////////////////////////////////////////
  // Set and get SID attribute
  //
  public void setSid(String newSid) {
    this.sid = newSid;
  }
  public String getSid() {
    return sid;
  }

  /////////////////////////////////////////////////////////////////////////////
  // Set and get USERNAME attribute
  //
  public void setUsername(String newUsername) {
    this.username = newUsername;
  }
  public String getUsername() {
    return username;
  }

  /////////////////////////////////////////////////////////////////////////////
  // Set and get PASSWORD attribute
  //
  public void setPassword(String newPassword) {
    this.password = newPassword;
  }
  public String getPassword() {
    return password;
  }
}

