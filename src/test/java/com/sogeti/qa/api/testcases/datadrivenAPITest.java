package com.sogeti.qa.api.testcases;

import com.sogeti.qa.ExcelUtilities.ExcelLibrary;
import com.sogeti.qa.base.APIBase;
import com.sogeti.qa.helper.WaitHelper;
import com.sogeti.qa.logger.loggerHelper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.sogeti.qa.base.APIBase.apiInitialization;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class datadrivenAPITest extends APIBase {
    private Logger log = loggerHelper.getLogger(WaitHelper.class);

    @BeforeMethod
    public void setup() {
        apiInitialization();
    }

    public String sDataSheet;
    public int rowCount;




    @Test(dataProvider="TestSuites",priority=1)
    void datadrivenTest(String country, String postcode, String placenameExcel){
        RestAssured.baseURI= baseuri;
        String response =given().log().all().header("Content-Type", "application/json")
                .when().get(country+"/"+postcode)
                .then().log().all().assertThat().statusCode(200).time(lessThan(1000L)).header("Content-Type", "application/json").extract().response().asString();

        JsonPath js= new JsonPath(response);
        int count = js.getInt("places.size()");
        System.out.println(count);
        String placeName;

        for(int i=0;i<count;i++)
        {
            placeName =  js.get("places["+i+"]['place name']");
            Assert.assertEquals(placeName,placenameExcel);

        }

    }


    @DataProvider(name = "TestSuites")
    public Object[][] TestSuites() {
        ExcelLibrary xl_testData = new ExcelLibrary();

        sDataSheet = System.getProperty("user.dir") + "\\APITestData.xlsx";
        rowCount = xl_testData.getRowCount(sDataSheet, "Sheet1");


        Object[][] testData = new Object[rowCount][3];
        for (int i = 0; i < rowCount; i++) {
            testData[i][0] = xl_testData.getExcelData(sDataSheet, "Sheet1", i+1, 0);
            testData[i][1] = xl_testData.getExcelData(sDataSheet, "Sheet1", i+1, 1);
            testData[i][2] = xl_testData.getExcelData(sDataSheet, "Sheet1", i+1, 2);
        }

        return testData;
    }
}
