package com.sogeti.qa.pages;
import com.sogeti.qa.TestUtilities.UtilFunctions;

import com.sogeti.qa.base.TestBase;
import com.sogeti.qa.helper.WaitHelper;
import com.sogeti.qa.logger.loggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class HomePage extends TestBase {
    WaitHelper waitHelper;
    private Logger log = loggerHelper.getLogger(WaitHelper.class);

    @FindBy(xpath = "//button[@class='acceptCookie']")
    WebElement acceptcookies;

    @FindBy(xpath = " //li[@data-levelname='level2']//div[contains(@class,'wrapper')]//span[.='Services']")
    WebElement services;

    @FindBy(xpath = "//div[@class='mega-navbar refreshed level2 hover']/ul[@class='level1']//a[contains(text(),'Automation')]")
    WebElement automation;

    @FindBy(xpath = " //div[@class='sprite-header sprite-global-arrowdown']")
    WebElement arrowdown;

    @FindBy(xpath = "//div[@class='country-list']//ul//li/a")
    List<WebElement> countryList;





    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
        log.info("initializing Home Page");
    }

    public void acceptCookies() {

        waitHelper.waitForElement(acceptcookies, 30);
        acceptcookies.click();

    }

  /*  public void mouseHoverServices() {
        waitHelper.waitForElement(services, 30);
        waitHelper.waitABit(400);
        Actions actions = new Actions(driver);
        //Mouse hover menuOption 'Services'
        actions.moveToElement(services).perform();
        log.info("Done Mouse hover on 'Services' from Menu");

    }*/

    public ServiceAutomationPage clickAutomation() {
        waitHelper.waitForElement(automation, 30);
        waitHelper.waitABit(400);
        automation.click();
        return new ServiceAutomationPage(driver);

    }

    public void hoverServices(){
        waitHelper.waitForElement(services, 30);
        UtilFunctions.mouseHover(services);
    }

    public void clickGlobalArrowDown(){
        waitHelper.waitForElement(arrowdown, 30);
        arrowdown.click();
    }



     public void VerifyCountryLink() throws IOException {

         for(int i=0;i<countryList.size();i++)
         {
             WebElement E1= countryList.get(i);
             String url= E1.getAttribute("href");
             verifyLinks(url);

         }
     }

    public boolean verifyLinks(String linkUrl)  {
        boolean verifylinks= false;
        try
        {
            URL url = new URL(linkUrl);
           log.info(linkUrl);
            //Now we will be creating url connection and getting the response code
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()==200)
            {
                log.info("HTTP STATUS - " + httpURLConnect.getResponseMessage() + "is a working link");
                verifylinks=true;
            }

            //Fetching and Printing the response code obtained
            else{
                log.info("HTTP STATUS - " + httpURLConnect.getResponseMessage());
            }
        }catch (Exception e) {
            log.info("Unable to get the links");

        }
        return verifylinks;
    }
 }





