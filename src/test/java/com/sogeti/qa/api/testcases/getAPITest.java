package com.sogeti.qa.api.testcases;

import com.sogeti.qa.base.APIBase;
import com.sogeti.qa.base.TestBase;
import com.sogeti.qa.helper.WaitHelper;
import com.sogeti.qa.logger.loggerHelper;
import com.sogeti.qa.pages.HomePage;
import com.sogeti.qa.pages.ServiceAutomationPage;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.sogeti.qa.base.APIBase.apiInitialization;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class getAPITest extends APIBase {
    private Logger log = loggerHelper.getLogger(WaitHelper.class);

    @BeforeMethod
    public void setup() {
        apiInitialization();

    }

    @Test
    void verifyResponseTimeAPI1()
    {
        RequestSpecification requestSpec = new RequestSpecBuilder().build();
        requestSpec.baseUri(baseuri);
        requestSpec.basePath(basepath);
        Response response = given().spec(requestSpec).get();
        log.info(response.getTime());
        Assert.assertTrue( response.getTime() < 1000L );
    }





    @Test
    void getAPITest(){
        String placename;
        String postalcode;
        RestAssured.baseURI= baseuri;
        String response =given().log().all().header("Content-Type", "application/json")
                .when().get(basepath)
                .then().assertThat().log().all().statusCode(200).body("country", equalTo("Germany")).body("state",equalTo("Baden-Württemberg"))
                .header("Content-Type", "application/json").extract().response().asString();

        JsonPath js= new JsonPath(response);
        int count = js.getInt("places.size()");

        for(int i=0;i<count;i++)
        {
             placename =  js.get("places["+i+"]['place name']");
             postalcode =  js.get("places["+i+"]['post code']");
            log.info(placename);
            log.info(postalcode);
            if(placename.trim().equals("Stuttgart Degerloch") && postalcode.trim().equals("70597")){
                log.info("response has postalcode and placename");
                break;
            }

        }


    }
}
