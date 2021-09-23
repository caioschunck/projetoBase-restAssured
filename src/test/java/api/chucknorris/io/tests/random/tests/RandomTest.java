package api.chucknorris.io.tests.random.tests;

import api.chucknorris.io.suites.AllTests;
import api.chucknorris.io.suites.ContractTests;
import api.chucknorris.io.tests.base.tests.BaseTest;
import api.chucknorris.io.tests.random.requests.RandomRequest;
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

@Feature("Validar funcionamento do endpoint de dizeres aleatorios")
public class RandomTest extends BaseTest {

    RandomRequest random = new RandomRequest();

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar status code 200 e tempo de resposta abaixo de 3000ms ao buscar dizeres de uma determinada categoria")
    public void testBasicResponseSearchEspecifyCategoires() throws Exception {
       String categoria = Utils.randomCategoriesForSearch();
       long tempoResposta = random.buscaDizeresCategoria(categoria).then().statusCode(200).extract().time();
       Response buscaCategoria = random.buscaDizeresCategoria(categoria);

         if(buscaCategoria.getStatusCode() == 200 && tempoResposta < 3000){
             System.out.println("Request realizada com sucesso ");
         } else {
             Assert.fail("Retorno da equest não corresponde as condições");


            }

        }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Category({ContractTests.class})
    @DisplayName("Validar contrato do retorno de dizeres por categoria")
    public void testValidatedContractResponseEspecidyCategory() throws Exception {

      String categoria = Utils.randomCategoriesForSearch();
      System.out.println(categoria);
      random.buscaDizeresCategoria(categoria).then().statusCode(200).assertThat().body(matchesJsonSchema(new File(Utils.getContractsBasePath("random", "randomcategoria"))));

    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar consulta de dizeres através de uma categoria válida")
    public void testValidResponseBySearchValidCategory() throws Exception {
        String categoria = Utils.randomCategoriesForSearch();
        Response buscaCategoria = random.buscaDizeresCategoria(categoria);

        if(buscaCategoria.getBody() != null && buscaCategoria.getStatusCode() == 200){
            System.out.println("Request realizada com sucesso ");
        } else {
            Assert.fail("Retorno da equest não corresponde as condições");


        }

    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar consulta de dizeres através de uma categoria inválida/inexistente")
    public void testValidResponseBySearchInvalidCategory() throws Exception {
        String categoria = "teste";
        Response buscaCategoria = random.buscaDizeresCategoria(categoria);
        String error = random.buscaDizeresCategoria(categoria).path("error").toString();
        System.out.println(error);

        if(error.equals("Not Found") && buscaCategoria.getStatusCode() == 404){
            System.out.println("Request realizada com sucesso ");
        } else {
            Assert.fail("Retorno da equest não corresponde as condições");


        }

    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar status code 200 e tempo de resposta abaixo de 3000ms ao buscar dizeres randomicamente")
    public void testBasicResponseSearchRandomCategoires() throws Exception {
        long tempoResposta = random.buscaDizeresRandom().then().statusCode(200).extract().time();
        Response buscaCategoria = random.buscaDizeresRandom();

        if(buscaCategoria.getStatusCode() == 200 && tempoResposta < 3000){
            System.out.println("Request realizada com sucesso ");
        } else {
            Assert.fail("Retorno da equest não corresponde as condições");


        }

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Category({ContractTests.class})
    @DisplayName("Validar contrato do retorno de dizeres randomicamente")
    public void testValidatedContractResponseRandomCategory() throws Exception {

        random.buscaDizeresRandom().then().statusCode(200).assertThat().body(matchesJsonSchema(new File(Utils.getContractsBasePath("random", "randomcategoria"))));

    }



}
