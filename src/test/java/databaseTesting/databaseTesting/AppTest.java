package databaseTesting.databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.PriorityBlockingQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

 WebDriver driver= new ChromeDriver();

   
 Connection con;
 Statement stmt;
 ResultSet rs;
 int customerNumberInToDatabase;
 String FirstNameInToDatabase;
 String LastNameInToDatabase;
 String Email;
 String password;
 
 String website = "https://smartbuy-me.com/account/register";
   
   @BeforeTest
   public void mySetup() throws SQLException {
	   
	  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","1234"); 
	   
	   driver.get(website);
	   
	   driver.manage().window().maximize();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 200)");
	   
   }
   
   @Test(priority = 1,enabled = true)
   public void createCustomer() throws SQLException {
	String query= "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (999, 'New Corp', 'Smith', 'John', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00)";   
	
	
	stmt=con.createStatement();
	
	int rowinsert= stmt.executeUpdate(query);
	
	System.out.println(rowinsert);
	   
	   
	   
   }
   
   @Test(priority = 2,enabled = true)
   public void UpdateCustomer() throws SQLException {
	   
	   String query="Update customers Set contactFirstName='Rola', contactLastName='rwahsah' where customerNumber=999 ";
	   stmt=con.createStatement();
	   
	   int effectiupdate= stmt.executeUpdate(query);
	   
	   System.out.println(effectiupdate);
	   
   }
	   

   
   @Test(priority = 3)
   
   public void ReadInToDatabase() throws SQLException {
	   
	   
	  String query= "select * from customers where customerNumber=999";
	  
	  stmt=con.createStatement();
	  
	  rs=stmt.executeQuery(query);
	  
	  
	  while(rs.next()) {
		  
		  customerNumberInToDatabase= rs.getInt("customerNumber");
		  
		  
		 FirstNameInToDatabase= rs.getString("contactFirstName").toString().trim();
		  
		 LastNameInToDatabase=rs.getString("contactLastName");
		
		  Email= FirstNameInToDatabase+LastNameInToDatabase+"@gmail.com";
		  
		 password= "Rsh@1234";
		  
		  
		  System.out.println(FirstNameInToDatabase);
		  
		  System.out.println(LastNameInToDatabase);
		  System.out.println(Email);
		  
		  System.out.println(password);
	  }
	   
	   WebElement FirstName= driver.findElement(By.id("customer[first_name]"));
	   
	   FirstName.sendKeys(FirstNameInToDatabase);
	   
	   WebElement LastName= driver.findElement(By.id("customer[last_name]"));
	   LastName.sendKeys(LastNameInToDatabase);
	   
	   WebElement email= driver.findElement(By.id("customer[email]"));
	   email.sendKeys(Email);
	   
	   WebElement pass= driver.findElement(By.id("customer[password]"));
	   
	  pass.sendKeys(Email);
	  
	  
	  
//	  WebElement create= driver.findElement(By.cssSelector(".form__submit.button.button--primary.button--full"));
//	  
//	  create.click();
//	  
	  
	  
   }
   
   
   @Test(priority = 4)
   public void Delet() throws SQLException {
	   
	   String query="delete from customers where customerNumber=999";
	   
	   stmt= con.createStatement();
	   
	int rowresult   = stmt.executeUpdate(query);
	   
	   
   }
   
   
   
   
}
