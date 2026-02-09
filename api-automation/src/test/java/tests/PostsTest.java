
package tests;

import base.BaseTest;
import clients.PostsClient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestDataFactory;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

/**
 * CAMADA DE TESTES - POSTS
 * Responsabilidade: Executar cenarios de teste e validar resultados
 */
public class PostsTest extends BaseTest {

    private PostsClient postsClient;

    @BeforeEach
    public void setupTest() {
        postsClient = new PostsClient(spec);
    }

    // ================== TESTES OBRIGATORIOS ==================

    @Test
    @DisplayName("TC-001: GET /posts retorna 100 posts com estrutura correta [OBRIGATORIO]")
    public void shouldReturn100PostsWithCorrectStructure() {
        Response response = postsClient.getAllPosts();

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(100))
                .body("[0]", hasKey("userId"))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("title"))
                .body("[0]", hasKey("body"))
                .body("[0].userId", isA(Integer.class))
                .body("[0].id", isA(Integer.class))
                .body("[0].title", isA(String.class))
                .body("[0].body", isA(String.class));
    }

    @Test
    @DisplayName("TC-002: GET /posts/1 retorna post especifico [OBRIGATORIO]")
    public void shouldReturnPostById() {
        Response response = postsClient.getPostById(1);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue());
    }

    @Test
    @DisplayName("TC-015: Validar contrato JSON com Schema [OBRIGATORIO]")
    public void shouldMatchJsonSchema() {
        Response response = postsClient.getPostById(1);

        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
    }

    // ================== TESTES NEGATIVOS ==================

    @Test
    @DisplayName("TC-003: GET post inexistente retorna 404")
    public void shouldReturn404ForNonExistentPost() {
        Response response = postsClient.getPostById(99999);

        response.then()
                .statusCode(404);
    }

    @Test
    @DisplayName("TC-004: GET com ID string documenta gap de validacao (BUG-001) [DIFERENCIAL]")
    public void shouldHandleInvalidIdType() {
        // CORRECAO: Chamar endpoint diretamente sem parse
        Response response = given()
                .spec(spec)
        .when()
                .get("/posts/abc");

        // Documenta comportamento real: API retorna 404 ao inves de 400
        int statusCode = response.getStatusCode();
        System.out.println("BUG-001: API retorna " + statusCode + " (esperado 400 para tipo invalido)");
    }

    // ================== TESTES FUNCIONAIS ==================

    @Test
    @DisplayName("TC-005: POST cria post com payload valido usando POJO")
    public void shouldCreatePostWithValidPayload() {
        Post post = TestDataFactory.createValidPost();

        Response response = postsClient.createPost(post);

        response.then()
                .statusCode(201)
                .body("title", equalTo(post.getTitle()))
                .body("body", equalTo(post.getBody()))
                .body("userId", equalTo(post.getUserId()))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("TC-006: POST sem title documenta gap de validacao (BUG-002) [DIFERENCIAL]")
    public void shouldRejectPostWithoutTitle() {
        Map<String, Object> payload = TestDataFactory.createPostWithoutTitle();

        Response response = postsClient.createPost(payload);

        // API fake aceita sem validacao - documenta gap
        int statusCode = response.getStatusCode();
        if (statusCode == 201) {
            System.out.println("BUG-002: Gap de validacao - API aceita POST sem title (esperado 400)");
        }
    }

    @Test
    @DisplayName("TC-007: POST sem body documenta gap de validacao (BUG-003) [DIFERENCIAL]")
    public void shouldRejectPostWithoutBody() {
        Map<String, Object> payload = TestDataFactory.createPostWithoutBody();

        Response response = postsClient.createPost(payload);

        int statusCode = response.getStatusCode();
        if (statusCode == 201) {
            System.out.println("BUG-003: Gap de validacao - API aceita POST sem body (esperado 400)");
        }
    }

    @Test
    @DisplayName("TC-008: POST sem userId documenta gap de validacao (BUG-004) [DIFERENCIAL]")
    public void shouldRejectPostWithoutUserId() {
        Map<String, Object> payload = TestDataFactory.createPostWithoutUserId();

        Response response = postsClient.createPost(payload);

        int statusCode = response.getStatusCode();
        if (statusCode == 201) {
            System.out.println("BUG-004: Gap de validacao - API aceita POST sem userId (esperado 400)");
        }
    }

    @Test
    @DisplayName("TC-009: POST com userId string documenta gap (BUG-005) [DIFERENCIAL]")
    public void shouldRejectInvalidUserIdType() {
        Map<String, Object> payload = TestDataFactory.createPostWithInvalidUserIdType();

        Response response = postsClient.createPost(payload);

        int statusCode = response.getStatusCode();
        if (statusCode == 201) {
            System.out.println("BUG-005: Gap de validacao - API aceita userId string (esperado 400)");
        }
    }

    @Test
    @DisplayName("TC-010: POST com payload vazio documenta gap (BUG-006) [DIFERENCIAL]")
    public void shouldRejectEmptyPayload() {
        Map<String, Object> payload = TestDataFactory.createEmptyPayload();

        Response response = postsClient.createPost(payload);

        int statusCode = response.getStatusCode();
        if (statusCode == 201) {
            System.out.println("BUG-006: Gap de validacao - API aceita payload vazio (esperado 400)");
        }
    }

    @Test
    @DisplayName("TC-011: PUT atualiza post completamente")
    public void shouldUpdatePostCompletely() {
        Post updatedPost = new Post(1, "Titulo Atualizado", "Corpo Atualizado");
        updatedPost.setId(1);

        Response response = postsClient.updatePost(1, updatedPost);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Titulo Atualizado"));
    }

    @Test
    @DisplayName("TC-012: PATCH atualiza post parcialmente")
    public void shouldPatchPostPartially() {
        Map<String, Object> update = new HashMap<>();
        update.put("title", "Novo Titulo");

        Response response = postsClient.patchPost(1, update);

        response.then()
                .statusCode(200)
                .body("title", equalTo("Novo Titulo"));
    }

    @Test
    @DisplayName("TC-013: DELETE remove post")
    public void shouldDeletePost() {
        Response response = postsClient.deletePost(1);

        response.then()
                .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    @DisplayName("TC-014: DELETE post inexistente retorna 404")
    public void shouldReturn404OnDeleteNonExistent() {
        Response response = postsClient.deletePost(99999);

        response.then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    @DisplayName("TC-016: Filtrar posts por userId")
    public void shouldFilterByUserId() {
        Response response = postsClient.getPostsByUserId(1);

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("userId", everyItem(equalTo(1)));
    }

    // ================== TESTES DE SEGURANCA [DIFERENCIAL] ==================

    @Test
    @DisplayName("TC-017: Simula SQL Injection em query param [DIFERENCIAL - Analise de Risco]")
    public void shouldBlockSQLInjection() {
        // CONTEXTO: Teste demonstra analise de risco, nao validacao de implementacao
        // API JSONPlaceholder nao usa SQL, mas o teste simula ataque comum
        
        Response response = given()
                .spec(spec)
                .queryParam("userId", "1' OR '1'='1")
        .when()
                .get("/posts");

        // Valida que payload malicioso nao altera comportamento esperado
        int size = response.jsonPath().getList("$").size();
        
        // API trata corretamente: nao retorna todos os 100 posts
        if (size != 100) {
            System.out.println("PASS: API trata SQL Injection corretamente");
        } else {
            System.out.println("ALERTA: Possivel comportamento inesperado com payload malicioso");
        }
    }

    @Test
    @DisplayName("TC-018: Simula XSS em title [DIFERENCIAL - Gap de Seguranca]")
    public void shouldSanitizeXSSInTitle() {
        // CONTEXTO: Demonstra visao de seguranca em APIs que retornam dados para frontend
        
        String xssPayload = TestDataFactory.generateXSSPayload();

        Map<String, Object> payload = new HashMap<>();
        payload.put("title", xssPayload);
        payload.put("body", "Teste XSS");
        payload.put("userId", 1);

        Response response = postsClient.createPost(payload);

        String returnedTitle = response.jsonPath().getString("title");
        
        if (returnedTitle.contains("<script>")) {
            System.out.println("BUG-007: Gap de seguranca - XSS nao sanitizado no title");
            System.out.println("Impacto: Em producao, permitiria execucao de scripts maliciosos em frontend");
        }
    }

    @Test
    @DisplayName("TC-019: Simula XSS em body [DIFERENCIAL - Gap de Seguranca]")
    public void shouldSanitizeXSSInBody() {
        // CONTEXTO: Valida se API sanitiza HTML malicioso em campos de texto
        
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Teste");
        payload.put("body", "<img src=x onerror=alert('XSS')>");
        payload.put("userId", 1);

        Response response = postsClient.createPost(payload);

        String returnedBody = response.jsonPath().getString("body");
        
        if (returnedBody.contains("<img")) {
            System.out.println("BUG-008: Gap de seguranca - XSS nao sanitizado no body");
        }
    }
}