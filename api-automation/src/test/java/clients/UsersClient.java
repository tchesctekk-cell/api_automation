
package clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * CAMADA DE CLIENTE - USERS
 * Responsabilidade: Encapsular chamadas HTTP para endpoint /users
 */
public class UsersClient {

    private RequestSpecification spec;

    public UsersClient(RequestSpecification spec) {
        this.spec = spec;
    }

    /**
     * GET /users - Retorna todos os usuarios
     */
    public Response getAllUsers() {
        return given()
                .spec(spec)
        .when()
                .get("/users");
    }

    /**
     * GET /users/{id} - Retorna usuario especifico
     */
    public Response getUserById(int userId) {
        return given()
                .spec(spec)
                .pathParam("id", userId)
        .when()
                .get("/users/{id}");
    }
}