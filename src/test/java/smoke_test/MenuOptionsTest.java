package smoke_test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.Annotation;

public class MenuOptionsTest {
    final String driverUser = "user24";
    final String Password = "UserUser123";
    final String storeM = "storemanager68";
    String expTitle="";
    String actTitle="";
    String expPageName="";
    String actPageName="";
    WebDriver driver;

    @BeforeMethod
    public void OpenBrowser (){
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
      driver.get("http://qa2.vytrack.com/user/login");
      driver.manage().window().maximize();
    }

    @AfterMethod
    public void CloseBrowser(){
        driver.quit();
    }


    @Test
    public void testDriver () throws InterruptedException {
        /*
1. Login to Vytrack as a driver
2. Navigate to FleetàVehicles, verify page title Car - Entities - System - Car - Entities - System, page name All Cars (updated)
3. Navigate to Customers à Accounts, verify page title Accounts - Customers, verify page name Accounts
4. Navigate to CustomersàContacts, verify page title Contacts - Customers, verify page name Contacts (updated)
5. Navigate to ActivitiesàCalendar Events, verify page title Calendar Events - Activities, page name Calendar Events
         */
        //1
        System.out.println("FIRST TEST");
        driver.findElement(By.id("prependedInput")).sendKeys(driverUser);
        driver.findElement(By.id("prependedInput2")).sendKeys(Password);
        driver.findElement(By.id("_submit")).click();

        //2
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[1]/a/span")).click();
        driver.findElement(By.xpath("//span[.='Vehicles']")).click();
        Thread.sleep(2000);

        System.out.println("Check title");
        expTitle = "Car - Entities - System - Car - Entities - System";
        actTitle = driver.getTitle();
        System.out.println(actTitle +"\n"+expTitle+"\n");
        Assert.assertEquals(actTitle,expTitle);

        System.out.println("Check page");
        expPageName = "Cars";
        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        Assert.assertEquals(actPageName,expPageName);
        System.out.println(actPageName +"\n"+expPageName+"\n");
        driver.navigate().back();

        //3
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[2]")).click();
        driver.findElement(By.xpath("//span[.='Accounts']")).click();
        Thread.sleep(2000);

        System.out.println("Check title");
        expTitle="Accounts - Customers";
        actTitle=driver.getTitle();
        System.out.println(actTitle +"\n"+expTitle+"\n");
        Assert.assertEquals(expTitle,actTitle);

        System.out.println("Check page");
        expPageName = "Accounts";
        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        System.out.println(actPageName +"\n"+expPageName+"\n");
        Assert.assertEquals(actPageName,expPageName);

        //4
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[2]")).click();
        driver.findElement(By.xpath("//span[.='Contacts']")).click();
        Thread.sleep(1000);

        System.out.println("Check title");
        expTitle = driver.getTitle();
        actTitle = "Contacts - Customers";
        System.out.println(actTitle +"\n"+expTitle+"\n");
        Assert.assertEquals(actTitle,expTitle);

        System.out.println("Check page");
        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        expPageName = "Contacts";
        System.out.println(actPageName +"\n"+expPageName+"\n");
        Assert.assertEquals(actPageName,expPageName);

        //5
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[3]/a/span")).click();
        driver.findElement(By.linkText("Calendar Events")).click();
        Thread.sleep(1000);

        System.out.println("Check title");
        System.out.println(actTitle+"\n"+expTitle+"\n");
        actTitle = driver.getTitle();
        expTitle = "Calendar Events - Activities";
        Assert.assertEquals(actTitle,expTitle);

        System.out.println("Check page");
        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        expPageName = "Calendar Events";
        System.out.println(actPageName +"\n"+expPageName+"\n");
        Assert.assertEquals(actPageName,expPageName);
        driver.findElement(By.cssSelector("i.fa-caret-down")).click();
        driver.findElement(By.cssSelector("a[href='/user/logout']")).click();

    }

