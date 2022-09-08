//package com.gfttraining.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.gfttraining.entities.EntityUserMovie;
//import com.gfttraining.services.MyMoviesUserMovieService;
//
//@RestController
//public class MyUserMovieController {
//	@Autowired
//	MyMoviesUserMovieService myuserMovieService;
//	
//	@GetMapping("api/movies/{movie_id}/favorite")
//	public boolean getFavorite(@PathVariable int movie_id){
//		return myuserMovieService.findFavorites(0,movie_id);
//	}
//}
