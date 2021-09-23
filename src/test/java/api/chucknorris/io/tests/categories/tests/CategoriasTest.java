package api.chucknorris.io.tests.categories.tests;


import api.chucknorris.io.suites.AllTests;
import api.chucknorris.io.suites.SchemaTests;
import api.chucknorris.io.tests.base.tests.BaseTest;
import api.chucknorris.io.tests.categories.requests.CategoriasRequest;
import api.chucknorris.io.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.lessThan;


@Feature("")
public class CategoriasTest extends BaseTest {

    CategoriasRequest categoria = new CategoriasRequest();

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar status code 200 e tempo de resposta abaixo de 3000ms ao buscar categorias")
    public void testBasicResponseSearchCategoires() throws Exception {
        categoria.buscaCategorias().then().statusCode(200).time(lessThan(3L), TimeUnit.SECONDS);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Category({SchemaTests.class})
    @DisplayName("Validar schema do retorno das categorias")
    public void testValidatedContractResponseCategories() throws Exception {

    categoria.buscaCategorias().then().statusCode(200).assertThat().body(matchesJsonSchema(new File(Utils.getSchemaBasePath("categories", "categorias"))));



    }



}
