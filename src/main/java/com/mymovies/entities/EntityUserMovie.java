package com.mymovies.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Entity
@Table(name="user_movie")

public class EntityUserMovie {
	@Id
	@Column(name="id",nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	String username;
	String movie;

    boolean favorite;
    Integer personal_rating;
    String notes;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public boolean isFavorite() {
		return favorite;
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	public Integer getPersonal_rating() {
		return personal_rating;
	}
	public void setPersonal_rating(Integer personal_rating) {
		this.personal_rating = personal_rating;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	
}
