package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

import java.util.List;

public class CalendarEventsPage extends BasePage{
    List<WebElement> all = Driver.get().findElements(By.xpath("//tr[@class='renderable']/td[@class='title-cell']"));
    public CalendarEventsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEvent;

    @FindBy(xpath = "//tr[@class='grid-row row-click-action']/td[.='Testers Meeting']")
    public WebElement TestersMeeting;


    @FindBy(xpath ="//td[.='Testers Meeting']/..//a[@class='dropdown-toggle']")
    public WebElement TreeDots;

    @FindBy(xpath = "//td[.='Testers Meeting']/..//div[@class='dropdown open']")
    public WebElement windowThreDots;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu__action-cell launchers-dropdown-menu detach dropdown-menu__floating']//li[@class='launcher-item']/a")
    public List<WebElement> DVE;



    @FindBy(xpath = "//a[@title='Grid Settings']")
    public WebElement GridSetings;


   @FindBy(xpath="//tr/td[@class='title-cell']/label")
    public List <WebElement> AllOptionsNameInGridSetings;

//   @FindBy(xpath = "//tr[1]/td[@class='visibility-cell']/input")
//   public WebElement CheckboxAlternate;

   @FindBy(xpath = "//th/a[.='Title']")
    public WebElement TitleColumn;

   @FindBy(xpath ="/..//tr/td[@class='visibility-cell']")
    public WebElement checkbox;

   @FindBy(xpath = "//a[@data-role='column-manager-show-selected']")
    public WebElement SelectedButton;



}