package api.chucknorris.io.tests.random.requests;

import api.chucknorris.io.tests.base.tests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RandomRequest extends BaseTest {


    @Step("Realizar a busca de dizeres conforme catregoria especifica")
    public Response buscaDizeresCategoria(String categoria) {
        return given()
                .when()
                .queryParam("category",categoria)
                .get("random");
    }

    @Step("Realizar a busca de dizeres conforme randomicamente")
    public Response buscaDizeresRandom() {
        return given()
                .when()
                .get("random");
    }
}
