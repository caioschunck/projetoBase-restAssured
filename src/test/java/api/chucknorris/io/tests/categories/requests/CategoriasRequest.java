package api.chucknorris.io.tests.categories.requests;

import api.chucknorris.io.tests.base.tests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CategoriasRequest extends BaseTest {

    @Step("Realizar a busca de categorias disponíveis")
    public Response buscaCategorias() {
        return given()
                .when()
                .get("categories");
    }

}
