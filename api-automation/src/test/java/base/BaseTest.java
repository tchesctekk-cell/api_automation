
package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

/**
 * Classe base para reutilizacao de configuracoes comuns
 * Todos os testes herdam desta classe
 */
public class BaseTest {

    protected static RequestSpecification spec;
    protected static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @BeforeAll
    public static void setup() {
        // Configura URL base global
        RestAssured.baseURI = BASE_URL;

        // Cria especificacao reutilizavel
        spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new RequestLoggingFilter(LogDetail.URI))
                .addFilter(new ResponseLoggingFilter(LogDetail.STATUS))
                .build();

        System.out.println("==============================================");
        System.out.println("Base URL: " + BASE_URL);
        System.out.println("==============================================");
    }
}