    @Test
    public void MenuOptionsStoreManager() throws InterruptedException {

        /*
1. Login to Vytrack as a store manager
2. Navigate to Dashboards à Dashboard, verify page title Dashboard - Dashboards, verify page name Dashboard
3. Navigate to FleetàVehicles, verify page title All - Car - Entities - System - Car - Entities - System, page name All Cars (updated)
4. Navigate to CustomersàAccounts, verify page title All - Accounts – Customers, verify page name All Accounts(updated)
5. Navigate to CustomersàContacts, verify page title All - Contacts - Customers, verify page name All Contacts (updated)
6. Navigate to Sales à Opportunities, verify page title Open Opportunities - Opportunities - Sales, verify page name Open Opportunities
7. Navigate to ActivitiesàCalls verify page title All - Calls - Activities, page name All Calls (updated)
8. Navigate to ActivitiesàCalendar Events, verify page title Calendar Events - Activities, page name All Calendar Events (updated)
         */

        //1
        System.out.println("SECOND TEST");
        driver.findElement(By.id("prependedInput")).sendKeys(storeM);
        driver.findElement(By.id("prependedInput2")).sendKeys(Password);
        driver.findElement(By.id("_submit")).click();
        Thread.sleep(3000);

        //2
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[1]")).click();
        driver.findElement(By.xpath("//span[.='Dashboard']")).click();
        Thread.sleep(2000);
        actTitle = driver.getTitle();
        expTitle = "Dashboard - Dashboards";
        System.out.println("Check title");
        System.out.println(actTitle+"\n"+expTitle+"\n");
        Assert.assertEquals(actTitle,expTitle);

        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        expPageName = "Dashboard";
        System.out.println("Check page name");
        System.out.println(actPageName+"\n"+expPageName+"\n");
        Assert.assertEquals(actPageName,expPageName);

        //3
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[2]")).click();
        driver.findElement(By.xpath("//span[.='Vehicles']")).click();
        System.out.println("Check title");
        expTitle = "All - Car - Entities - System - Car - Entities - System";
        Thread.sleep(3000);
        actTitle = driver.getTitle();
        System.out.println(actTitle+"\n"+expTitle+"\n");
        Assert.assertEquals(actTitle,expTitle);

        System.out.println("Check page");
        expPageName = "All Cars";
        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        System.out.println(actPageName+"\n"+expPageName+"\n");
        Assert.assertEquals(actPageName,expPageName);

        //4
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[3]")).click();
        driver.findElement(By.xpath("//span[.='Accounts']")).click();

        Thread.sleep(2000);
        System.out.println("Check title");
        expTitle="All - Accounts - Customers";
        actTitle=driver.getTitle();
        System.out.println(actTitle+"\n"+expTitle+"\n");
        Assert.assertEquals(expTitle,actTitle);

        System.out.println("Check page");
        expPageName = "All Accounts";
        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        System.out.println(actPageName+"\n"+expPageName+"\n");
        Assert.assertEquals(actPageName,expPageName);

        //5
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[3]")).click();
        driver.findElement(By.xpath("//span[.='Contacts']")).click();

        Thread.sleep(2000);
        System.out.println("Check title");
        expTitle="All - Contacts - Customers";
        actTitle=driver.getTitle();
        System.out.println(actTitle+"\n"+expTitle+"\n");
        Assert.assertEquals(expTitle,actTitle);

        System.out.println("Check page");
        expPageName = "All Contacts";
        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        System.out.println(actPageName+"\n"+expPageName+"\n");
        Assert.assertEquals(actPageName,expPageName);

        //6
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[4]")).click();
        driver.findElement(By.xpath("//li[@data-route='oro_sales_opportunity_index']")).click();
        System.out.println("Check title");
        expTitle = "Open Opportunities - Opportunities - Sales";
        Thread.sleep(2000);
        actTitle = driver.getTitle();
        System.out.println(actTitle+"\n"+expTitle+"\n");
        Assert.assertEquals(expTitle,actTitle);

        System.out.println("Check page");
        expPageName = "Open Opportunities";
        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        System.out.println(actPageName+"\n"+expPageName+"\n");
        Assert.assertEquals(actPageName,expPageName);

        //7
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[5]")).click();
        driver.findElement(By.xpath("//span[.='Calls']")).click();
        System.out.println("Check title");
        expTitle = "All - Calls - Activities";
        Thread.sleep(2000);
        actTitle = driver.getTitle();
        System.out.println(actTitle+"\n"+expTitle+"\n");
        Assert.assertEquals(expTitle,actTitle);

        System.out.println("Check page");
        expPageName = "All Calls";
        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        System.out.println(actPageName+"\n"+expPageName+"\n");
        Assert.assertEquals(actPageName,expPageName);

        //8
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[5]")).click();
        driver.findElement(By.xpath("//span[.='Calendar Events']")).click();
        System.out.println("Check title");
        expTitle = "All - Calendar Events - Activities";
        Thread.sleep(2000);
        actTitle = driver.getTitle();
        System.out.println(actTitle+"\n"+expTitle+"\n");
        Assert.assertEquals(expTitle,actTitle);

        System.out.println("Check page");
        expPageName = "All Calendar Events";
        actPageName = driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        System.out.println(actPageName+"\n"+expPageName+"\n");
        Assert.assertEquals(actPageName,expPageName);

      driver.findElement(By.cssSelector("i.fa-caret-down")).click();
      driver.findElement(By.cssSelector("a[href='/user/logout']")).click();

    }
}
