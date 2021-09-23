package api.chucknorris.io.tests.base.tests;

import org.junit.BeforeClass;
import io.restassured.RestAssured;
import api.chucknorris.io.utils.Utils;

public class BaseTest {

    @BeforeClass
    public static void setup() {
    RestAssured.baseURI = Utils.getURL();

    }
}
