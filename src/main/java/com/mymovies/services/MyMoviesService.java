package com.mymovies.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@SuppressWarnings("unchecked")
public class MyMoviesService {

	@Value("themoviedatabase.api_key")
    private String apiKey;
    
    WebClient webClient = WebClient.create("https://api.themoviedb.org/3/");

	public HashMap<String,Object> getConfig(){
    	HashMap<String,Object> x = webClient.get()
    						.uri(uriBuilder -> uriBuilder.path("/configuration")
    									.queryParam("api_key","949f7ad927a01a36a739c336dd6449b9")
    									.build()
    								).retrieve()
    								 .bodyToMono(HashMap.class)
    								 .block();
    				return x;

    }
    

	public HashMap<String,Object> findAllGenreMovieList() throws IOException{
	    	HashMap<String,Object> x = webClient.get()
					.uri(uriBuilder -> uriBuilder.path("genre/movie/list")
								.queryParam("api_key","949f7ad927a01a36a739c336dd6449b9")
								.build()
							).retrieve()
							 .bodyToMono(HashMap.class)
							 .block();
			return x;

	}
	@Cacheable("popular_movies")
	public HashMap<String,Object> findPopularMovies() throws IOException{
		HashMap<String,Object> x = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/movie/popular")
							.queryParam("api_key","949f7ad927a01a36a739c336dd6449b9")
							.build()
						).retrieve()
						 .bodyToMono(HashMap.class)
						 .block();
		return x;
				
	}
	@Cacheable("top_rated")
	public HashMap<String,Object> findTopRatedMovies() throws IOException {
		HashMap<String,Object> x = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/movie/top_rated")
							.queryParam("api_key","949f7ad927a01a36a739c336dd6449b9")
							.build()
						).retrieve()
						 .bodyToMono(HashMap.class)
						 .block();
		return x;
				
	}

	public HashMap<String,Object> findMovie(String Id) throws IOException {
			HashMap<String,Object> x = webClient.get()
					.uri(uriBuilder -> uriBuilder.path("/movie/{movie_id}")
								.queryParam("api_key","949f7ad927a01a36a739c336dd6449b9")
								.build(Id)
							).retrieve()
							 .bodyToMono(HashMap.class)
							 .block();
			return x;
					
	}
	
	public StringBuilder createStringBuilder(URL url) throws IOException{
	       InputStream input = url.openStream();
	        InputStreamReader isr = new InputStreamReader(input);
	        BufferedReader reader = new BufferedReader(isr);
	        StringBuilder stringBuilder = new StringBuilder();
	        int c ;
	        while((c= reader.read())!=-1){
	            stringBuilder.append((char)c);
	        }
	        return stringBuilder;

	}
	@Cacheable("credits")
	public HashMap<String,Object> findMovieCredits(Integer Id) throws IOException{
		HashMap<String,Object> x = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/movie/{movie_id}/credits")
							.queryParam("api_key","949f7ad927a01a36a739c336dd6449b9")
							.build(Id)
						).retrieve()
						 .bodyToMono(HashMap.class)
						 .block();
		return x;
	}
	@Cacheable("images")
	public HashMap<String,Object> findMovieImages(Integer Id) throws IOException{
		HashMap<String,Object> x = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/movie/{movie_id}/images")
							.queryParam("api_key","949f7ad927a01a36a739c336dd6449b9")
							.build(Id)
						).retrieve()
						 .bodyToMono(HashMap.class)
						 .block();
		return x;
	}
	@Cacheable("keywords")
	public HashMap<String,Object> findMovieKeywords(Integer Id) throws IOException{
		HashMap<String,Object> x = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/movie/{movie_id}/keywords")
							.queryParam("api_key","949f7ad927a01a36a739c336dd6449b9")
							.build(Id)
						).retrieve()
						 .bodyToMono(HashMap.class)
						 .block();
		return x;
	}
	@Cacheable("recommendations")
	public HashMap<String,Object> findMovieRecommendations(Integer Id) throws IOException{
		HashMap<String,Object> x = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/movie/{movie_id}/recommendations")
							.queryParam("api_key","949f7ad927a01a36a739c336dd6449b9")
							.build(Id)
						).retrieve()
						 .bodyToMono(HashMap.class)
						 .block();
		return x;
	}
	@Cacheable("similar")
	public HashMap<String,Object> findMovieSimilar(Integer Id) throws IOException{
		HashMap<String,Object> x = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/movie/{movie_id}/similar")
							.queryParam("api_key","949f7ad927a01a36a739c336dd6449b9")
							.build(Id)
						).retrieve()
						 .bodyToMono(HashMap.class)
						 .block();
		return x;
	}
}
