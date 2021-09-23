package api.chucknorris.io.tests.search.tests;


import api.chucknorris.io.suites.AllTests;
import api.chucknorris.io.suites.SchemaTests;
import api.chucknorris.io.tests.base.tests.BaseTest;
import api.chucknorris.io.tests.search.requests.SearchRequest;
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

@Feature("")
public class SearchTest extends BaseTest {

    SearchRequest search = new SearchRequest();

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar status code 200 e tempo de resposta abaixo de 3000ms conforme busca textual")
    public void testBasicResponseSearchText() throws Exception {
        String texto = Utils.randomTextForSearch();
        search.searchDizeres(texto).then().statusCode(200).time(lessThan(3L), TimeUnit.SECONDS);

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Category({SchemaTests.class})
    @DisplayName("Validar schema do retorno de dizeres na busca por texto")
    public void testValidatedContractResponseEspecidyCategory() throws Exception {

        String texto = Utils.randomTextForSearch();
        search.searchDizeres(texto).then().statusCode(200).assertThat().body(matchesJsonSchema(new File(Utils.getSchemaBasePath("search", "searchtext"))));

    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar consulta de dizeres através de busca textual valida")
    public void testValidResponseBySearchValidText() throws Exception {
        String texto = Utils.randomTextForSearch();
        search.searchDizeres(texto).then().statusCode(200).body(containsString("value"));;

    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar consulta de dizeres através de uma busca por termo inexistente")
    public void testValidResponseBySearchInvalidText() throws Exception {
        String texto = "camisa";
        search.searchDizeres(texto).then().statusCode(200).body(containsString("[]"));

    }

}
