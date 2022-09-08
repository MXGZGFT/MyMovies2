package com.mymovies.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymovies.entities.EntityUserMovie;
import com.mymovies.repositories.UserMovieRepository;
import com.mymovies.services.MyMoviesService;

@RestController
@RequestMapping("/api")
public class MyMovieController {
	
	@Autowired
	MyMoviesService myMoviesService;
	@Autowired
	UserMovieRepository userMoviesRepository;
	@GetMapping("/configuration")
	public Object getConfiguration(){
		var x = myMoviesService.getConfig();
		return x;
	}
	@GetMapping("genre/movie/list")
	public HashMap<String,Object> findAllGenreMovieList() throws IOException{
		return myMoviesService.findAllGenreMovieList();
	}
	@GetMapping("/movie/popular")
	public HashMap<String,Object> returnPopularMovies() throws IOException{
		return myMoviesService.findPopularMovies();
	}
	
	@GetMapping("/movie/top_rated")
	public HashMap<String,Object> returnTopRatedMovies() throws IOException{
		HashMap<String,Object> movie = myMoviesService.findTopRatedMovies();
		return movie;
	}
	@GetMapping("/movie/{movie_id}")
	public Object returnMovie(@AuthenticationPrincipal UserDetails user, @PathVariable Integer movie_id) throws IOException {
		EntityUserMovie userMovie = userMoviesRepository.findByUsernameAndMovie(user.getUsername(), movie_id.toString()).orElse(null);
		HashMap<String,Object> movie = myMoviesService.findMovie(movie_id.toString());
		if(userMovie !=null) {
			movie.put("favorite",userMovie.isFavorite());
			movie.put("personal_rating", userMovie.getPersonal_rating());
			movie.put("notes", userMovie.getNotes());
		}
		return movie;
	}
	@GetMapping("/movie/{movie_id}/credits")
	public HashMap<String,Object> returnMovieCredits(@PathVariable Integer movie_id) throws IOException{
		return myMoviesService.findMovieCredits(movie_id);
	}
	@GetMapping("/movie/{movie_id}/images")
	public HashMap<String,Object> returnMovieImages(@PathVariable Integer movie_id) throws IOException{
		return myMoviesService.findMovieImages(movie_id);
	}
	
	@GetMapping("/movie/{movie_id}/keywords")
	public HashMap<String,Object> returnKeywords(@PathVariable Integer movie_id) throws IOException{
		return myMoviesService.findMovieKeywords(movie_id);
	}
	
	@GetMapping("/movie/{movie_id}/recommendations")
	public HashMap<String,Object> returnRecommendations(@PathVariable Integer movie_id) throws IOException{
		return myMoviesService.findMovieRecommendations(movie_id);
	}

	@GetMapping("/movie/{movie_id}/similar")
	public HashMap<String,Object> returnSimilar(@PathVariable Integer movie_id) throws IOException{
		return myMoviesService.findMovieSimilar(movie_id);
	}
	@PatchMapping("/movie/{movie_id}")
	public Object patchNewVariables(@AuthenticationPrincipal UserDetails user,@RequestBody EntityUserMovie newUserMovie, @PathVariable Integer movie_id) throws IOException{
		 EntityUserMovie updatedMovie = userMoviesRepository.findByUsernameAndMovie(user.getUsername(),movie_id.toString()).orElse(null);
	        if(updatedMovie == null){
	            updatedMovie = new EntityUserMovie();
	        }
	        updatedMovie.setUsername(user.getUsername());
	        updatedMovie.setMovie(movie_id.toString());
	        updatedMovie.setFavorite(newUserMovie.isFavorite());
	        updatedMovie.setPersonal_rating(newUserMovie.getPersonal_rating());
	        updatedMovie.setNotes((newUserMovie.getNotes()));



	       userMoviesRepository.save(updatedMovie);



	       return new ResponseEntity<EntityUserMovie>(updatedMovie, HttpStatus.OK);

	}
}
