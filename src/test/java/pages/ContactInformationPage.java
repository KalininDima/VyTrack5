package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactInformationPage extends BasePage {

    @FindBy(xpath = "//h1[@class='user-name']")
    public WebElement contactFullName;

    @FindBy(css = "a.phone")
    public WebElement phone;

    @FindBy(css = "a.email")
    public WebElement email;


}
