package com.sogeti.qa.ui.testcases;

import com.sogeti.qa.base.TestBase;
import com.sogeti.qa.helper.WaitHelper;
import com.sogeti.qa.logger.loggerHelper;
import com.sogeti.qa.pages.HomePage;
import com.sogeti.qa.pages.ServiceAutomationPage;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC03VerifyCountryLinks extends TestBase {

    HomePage homepage;
    ServiceAutomationPage serviceautomationpage;
    private Logger log = loggerHelper.getLogger(WaitHelper.class);

    public TC03VerifyCountryLinks() {
        super();
    }

    @BeforeMethod
    public void setup() {
        initialization();
        homepage = new HomePage(driver);
        serviceautomationpage=new ServiceAutomationPage(driver);
    }


    @Test(priority=1)
    public void verifyCountryLinks() throws IOException {
        log.info("----------------------------------" + "Starting TC03 Verify Country Links" + "----------------------------------");
        homepage.acceptCookies();
        homepage.hoverServices();
        homepage.clickGlobalArrowDown();
        homepage.VerifyCountryLink();
        log.info("----------------------------------" + "Ending TC03 Verify Country Links" + "----------------------------------");


    }
}
