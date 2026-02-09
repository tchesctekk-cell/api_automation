
package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO representando estrutura de um Post
 * Usado para serializacao/deserializacao automatica via Jackson
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    // Construtor vazio (necessario para Jackson)
    public Post() {
    }

    // Construtor para criacao de posts (sem id - gerado pela API)
    public Post(Integer userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    // Getters e Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}