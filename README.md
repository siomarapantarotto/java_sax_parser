# A Java Sax Parser Demo

This mini project was created in 2001 for learning/tutorial purposes and has served as the basis for many projects in my career.

It was recently retrieved from Geocities.com, one of the most popular and successful free personal web hosting services of the 1990s, which was shut down in October 2009.

Apparently all sites hosted on the service would be deleted and could not be recovered.

Thanks to Geocities for not deleting this and many of my other projects.


## Objective:

Demonstrates how to create and work with a Java Sax Parser in order to extract information from an XML file and store it into an Oracle database.


## Context:

A company that sells CDs decides to implement a data warehouse to drive its business. They need to use a Java Sax parser to extract information from an XML file and store the data into a denormalized table created to store sales information.  The example start point of this example is the program called DWSAXParser.java code and the following statement is the command to run the example: 

C:> java DWSAXParser CDSales.xml

The parser code will read the data and, after the whole document is read, the parser invokes the SaleManager class to persist the objects into the database and to retrieve them and display on the console.  All the codes are listed bellow


## Steps:

- The Java Sax parser opens the xml file, reads its information line by line, and at the same time parse its information temporarary to variables and when the end of a sale is detected these information is transferred to a sale object that is stored in a vector.

- After reading the whole file, the Sax parser creates a Sale Manager object that stablishes a connection to the database, then reads the vector and for each object found invokes the method "add" of the SaleManager class.

- The objects are stored in the database and then retrieved and displayed in the screen in order to show the whole process of including and retrieving sales information from the database.


## Files:

The following 7 files compose the Java Sax Parser example:

1) DBProperties.txt - Contains the parameters to connect to the database.
2) DSales.xml - Contains the data to be parsed.
3) ctSales.sql - Contains the script to create the table Sales into Oracle database.
4) DBConnection.java - Establish connection with the database.
5) Sale.java - Contains the definition of class Sale.
6) SaleManager.java - Manage transactions with the database like insert, update, delete.
7) DWSAXParser.java - Read the XML file, parse the data and populate the database.


## Technologies

<p>
  <img src="https://img.shields.io/badge/Jakarta-Java-007396?style=for-the-badge&logo=java&logoColor=white" />&nbsp;&nbsp;
</p>


## Screenshots
<kbd><img src="https://user-images.githubusercontent.com/5893219/147393914-4e243041-ef95-4cea-b756-b80b6c07d8ba.png" width="500" height="450"></kbd>
<kbd><img src="https://user-images.githubusercontent.com/5893219/147393913-daad819e-e261-492a-bdde-06fef2ea1c1d.png" width="500" height="450"></kbd>


<!-- FOOTER (Author / Visit My Online Resume / Download My PDF Resume) -->
<hr>
<p align='center'>
  <a href="#"><img src="https://img.shields.io/badge/author-%C2%A9%20Siomara%20Cintia%20Pantarotto.%20All%20rights%20reserved.-008080?style=social"></a>&nbsp;&nbsp;
  <a href="https://siomara.com.br/"><img src="https://img.shields.io/badge/visit-My Online Resume-008080?style=social"></a>&nbsp;&nbsp;
  <a href="https://siomara.com.br/ResumePANTAROTTO.pdf"><img src="https://img.shields.io/badge/download-My PDF Resume-008080?style=social"></a>
</p>
 

