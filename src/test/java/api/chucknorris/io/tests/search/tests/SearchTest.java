package api.chucknorris.io.tests.search.tests;


import api.chucknorris.io.suites.AllTests;
import api.chucknorris.io.suites.ContractTests;
import api.chucknorris.io.tests.base.tests.BaseTest;
import api.chucknorris.io.tests.search.requests.SearchRequest;
import api.chucknorris.io.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

@Feature("")
public class SearchTest extends BaseTest {

    SearchRequest search = new SearchRequest();

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar status code 200 e tempo de resposta abaixo de 3000ms conforme busca textual")
    public void testBasicResponseSearchText() throws Exception {
        String texto = Utils.randomTextForSearch();
        long tempoResposta =search.searchDizeres(texto).then().statusCode(200).extract().time();
        Response buscaDizeres = search.searchDizeres(texto);

        if(buscaDizeres.getStatusCode() == 200 && tempoResposta < 3000){
            System.out.println("Request realizada com sucesso ");
        } else {
            Assert.fail("Retorno da equest não corresponde as condições");


        }

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Category({ContractTests.class})
    @DisplayName("Validar contrato do retorno de dizeres na busca por texto")
    public void testValidatedContractResponseEspecidyCategory() throws Exception {

        String texto = Utils.randomTextForSearch();
        search.searchDizeres(texto).then().statusCode(200).assertThat().body(matchesJsonSchema(new File(Utils.getContractsBasePath("search", "searchtext"))));

    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar consulta de dizeres através de busca textual")
    public void testValidResponseBySearchValidText() throws Exception {
        String texto = Utils.randomTextForSearch();
        Response buscaCategoria = search.searchDizeres(texto);

        if(buscaCategoria.getBody() != null && buscaCategoria.getStatusCode() == 200){
            System.out.println("Request realizada com sucesso ");
        } else {
            Assert.fail("Retorno da equest não corresponde as condições");


        }

    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar consulta de dizeres através de uma busca por termo inexistente")
    public void testValidResponseBySearchInvalidText() throws Exception {
        String texto = "camisa";
        Response buscaCategoria = search.searchDizeres(texto);
        String result = search.searchDizeres(texto).path("result").toString();

        if(buscaCategoria.getStatusCode() == 200 && result.equals("[]")){
            System.out.println("Request realizada com sucesso ");
        } else {
            Assert.fail("Retorno da equest não corresponde as condições");


        }

    }

}
