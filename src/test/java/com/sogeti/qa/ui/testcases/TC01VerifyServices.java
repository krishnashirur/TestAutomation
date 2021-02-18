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

public class TC01VerifyServices extends TestBase {
    HomePage homepage;
    ServiceAutomationPage serviceautomationpage;
    private Logger log = loggerHelper.getLogger(WaitHelper.class);


    public TC01VerifyServices() {
        super();
    }

    @BeforeMethod
    public void setup() {
        initialization();
        homepage = new HomePage(driver);
        serviceautomationpage=new ServiceAutomationPage(driver);
    }


    @Test(priority=1)
    public void verifyPageNavigationAndLinkSelection() {
        log.info("----------------------------------" + "Starting TC01 Verify Page Navigation to Automation Page from Home page" + "----------------------------------");
        homepage.acceptCookies();
        homepage.hoverServices();
        homepage.clickAutomation();
        Assert.assertTrue(serviceautomationpage.verifyAutomationHeadiing());
        serviceautomationpage.verifyLinkIsSelected("Services");
        //li[@data-levelname='level2']
        log.info("----------------------------------" + "Ending TC01 Verify Page Navigation to Automation Page from Home page" + "----------------------------------");


    }

}
