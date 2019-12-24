package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.concurrent.TimeUnit;

public class TestCase1_6 {

        /*
    1.  Go to “https://qa1.vytrack.com/"
    2. Login as a store manager
    3. Navigate to “Activities -> Calendar Events”
         */
        WebDriver driver;
        Actions action;
        WebDriverWait wait;
        @BeforeMethod
        public void setUp (){
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(ConfigurationReader.getProperties("url"));
            driver.findElement(By.name("_username")).sendKeys("storemanager85");
            driver.findElement(By.name("_password")).sendKeys("UserUser123"+ Keys.ENTER);
            action = new Actions(driver);
            WebElement activities = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[5]"));
            action.moveToElement(activities).perform();
            driver.findElement(By.xpath("//span[.='Calendar Events']")).click();

        }

        @Test
        public void Test1(){
        /*
       4. Verify that page subtitle "Options" is displayed
         */
            WebElement activities = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[5]"));
            action.moveToElement(activities).perform();
            driver.findElement(By.xpath("//span[.='Calendar Events']")).click();
            String options = driver.findElement(By.xpath("//div[@class='btn-group actions-group']/div")).getText();
            System.out.println("Check equals title option "+options.equals("Options"));
            WebElement opt = driver.findElement(By.xpath("//div[@class='btn-group actions-group']/div"));
            Assert.assertEquals(options,"Options");
            System.out.println("Check Options is displayed "+ opt.isDisplayed());
            Assert.assertTrue(opt.isDisplayed());
        }

        @Test
        public void Test2(){
        /*
         4.Verify that page number is equals to "1"
         */
            String attr = driver.findElement(By.cssSelector("div.pagination.pagination-centered >ul>li>input[value='1']")).getAttribute("value");
            System.out.println("check page is "+attr+"? "+attr.equals("1"));
            Assert.assertEquals(attr,"1");

        }

        @Test
        public void Test3(){
        /*
  4. Verify that view per page number is equals to "25"
         */
            String pagenumber = driver.findElement(By.xpath("//button[contains(text(), '25')]")).getText();
            System.out.println("Check page number is "+pagenumber+"? "+pagenumber.equals("25"));

            Assert.assertEquals(pagenumber,"25");


        }

        @Test
        public void Test4(){
        /*
        Verify that number of calendar events (rows in
the table) is equals to number of records
Hint: In HTML, <table> tag represents table,
 <thread> - table header, <tr> tag represents table
 row element, <th> table header cell and <td> table data.
         */


            Integer rows = driver.findElements(By.xpath("//tr[@class='grid-row row-click-action']")).size();
            String total = driver.findElement(By.xpath("//div[@class='pagination pagination-centered']//label[3]")).getText();
            String rowsC="";
            String rowsA=""+rows;
            for (int i =0; i<total.length();i++){
                if(Character.isDigit(total.charAt(i))){
                    rowsC+=total.charAt(i);
                }
            }

            System.out.println( rowsC+"\n"+rowsA+"\n"+rowsA.equals(rowsC));
            Assert.assertEquals(rowsC,rowsA);
        }

        @Test
        public void Test5(){
        /*
4. Click on the top checkbox to select all
5. Verify that all calendar events were selected
         */

            int BeforeSelectNotChecked = driver.findElements(By.xpath("//tr[@class='grid-row row-click-action']")).size();
            int BeforeSelectedChecked = driver.findElements(By.xpath("//tr[@class='grid-row row-click-action row-selected']")).size();
            System.out.println("Check how many checked boxes before click, must be 0");
            System.out.println(BeforeSelectedChecked==0);
            Assert.assertEquals(BeforeSelectedChecked,0);
            System.out.println("Check how many uncheked boxes before click");
            System.out.println(BeforeSelectNotChecked);
            System.out.println("Click on all check boxes");
            driver.findElement(By.xpath("//button/input[@type='checkbox']")).click();

            int AfterSelectAllNotChekedBoxes = driver.findElements(By.xpath("//tr[@class='grid-row row-click-action']")).size();
            int AfterSelectedAllChekedBoxes = driver.findElements(By.xpath("//tr[@class='grid-row row-click-action row-selected']")).size();
            String AfterSelectedAllChekedBoxesS=""+AfterSelectedAllChekedBoxes;
            System.out.print("Should be zero not selected boxes, after selection: ");
            System.out.print(AfterSelectAllNotChekedBoxes==BeforeSelectedChecked);
            Assert.assertEquals(AfterSelectAllNotChekedBoxes,BeforeSelectedChecked);
            String totalBoxesString = driver.findElement(By.xpath("//div[@class='pagination pagination-centered']//label[3]")).getText();
            String totalBoxes="";
            for (int i =0; i<totalBoxesString.length();i++){
                if(Character.isDigit(totalBoxesString.charAt(i))){
                    totalBoxes+=totalBoxesString.charAt(i);
                }
            }
            System.out.println("\n"+"Check that all cheked boxes equal to all boxes on the page: ");

            System.out.println(AfterSelectedAllChekedBoxesS.equals(totalBoxes));
            Assert.assertEquals(AfterSelectedAllChekedBoxesS,totalBoxes);



        }

        @Test
        public void Test6() throws InterruptedException {
        /*
 4.Select “Testers meeting”
 5.Verify that following data is displayed:
         */
            WebElement click = driver.findElement(By.xpath("//tr[@class='grid-row row-click-action']/td[contains(text(),'Testers Meeting')]/.."));
            Thread.sleep(5000);
            action.moveToElement(click).click(click).build().perform();
            WebElement GIE = driver.findElement(By.xpath("//div[@class='row-fluid form-horizontal']"));
            String GeneralInformation = GIE.getText();

            System.out.println("General info is displayed ? "+GIE.isDisplayed());
            Assert.assertTrue(GIE.isDisplayed());

            String [][] GeneralInfo = {{"Title","Testers Meeting"},{"Description","This is a a weekly testers meeting"},
                    {"Start","Nov 27, 2019, 9:30 AM"} ,{"End","Nov 27, 2019, 10:30 AM"},
                    {"All-day Event","No"},{"Organizer","Stephan Haley"},{"Guests","Tom Smith - Required"},
                    {"Recurrence","Weekly every 1 week on Wednesday"},{"Call Via Hangout","No"}};

            int count=0;
            System.out.println();

            for(int i = 0;i<9;i++){
                for (int j =0;j<2;j++){
                    if(GeneralInformation.contains(GeneralInfo[i][j])){
                        count++;
                        System.out.println(GeneralInfo[i][j]+" true");
                    } else{
                        System.out.println(GeneralInfo[i][j]+" false");
                    }
                }
            }
            System.out.println();
            System.out.println("All info been displayed?");
            System.out.println(count==18);
            Assert.assertEquals(count,18);
        }

    }

