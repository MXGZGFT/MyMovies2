package com.mymovies.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class MyMovies2ServiceTests {
    @Autowired
    private WebTestClient webTestClient;
    private Integer idTest = 550;
    final String ContentType = HAL_JSON_VALUE;

    @Test
    void getConfig(){
        webTestClient.get()
                .uri("/api/configuration")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json");
    }
    @Test
    void getConfigBody(){
        webTestClient.get()
                .uri("/api/configuration")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.images").exists();
    }
    @Test
    void getAllGenres(){
        webTestClient.get()
                .uri("/api/genre/movie/list")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.genres").exists();
    }
    @Test
    void findPopularMovies() {
        webTestClient.get()
                .uri("/api/movie/popular")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.page").exists();
    }

    @Test
    void findTopRatedMovies() {
        webTestClient.get()
                .uri("/api/movie/top_rated")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.page").exists();
    }

    @Test
    void findMovie() {
        webTestClient.get()
                .uri("/api/movie/"+idTest)
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                //.uri(uriBuilder -> uriBuilder.path("/api/movie/movie").queryParam("movie_id","550").build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody();
       /*         .jsonPath("$.page").exists()
                .jsonPath("$.total_page").exists()
                .jsonPath("$.results").exists()
                .jsonPath("$.total_results").exists();*/
    }
    @Test
    void findMovieCredits() {
        webTestClient.get()
                .uri("/api/movie/"+idTest+"/credits")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.cast").exists()
                .jsonPath("$.crew").exists();
    }

    @Test
    void findMovieImages() {
        webTestClient.get()
                .uri("/api/movie/"+idTest+"/images")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.id").exists();
    }

    @Test
    void findMovieKeywords() {
        webTestClient.get()
                .uri("/api/movie/"+idTest+"/keywords")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.keywords").exists();
    }

    @Test
    void findMovieRecommendations() {
        webTestClient.get()
                .uri("/api/movie/"+idTest+"/recommendations")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.page").exists();
    }

    @Test
    void findMovieSimilar() {
        webTestClient.get()
                .uri("/api/movie/"+idTest+"/similar")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.page").exists();
    }
}

