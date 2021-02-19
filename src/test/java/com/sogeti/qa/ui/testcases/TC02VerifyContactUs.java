package com.sogeti.qa.ui.testcases;

import com.sogeti.qa.base.TestBase;
import com.sogeti.qa.helper.WaitHelper;
import com.sogeti.qa.logger.loggerHelper;
import com.sogeti.qa.pages.HomePage;
import com.sogeti.qa.pages.ServiceAutomationPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC02VerifyContactUs extends TestBase {

    HomePage homepage;
    ServiceAutomationPage serviceautomationpage;
    private Logger log = loggerHelper.getLogger(WaitHelper.class);


    public TC02VerifyContactUs() {
        super();
    }

    @BeforeMethod
    public void setup() {
        initialization();
        homepage = new HomePage(driver);
        serviceautomationpage=new ServiceAutomationPage(driver);
    }


    @Test(priority=1)
    public void verifyContactUs() {
        log.info("----------------------------------" + "Starting TC02 Verify Page Navigation to Automation Page from Home page" + "----------------------------------");
        homepage.acceptCookies();
        homepage.hoverServices();
        homepage.clickAutomation();
        Assert.assertTrue(serviceautomationpage.verifyAutomationHeadiing());
        serviceautomationpage.enterFirstname();
        serviceautomationpage.enterLastname();
        serviceautomationpage.enterEmail();
        serviceautomationpage.enterPhoneNumber();
        serviceautomationpage.selectCountry("Germany");
        serviceautomationpage.enterMessage();
        serviceautomationpage.selectCheckbox();
        serviceautomationpage.selectCaptcha();
        //serviceautomationpage.clicksubmit();

        //Assert.assertEquals(serviceautomationpage.getBackroundColorService(),"#ff304c" );
        log.info("----------------------------------" + "Ending TC02 Verify Page Navigation to Automation Page from Home page" + "----------------------------------");


    }
}
