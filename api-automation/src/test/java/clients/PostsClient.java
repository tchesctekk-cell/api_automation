
package clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Post;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * CAMADA DE CLIENTE - POSTS
 * Responsabilidade: Encapsular APENAS as chamadas HTTP para endpoint /posts
 * Nao contem validacoes - apenas comunicacao com API
 */
public class PostsClient {

    private RequestSpecification spec;

    public PostsClient(RequestSpecification spec) {
        this.spec = spec;
    }

    /**
     * GET /posts - Retorna todos os posts
     */
    public Response getAllPosts() {
        return given()
                .spec(spec)
        .when()
                .get("/posts");
    }

    /**
     * GET /posts/{id} - Retorna post especifico
     */
    public Response getPostById(int postId) {
        return given()
                .spec(spec)
                .pathParam("id", postId)
        .when()
                .get("/posts/{id}");
    }

    /**
     * GET /posts?userId={userId} - Filtra posts por usuario
     */
    public Response getPostsByUserId(int userId) {
        return given()
                .spec(spec)
                .queryParam("userId", userId)
        .when()
                .get("/posts");
    }

    /**
     * POST /posts - Cria novo post usando POJO
     */
    public Response createPost(Post post) {
        return given()
                .spec(spec)
                .body(post)
        .when()
                .post("/posts");
    }

    /**
     * POST /posts - Cria novo post usando Map (para testes negativos)
     */
    public Response createPost(Map<String, Object> payload) {
        return given()
                .spec(spec)
                .body(payload)
        .when()
                .post("/posts");
    }

    /**
     * PUT /posts/{id} - Atualiza post completo
     */
    public Response updatePost(int postId, Post post) {
        return given()
                .spec(spec)
                .pathParam("id", postId)
                .body(post)
        .when()
                .put("/posts/{id}");
    }

    /**
     * PATCH /posts/{id} - Atualiza post parcialmente
     */
    public Response patchPost(int postId, Map<String, Object> payload) {
        return given()
                .spec(spec)
                .pathParam("id", postId)
                .body(payload)
        .when()
                .patch("/posts/{id}");
    }

    /**
     * DELETE /posts/{id} - Deleta post
     */
    public Response deletePost(int postId) {
        return given()
                .spec(spec)
                .pathParam("id", postId)
        .when()
                .delete("/posts/{id}");
    }
}