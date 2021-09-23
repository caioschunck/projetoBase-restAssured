package api.chucknorris.io.tests.random.tests;

import api.chucknorris.io.suites.AllTests;
import api.chucknorris.io.suites.SchemaTests;
import api.chucknorris.io.tests.base.tests.BaseTest;
import api.chucknorris.io.tests.random.requests.RandomRequest;
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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

@Feature("Validar funcionamento do endpoint de dizeres aleatorios")
public class RandomTest extends BaseTest {

    RandomRequest random = new RandomRequest();

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar status code 200 e tempo de resposta abaixo de 3000ms ao buscar dizeres de uma determinada categoria")
    public void testBasicResponseSearchEspecifyCategoires() throws Exception {
       String categoria = Utils.randomCategoriesForSearch();
       random.buscaDizeresCategoria(categoria).then().statusCode(200).time(lessThan(3L), TimeUnit.SECONDS);

        }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Category({SchemaTests.class})
    @DisplayName("Validar schema do retorno de dizeres por categoria")
    public void testValidatedContractResponseEspecidyCategory() throws Exception {

      String categoria = Utils.randomCategoriesForSearch();
      random.buscaDizeresCategoria(categoria).then().statusCode(200).assertThat().body(matchesJsonSchema(new File(Utils.getSchemaBasePath("random", "randomcategoria"))));

    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar consulta de dizeres através de uma categoria válida")
    public void testValidResponseBySearchValidCategory() throws Exception {
        String categoria = Utils.randomCategoriesForSearch();
       random.buscaDizeresCategoria(categoria).then().statusCode(200).time(lessThan(3L), TimeUnit.SECONDS);

    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar consulta de dizeres através de uma categoria inválida/inexistente")
    public void testValidResponseBySearchInvalidCategory() throws Exception {
        String categoria = "teste";
        random.buscaDizeresCategoria(categoria).then().statusCode(404).body(containsString("Not Found"));

    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar status code 200 e tempo de resposta abaixo de 3000ms ao buscar dizeres randomicamente")
    public void testBasicResponseSearchRandomCategoires() throws Exception {
        random.buscaDizeresRandom().then().statusCode(200).time(lessThan(3L), TimeUnit.SECONDS);

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Category({SchemaTests.class})
    @DisplayName("Validar schema do retorno de dizeres randomicamente")
    public void testValidatedContractResponseRandomCategory() throws Exception {

        random.buscaDizeresRandom().then().statusCode(200).assertThat().body(matchesJsonSchema(new File(Utils.getSchemaBasePath("random", "randomcategoria"))));

    }



}
