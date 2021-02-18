package com.sogeti.qa.pages;

import com.sogeti.qa.TestUtilities.UtilFunctions;
import com.sogeti.qa.base.TestBase;
import com.sogeti.qa.helper.WaitHelper;
import com.sogeti.qa.logger.loggerHelper;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ServiceAutomationPage extends TestBase {
    WaitHelper waitHelper;
    private Logger log = loggerHelper.getLogger(WaitHelper.class);

    @FindBy(xpath = " //div[@class='page-heading-wrapper']")
    WebElement automationHeading;

    @FindBy(xpath = " //li[@data-levelname='level2']//div[contains(@class,'wrapper')]//span[.='Services']")
    WebElement services;

    @FindBy(xpath = "  //input[@name='__field_123935']")
    WebElement labelCheckBox;

    @FindBy(xpath = "  //input[@name='__field_123927']")
    WebElement firstName;

    @FindBy(xpath = "  //input[@name='__field_123938']")
    WebElement lastName;

    @FindBy(xpath = "  //input[@name='__field_123928']")
    WebElement email;

    @FindBy(xpath = " //div[contains(@class,'Textarea ValidationRequired')]//textarea")
    WebElement messageTextArea;

    @FindBy(xpath = " //div[@class='Form__Element FormTextbox']//input")
    WebElement phoneNumber;

    @FindBy(xpath = " //span[@id='recaptcha-anchor']")
    WebElement  captchaEle;

    @FindBy(xpath = "//li[@data-levelname='level2']")
    WebElement  verifyServiceisselected;

    @FindBy(xpath = "//div[@class='mega-navbar refreshed level2 hover']/ul[@class='level1']//a[contains(text(),'Automation')]/..")
    WebElement  verifyAutomationisselected;






     public ServiceAutomationPage(WebDriver driver){
         this.driver=driver;
         PageFactory.initElements(driver, this);
         waitHelper = new WaitHelper(driver);
         log.info("initializing Automation Page");

     }

    public boolean  verifyAutomationHeadiing() {
        waitHelper.waitForElement(automationHeading, 30);
        boolean verifytxt = false;
        if(automationHeading.getAttribute("innerText").equals("Automation"))
        {
            verifytxt = true;
        }
        log.info("Done Mouse hover on 'Services' from Menu");
        return  verifytxt;

    }

    public void mouseHoverServices(String ele) {
         WebElement e=driver.findElement(By.xpath("//li[@data-levelname='level2']//div[contains(@class,'wrapper')]//span[.='"+ele+"']"));
        waitHelper.waitForElement(services, 30);
        waitHelper.waitABit(400);
        Actions actions = new Actions(driver);
        //Mouse hover menuOption 'Services'
        actions.moveToElement(e).perform();
        log.info("Done Mouse hover on 'Services' from Menu");

    }

    public void enterFirstname(){
        waitHelper.waitForElement(firstName, 30);
        firstName.sendKeys(UtilFunctions.randomString(10));
    }

    public void enterLastname(){
        waitHelper.waitForElement(lastName, 30);
        lastName.sendKeys(UtilFunctions.randomString(10));
    }

    public void enterEmail(){
        waitHelper.waitForElement(email, 30);
        email.sendKeys(UtilFunctions.randomString(10) + "@gmail.com");
    }

    public void enterPhoneNumber(){
        waitHelper.waitForElement(phoneNumber, 30);
        phoneNumber.sendKeys(UtilFunctions.randomNumber(11) );
    }

    public void enterMessage(){
        waitHelper.waitForElement(messageTextArea, 30);
        messageTextArea.sendKeys(UtilFunctions.randomString(15) );
    }

    public void selectCheckbox(){
        waitHelper.waitForElement(labelCheckBox, 30);
        labelCheckBox.click();
    }

    public void selectCountry(String country) {
        WebElement dd = driver.findElement(By.name("__field_132596"));
        Select slct = new Select(dd);
        List<WebElement> op = slct.getOptions();
        for (int i = 0; i < slct.getOptions().size(); i++) {
            String options = op.get(i).getText();
            if (options.trim().equals(country)) {
                op.get(i).click();
                break;
            }

        }

    }

    public boolean verifyLinkIsSelected(String ele) {
         boolean verifyIsSelected=false;
        String getServiceClassAttribute;
        String getAutomationClassAttribute;
        mouseHoverServices(ele);
        waitHelper.waitForElement(services, 30);
        waitHelper.waitABit(400);
        //getting background color attribute with getCssValue()
        getServiceClassAttribute  = verifyServiceisselected.getAttribute("class");
        getAutomationClassAttribute = verifyAutomationisselected.getAttribute("class");
        if(getServiceClassAttribute.contains("expanded") && getAutomationClassAttribute.contains("expanded")){
            verifyIsSelected= true;
        }
        return verifyIsSelected;
    }

    public void selectCaptcha() {
        waitHelper.waitABit(400);
        UtilFunctions.switchToFrame(2);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", captchaEle);
    }



}
