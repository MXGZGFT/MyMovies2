package com.mymovies.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymovies.entities.EntityUserMovie;

@Repository
public interface UserMovieRepository extends JpaRepository<EntityUserMovie, Integer> {
	Optional<EntityUserMovie> findByUsernameAndMovie(String username ,String movieid);
}
