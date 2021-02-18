package com.sogeti.qa.TestUtilities;

import com.sogeti.qa.base.TestBase;
import com.sogeti.qa.helper.WaitHelper;
import com.sogeti.qa.logger.loggerHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

public class UtilFunctions extends TestBase {

    WaitHelper waitHelper;
    private Logger log = loggerHelper.getLogger(WaitHelper.class);


    public static void mouseHover(WebElement ele) {
        Actions actions = new Actions(driver);
        //Mouse hover menuOption 'Services'
        actions.moveToElement(ele).perform();
    }


    public static String getBackroundColorService(WebElement ele) {
        mouseHover(ele);
        //getting background color attribute with getCssValue()
        String rgbFormat  = ele.getCssValue("background-color");
        String hexcolor = Color.fromString(rgbFormat).asHex();
        System.out.println(Color.fromString(rgbFormat).asHex());
        return Color.fromString(rgbFormat).asHex(); //converted Into HexFormat

    }

    public static String randomString(int length) {
        String randomval=null;
        for (int i = 0; i < 5; i++) {
             randomval= (RandomStringUtils.randomAlphabetic(length));
        }
        return randomval;
    }

    public static String randomNumber(int length) {
        String randomnum=null;
        for (int i = 0; i < 5; i++) {
            randomnum= (RandomStringUtils.randomNumeric(length));
        }
        return randomnum;
    }

    public static void switchToFrame(int index) {
        logger.info( "switching to frame by element");
        driver.switchTo().frame(index);
        logger.info( index);
    }

}
