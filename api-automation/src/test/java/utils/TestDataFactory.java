package utils;

import models.Post;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Factory para geracao dinamica de dados de teste
 * Evita dados hardcoded e garante unicidade
 */
public class TestDataFactory {

    private static final AtomicInteger counter = new AtomicInteger(1);

    /**
     * Cria Post valido com dados unicos
     */
    public static Post createValidPost() {
        int count = counter.getAndIncrement();
        return new Post(
                1,
                "Teste QA Automatizado " + count,
                "Conteudo do teste numero " + count
        );
    }

    /**
     * Cria payload sem campo title (teste negativo)
     */
    public static Map<String, Object> createPostWithoutTitle() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("body", "Teste sem titulo");
        payload.put("userId", 1);
        return payload;
    }

    /**
     * Cria payload sem campo body (teste negativo)
     */
    public static Map<String, Object> createPostWithoutBody() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Titulo sem corpo");
        payload.put("userId", 1);
        return payload;
    }

    /**
     * Cria payload sem userId (teste negativo)
     */
    public static Map<String, Object> createPostWithoutUserId() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Teste");
        payload.put("body", "Corpo do teste");
        return payload;
    }

    /**
     * Cria payload com userId string (teste negativo)
     */
    public static Map<String, Object> createPostWithInvalidUserIdType() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Teste");
        payload.put("body", "Corpo");
        payload.put("userId", "invalido");
        return payload;
    }

    /**
     * Cria payload vazio (teste negativo)
     */
    public static Map<String, Object> createEmptyPayload() {
        return new HashMap<>();
    }

    /**
     * Gera payload com SQL Injection
     */
    public static String generateSQLInjectionPayload() {
        return "1' OR '1'='1";
    }

    /**
     * Gera payload com XSS
     */
    public static String generateXSSPayload() {
        return "<script>alert('XSS')</script>";
    }

    /**
     * Gera string longa para teste de overflow
     */
    public static String generateLongString(int length) {
        return "A".repeat(length);
    }
}