package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

import java.util.List;

public class CreateCalendarEventsPage extends BasePage{
    public CreateCalendarEventsPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[id^='recurrence-repeat-view']")
    public WebElement repeat;

    @FindBy(css = "[id^='recurrence-repeats-view']")
    public WebElement repeatOptions;

    @FindBy(className = "select2-chosen")
    public WebElement selectedOwner;

    @FindBy(css = "input[id^='oro_calendar_event_form_title-']")
    public WebElement title;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDate;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    public WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTime;

    @FindBy(xpath = "(//input[@type='radio'])[1]")
    public WebElement days;

    @FindBy(xpath = "(//input[@type='radio'])[2]")
    public WebElement weekday;

    @FindBy(xpath = "(//input[@type='radio'])[3]")
    public WebElement never;

    @FindBy(xpath = "(//input[@type='radio'])[4]")
    public WebElement after;

    @FindBy(xpath = "(//input[@type='radio'])[5]")
    public WebElement by;

    @FindBy(xpath = "//a[@title='Cancel']")
    public WebElement Cancel;

    @FindBy(xpath = "//div[@class='btn-group pull-right']/a")
    public WebElement dropdownSaveAndClose;

    @FindBy(xpath = "(//ul[@class='dropdown-menu'])[3]/li")
    public List<WebElement> listOfSaC;


    // повторы
    @FindBy(xpath="//input[@class='input-small timepicker-input start ui-timepicker-input']")
    public WebElement cellStartTime;

    @FindBy(xpath = "(//div[@class='ui-timepicker-wrapper ui-timepicker-positioned-top'])[1]")
    public WebElement listOfStartTime;


    @FindBy(xpath = "//input[@class='input-small timepicker-input end ui-timepicker-input']")
    public WebElement cellEndTime;

    @FindBy(xpath = "(//div[@class='ui-timepicker-wrapper ui-timepicker-positioned-top'])[2]")
    public WebElement listOfEndTime;

    @FindBy(xpath = "//ul[@class='ui-timepicker-list']/li[.='9:00 PM']")
    public WebElement ChoiseStartTime;

    @FindBy(xpath = "//input[@name='oro_calendar_event_form[allDay]']")
    public WebElement BoxAllDayEvent;

    @FindBy(xpath = "//input[@class='input-small datepicker-input start hasDatepicker']")
    public WebElement CellStartDay;

    @FindBy(xpath = "//input[@class='input-small datepicker-input end hasDatepicker']")
    public WebElement CellEndDay;

    @FindBy(xpath = "//input[@data-name='recurrence-repeat']")
    public WebElement repeatBox;

    @FindBy(xpath = "//select[@class='recurrence-repeats__select']")
    public WebElement repeatAllListSelection;

    @FindBy(xpath = "//select[@class='recurrence-repeats__select']/option")
    public WebElement repeatShowFirstOptionInList;

    @FindBy(xpath ="//div[@class='control-group control-group-choice']//span")
    public WebElement repeatSelectedOption;

    @FindBy(xpath = "//select[@class='recurrence-repeats__select']/option[.='Daily']")
    public WebElement dailyOptionInList;

    @FindBy(xpath = "//select[@class='recurrence-repeats__select']/option[.='Weekly']")
    public WebElement weeklyOptionInList;

    @FindBy(xpath = "//input[@value='monday']")
    public WebElement repeatOnMonday;

    @FindBy(xpath = "//input[@value='friday']")
    public WebElement repeatOnFriday;

    @FindBy(xpath = "//div[@class='controls recurrence-subview-control__items']//input[@checked='checked']")
    public WebElement radioRepeatEvery;

    @FindBy (xpath ="//div[@data-name='recurrence-ends']//label[.='Ends']/../..//span[.='Never']/../input")
    public WebElement radioNeverInEndsOption;

    @FindBy(xpath = "//div[@data-name='recurrence-ends']//label[.='Ends']/../..//span[.='After']/../input[@type='radio']")
    public WebElement radioAfterInEndsOption;

    @FindBy(xpath = "//div[@data-name='recurrence-ends']//label[.='Ends']/../..//span[.='By']/../input")
    public WebElement radioByInEndsOption;

    @FindBy(xpath ="//input[@class='datepicker-input hasDatepicker']")
    public WebElement DateCellInEnds;

    @FindBy(xpath = "//input[@class='datepicker-input hasDatepicker ui-datepicker-dialog-is-above']")
    public WebElement DateCellTableEnds;

    @FindBy(xpath = "//select[@class='ui-datepicker-year']/option")
    public WebElement YearsCellInEnds;

    @FindBy(xpath = "//select[@class='ui-datepicker-month']/option")
    public WebElement MonthCellInEnds;


    @FindBy(xpath = "//div[@class=\"recurrence-subview-control__item\"]//span[.=\"After\"]/../input[@type='text']")
    public WebElement CellInAfterEnds;

    @FindBy(xpath = "//div[@data-name='recurrence-summary']/div")
    public WebElement summary;

    @FindBy(xpath = "//div[@class='control-group recurrence-summary alert-info']//label[.='Summary:']")
    public WebElement summaryClick;

    public Select repeatOptionsList(){
        return new Select(repeatOptions);
    }

}