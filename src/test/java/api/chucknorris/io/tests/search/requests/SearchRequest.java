package api.chucknorris.io.tests.search.requests;

import api.chucknorris.io.tests.base.tests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SearchRequest extends BaseTest {

    @Step("Consultar dizeres conforme pesquisa de texto")
    public Response searchDizeres (String texto) {
        return given()
                .when()
                .queryParam("query",texto)
                .get("search");
    }

}
