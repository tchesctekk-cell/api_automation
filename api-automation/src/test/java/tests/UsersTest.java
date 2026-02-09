
package tests;

import base.BaseTest;
import clients.UsersClient;
import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CAMADA DE TESTES - USERS
 */
public class UsersTest extends BaseTest {

    private UsersClient usersClient;

    @BeforeEach
    public void setupTest() {
        usersClient = new UsersClient(spec);
    }

    @Test
    @DisplayName("TC-020: GET /users retorna todos os usuarios")
    public void shouldReturnAllUsers() {
        Response response = usersClient.getAllUsers();

        response.then()
                .statusCode(200)
                .body("size()", equalTo(10))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"))
                .body("[0]", hasKey("username"))
                .body("[0]", hasKey("email"));
    }

    @Test
    @DisplayName("TC-021: GET /users/1 retorna usuario especifico")
    public void shouldReturnUserById() {
        Response response = usersClient.getUserById(1);

        // Converte para POJO
        User user = response.as(User.class);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1));

        // Validacoes usando objeto
        assertNotNull(user.getName());
        assertNotNull(user.getEmail());
    }

    @Test
    @DisplayName("TC-022: GET usuario inexistente retorna 404")
    public void shouldReturn404ForNonExistentUser() {
        Response response = usersClient.getUserById(99999);

        response.then()
                .statusCode(404);
    }
}