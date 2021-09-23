package api.chucknorris.io.tests.categories.tests;


import api.chucknorris.io.suites.AllTests;
import api.chucknorris.io.suites.ContractTests;
import api.chucknorris.io.tests.base.tests.BaseTest;
import api.chucknorris.io.tests.categories.requests.CategoriasRequest;
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
public class CategoriasTest extends BaseTest {

    CategoriasRequest categoria = new CategoriasRequest();

    @Severity(SeverityLevel.MINOR)
    @Test
    @Category({AllTests.class})
    @DisplayName("Validar status code 200 e tempo de resposta abaixo de 3000ms ao buscar categorias")
    public void testBasicResponseSearchCategoires() throws Exception {

        long tempoResposta = categoria.buscaCategorias().then().statusCode(200).extract().time();
        Response buscaCategoria = categoria.buscaCategorias();

        if(buscaCategoria.getStatusCode() == 200 && tempoResposta < 3000){
        System.out.println("Request realizada com sucesso ");
    } else {
        Assert.fail("Retorno da equest não corresponde as condições");


       }

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Category({ContractTests.class})
    @DisplayName("Validar contrato do retorno das categorias")
    public void testValidatedContractResponseCategories() throws Exception {

    categoria.buscaCategorias().then().statusCode(200).assertThat().body(matchesJsonSchema(new File(Utils.getContractsBasePath("categories", "categorias"))));



    }



}
