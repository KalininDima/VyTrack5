package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CalendarEventsPage;
import pages.CreateCalendarEventsPage;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.TestBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestCase4 extends TestBase {
    LoginPage loginPage;
    String username = ConfigurationReader.getProperties("store_manager_username");
    String password = ConfigurationReader.getProperties("standartPassword");
    DashboardPage dashboardPage;
    CalendarEventsPage calendarEventsPage;
    CreateCalendarEventsPage createCalendarEventsPage;


    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage();
        loginPage.login(username, password);
        dashboardPage = new DashboardPage();
        dashboardPage.navigateToModule("Activities", "Calendar Events");
        calendarEventsPage = new CalendarEventsPage();
        createCalendarEventsPage = new CreateCalendarEventsPage();

    }


    @Test
    public void Test1() {
    /*
Test Case #1
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Hover on three dots “...” for “Testers meeting”
calendar event
5. Verify that “view”, “edit” and “delete” options are available
 */


        actions.moveToElement(calendarEventsPage.TreeDots).build().perform();
        for (WebElement each : calendarEventsPage.DVE) {
            System.out.println(each.getText());
            if (each.getAttribute("title").equals("View")) {
                Assert.assertTrue(each.isDisplayed());
                System.out.println(each.getAttribute("title") + " is  Displayed? " + each.isDisplayed());
            } else if (each.getAttribute("title").equals("Delete")) {
                Assert.assertTrue(each.isDisplayed());
                System.out.println(each.getAttribute("title") + " is  Displayed? " + each.isDisplayed());
            } else if (each.getAttribute("title").equals("Edit")) {
                Assert.assertTrue(each.isDisplayed());
                System.out.println(each.getAttribute("title") + " is  Displayed? " + each.isDisplayed());
            }

        }


    }

    @Test
    public void Test2() throws InterruptedException {
//    Test Case #2
//1. Go to “https://qa1.vytrack.com/"
//2. Login as a store manager
//3. Navigate to “Activities -> Calendar Events”
//4. Click on “Grid Options” button
//5. Deselect all options except “Title”
//6. Verify that “Title” column still displayed
//     */


        Thread.sleep(1000);
        calendarEventsPage.GridSetings.click();
        int columnMover = 0;
        for (WebElement each : calendarEventsPage.AllOptionsNameInGridSetings) {
            columnMover++;
            WebElement elementCheckBoxes = driver.findElement(By.xpath("//tr[" + columnMover + "]/td[@class='visibility-cell']/input"));

            if (!each.getText().equals("Title")) {
                System.out.println(each.getText() + " Is it selected? " + elementCheckBoxes.isSelected());
                Assert.assertTrue(elementCheckBoxes.isSelected());
                System.out.println("deselecting it...");
                each.click();
                System.out.println(each.getText()
                        + " Check again , is it selected? " + elementCheckBoxes.isSelected());
                Assert.assertFalse(elementCheckBoxes.isSelected());
            } else {
                System.out.println(each.getText() + " equals Title? " + each.getText().equals("Title"));
                Assert.assertEquals(each.getText(), "Title");
                System.out.println(each.getText() + " is selected? " + elementCheckBoxes.isSelected());
                Assert.assertTrue(elementCheckBoxes.isSelected());
            }

            System.out.println("=================");
        }
        System.out.println("Title column is displayed? " + calendarEventsPage.TitleColumn.isDisplayed());
        Assert.assertTrue(calendarEventsPage.TitleColumn.isDisplayed());


    }

    @Test
    public void Test3() {

    /*
    Test Case #3
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Expand “Save And Close” menu
6. Verify that “Save And Close”, “Save And New”
and “Save” options are available
     */

        calendarEventsPage.createCalendarEvent.click();
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        createCalendarEventsPage.dropdownSaveAndClose.click();
        for (WebElement each : createCalendarEventsPage.listOfSaC) {
            if (each.getText().trim().equals("Save And Close")) {
                System.out.println(each.getText() + " is it displayed? " + each.isDisplayed());
                Assert.assertTrue(each.isDisplayed());
                Assert.assertEquals(each.getText().trim(), "Save And Close");
            } else if (each.getText().trim().equals("Save And New")) {
                System.out.println(each.getText() + " is displayed? " + each.isDisplayed());
                Assert.assertEquals(each.getText().trim(), "Save And New");
                Assert.assertTrue(each.isDisplayed());
            } else if (each.getText().trim().equals("Save")) {
                System.out.println(each.getText() + " is displayed? " + each.isDisplayed());
                Assert.assertTrue(each.isDisplayed());
                Assert.assertEquals(each.getText().trim(), "Save");
            }

        }


    }

    @Test
    public void Test4() {
         /*
Test Case #4
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Then, click on “Cancel” button
6. Verify that “All Calendar Events” page subtitle is displayed
          */
        calendarEventsPage.createCalendarEvent.click();
        CreateCalendarEventsPage createCalendarEventsPage = new CreateCalendarEventsPage();
        BrowserUtils.waitForClickablility(createCalendarEventsPage.Cancel,5);
        createCalendarEventsPage.Cancel.click();
        String title = "All Calendar Events";
        BrowserUtils.waitFor(3);
        WebElement titleE = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        String titleFromE = titleE.getText().trim();
        System.out.println("All Calendar Events is equal with sub title name? " + title.equals(titleFromE));
        Assert.assertEquals(titleFromE, title);
        System.out.println(title + " is Displayed? " + titleE.isDisplayed());
        Assert.assertTrue(titleE.isDisplayed());

    }

    @Test
    public void Test5() throws ParseException {
         /*
         Test Case #5
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Verify that difference between end and start time is exactly 1 hour
          */

        calendarEventsPage.createCalendarEvent.click();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM a");
        BrowserUtils.waitFor(2);
        String ST = createCalendarEventsPage.cellStartTime.getAttribute("value");
        System.out.println("Start time " + ST);
        long StartTime = sdf.parse(ST).getTime() / 3600000;
        String ET = createCalendarEventsPage.cellEndTime.getAttribute("value");
        long EndTime = sdf.parse(ET).getTime() / 3600000;
        System.out.println("End time " + ET);
        System.out.println("Difference 1 hour between them ?" + (EndTime - StartTime == 1));
        Assert.assertEquals(EndTime - StartTime, 1);

    }

    @Test
    public void Test6() {
         /*
         Test Case #6
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Select “9:00 PM” as a start time
6. Verify that end time is equals to “10:00 PM”
          */
        calendarEventsPage.createCalendarEvent.click();
        BrowserUtils.waitFor(1);
        createCalendarEventsPage.cellStartTime.click();
        BrowserUtils.waitFor(1);
        createCalendarEventsPage.ChoiseStartTime.click();
        String getTime = createCalendarEventsPage.cellEndTime.getAttribute("value");
        System.out.println("Is it 10:00 pm? " + getTime + "\n" + getTime.equals("10:00 PM"));
        Assert.assertEquals(getTime, "10:00 PM");

    }

    @Test
    public void Test7() {

         /*
         Test Case #7
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Select “All-Day Event” checkbox
6. Verify that “All-Day Event” checkbox is selected
7. Verify that start and end time input boxes are
not displayed
8. Verify that start and end date input boxes are
displayed
          */

        calendarEventsPage.createCalendarEvent.click();
        createCalendarEventsPage.BoxAllDayEvent.click();
        System.out.println("Check box 'All-Day Event' is selected? " + createCalendarEventsPage.BoxAllDayEvent.isSelected());
        Assert.assertTrue(createCalendarEventsPage.BoxAllDayEvent.isSelected());
        System.out.println("Start cell time is displayed? " + createCalendarEventsPage.cellStartTime.isDisplayed() + "\n" +
                "End cell time is displayed? " + createCalendarEventsPage.cellEndTime.isDisplayed());
        Assert.assertFalse(createCalendarEventsPage.cellEndTime.isDisplayed());
        Assert.assertFalse(createCalendarEventsPage.cellStartTime.isDisplayed());
        System.out.println("Start date cell is available? " + createCalendarEventsPage.CellStartDay.isDisplayed() + "\n"
                + "End date cell is available? " + createCalendarEventsPage.CellEndDay.isDisplayed());
        Assert.assertTrue(createCalendarEventsPage.CellStartDay.isDisplayed());
        Assert.assertTrue(createCalendarEventsPage.CellEndDay.isDisplayed());
    }

    @Test
    public void Test8() {
         /*
         Test Case #8
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Select “Repeat” checkbox
6. Verify that “Repeat” checkbox is selected
7. Verify that “Daily” is selected by default and
following options are available in   “Repeats” drop-down:
          */
        calendarEventsPage.createCalendarEvent.click();
        createCalendarEventsPage.repeatBox.click();
        System.out.println("Repeat check box is selected? " + createCalendarEventsPage.repeatBox.isSelected());
        Assert.assertTrue(createCalendarEventsPage.repeatBox.isSelected());
        // Select select = new Select(createCalendarEventsPage.repeatAllListSelection);
        createCalendarEventsPage.repeatAllListSelection.click();

        String stringofrepeat = createCalendarEventsPage.repeatAllListSelection.getText();
        String[] listofrepeat = stringofrepeat.split("\n");
        int expectedCount = listofrepeat.length;
        int actualCount =0;
        for (String each : listofrepeat) {
            if (each.equals("Daily")) {
                System.out.println(each + " is selected? " + createCalendarEventsPage.dailyOptionInList.isSelected());
                Assert.assertTrue(createCalendarEventsPage.dailyOptionInList.isSelected());
                actualCount++;
            } else if (!each.equals("Daily")) {
                System.out.println(each + "is available? " +
                        driver.findElement(By.xpath("//select[@class='recurrence-repeats__select']/option[.='" + each + "']")).isEnabled());
                Assert.assertTrue(driver.findElement(By.xpath("//select[@class='recurrence-repeats__select']/option[.='" + each + "']")).isEnabled());
                actualCount++;
            }
        }
        System.out.println("All options are avalaible? "+(actualCount==expectedCount));
        Assert.assertEquals(actualCount,expectedCount);


    }

    @Test
    public void Test9() {
         /*
         Test Case #9
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Select “Repeat” checkbox
6. Verify that “Repeat” checkbox is selected
7. Verify that “Repeat Every” radio button is
selected
8. Verify that “Never” radio button is selected as an
“Ends” option.
9. Verify that following message as a summary is
displayed: “Summary: Daily every 1 day”
          */
calendarEventsPage.createCalendarEvent.click();
createCalendarEventsPage.repeatBox.click();
        System.out.println("Repeat check box is selected? "+createCalendarEventsPage.repeatBox.isSelected()+"\n"+
                "Radio button 'Repeat every' is selected? "+createCalendarEventsPage.radioRepeatEvery.isSelected());
        Assert.assertTrue(createCalendarEventsPage.repeatBox.isSelected());
        Assert.assertTrue(createCalendarEventsPage.radioRepeatEvery.isSelected());
        System.out.println("Radio button Never in Ends Option is selected? "+
                createCalendarEventsPage.radioNeverInEndsOption.isSelected());
        Assert.assertTrue(createCalendarEventsPage.radioNeverInEndsOption.isSelected());

        String summary = createCalendarEventsPage.summaryClick.getText();
            System.out.println("Daily every 1 day is equal to? "+summary+" "+summary.equals("Daily every 1 day"));
            Assert.assertTrue(summary.equals("Daily every 1 day"));
        System.out.println("the text " +summary+" is displayed? "+createCalendarEventsPage.summaryClick.isDisplayed());
        Assert.assertTrue(createCalendarEventsPage.summaryClick.isDisplayed());

    }

    @Test
    public void Test10() {
         /*
         Test Case #10
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Select “Repeat” checkbox
6. Select “After 10 occurrences” as an “Ends”
option.
7. Verify that following message as a summary is
displayed: “Summary: Daily every 1 day, end after 10 occurrences”
          */

        BrowserUtils.waitFor(2);
         calendarEventsPage.createCalendarEvent.click();
        extentTest.info("step 2");
         createCalendarEventsPage.repeatBox.click();
        extentTest.info("step 3");
         createCalendarEventsPage.radioAfterInEndsOption.click();
        extentTest.info("step 4");
         createCalendarEventsPage.CellInAfterEnds.sendKeys("10");
       createCalendarEventsPage.summary.click();
        extentTest.info("step 5");
         String strSummary = createCalendarEventsPage.summary.getText();
        extentTest.info("step 6");
        System.out.println(strSummary+" |is equal to 'Daily every 1 day, end after 10 occurrences': "+
                strSummary.equals("Daily every 1 day, end after 10 occurrences")+"\n"+"Is it displayed? "+
                createCalendarEventsPage.summary.isDisplayed());
        extentTest.info("step 7");
        Assert.assertEquals(strSummary,"Daily every 1 day, end after 10 occurrences");
        extentTest.pass("great");


    }

    @Test
    public void Test11() {
         /*
         Test Case #11
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Select “Repeat” checkbox
6. Select “By Nov 18, 2021” as an “Ends” option.
7. Verify that following message as a summary is
displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”
          */
        extentTest = extentReports.createTest("Second fucking test");
         extentTest.info("click on 'Create Calendar Event'");
         calendarEventsPage.createCalendarEvent.click();

         extentTest.info("Click on 'Repeat' checkbox");
         createCalendarEventsPage.repeatBox.click();

         createCalendarEventsPage.radioByInEndsOption.click();

         createCalendarEventsPage.DateCellInEnds.click();
         createCalendarEventsPage.DateCellTableEnds.sendKeys("Nov 18, 2021");


         String strSummary = createCalendarEventsPage.summary.getText();
        System.out.println(strSummary+" |is equal to 'Daily every 1 day, end by Nov 18, 2021': "+
                strSummary.equals("Daily every 1 day, end by Nov 18, 2021")+"\n"+"Is it displayed? "+
                createCalendarEventsPage.summary.isDisplayed());
        extentTest.info("step 7");
        Assert.assertEquals(strSummary,"Daily every 1 day, end by Nov 18, 2021");
        extentTest.pass("great");



    }

    @Test
    public void Test12() {
         /*
         Test Case #12
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Select “Repeat” checkbox
6. Select “Weekly” options as a “Repeat” option
7. Select “Monday and Friday” options as a
“Repeat On” options
8. Verify that “Monday and Friday” options are
selected
9. Verify that following message as a summary is
displayed: “Summary: Weekly every 1 week on Monday, Friday”
          */
         calendarEventsPage.createCalendarEvent.click();

         createCalendarEventsPage.repeatBox.click();
        System.out.println(createCalendarEventsPage.repeatOptions.getText());
        createCalendarEventsPage.weeklyOptionInList.click();
        createCalendarEventsPage.repeatOnFriday.click();
        createCalendarEventsPage.repeatOnMonday.click();
        System.out.println(createCalendarEventsPage.repeatOnFriday.isSelected()+"\n"+
                createCalendarEventsPage.repeatOnMonday.isSelected());
        String summarystr = createCalendarEventsPage.summary.getText();

        System.out.println(summarystr+" is equals with 'Weekly every 1 week on Monday, Friday' "+
                summarystr.equals("Weekly every 1 week on Monday, Friday")+"\n"+
                createCalendarEventsPage.summary.isDisplayed());
    }
}





