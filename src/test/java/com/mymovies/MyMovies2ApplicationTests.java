package com.mymovies;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymovies.entities.EntityUserMovie;
import com.mymovies.repositories.UserMovieRepository;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;
import java.util.HashMap;
import java.util.Optional;

import com.mymovies.services.MyMoviesService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters=false)
public class MyMovies2ApplicationTests {
	@MockBean
	MyMoviesService myMoviesService;

	@MockBean
	UserMovieRepository userMovieRepository;
	@Autowired
    private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	@Test
	@WithMockUser
	void getConfiguration() throws Exception {
		HashMap<String,Object> resultFromGetConfig = new HashMap<>();
		resultFromGetConfig.put("images", 0);
		resultFromGetConfig.put("change_keys", 0);
		given(myMoviesService.getConfig()).willReturn(resultFromGetConfig).toString();
		ResultActions response = mockMvc.perform(get("/api/configuration"));
				response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.images",is(0)))
                .andExpect(jsonPath("$.change_keys",is(0)))
                .andReturn();
	}
	@Test
	@WithMockUser
	void getAllGenre() throws Exception {
		HashMap<String,Object> resultFromGetGenre = new HashMap<>();
		resultFromGetGenre.put("genres", 0);
		given(myMoviesService.findAllGenreMovieList()).willReturn(resultFromGetGenre).toString();
		ResultActions response = mockMvc.perform(get("/api/genre/movie/list"));
		response
				.andExpect(status().isOk())
				.andExpect(jsonPath( "$.genres",is(0)))
				.andReturn();
	}
	@Test
	@WithMockUser
	void getPopulars() throws Exception {
		HashMap<String,Object> resultFromGetPopulars = new HashMap<>();
		resultFromGetPopulars.put("page", 0);
		given(myMoviesService.findPopularMovies()).willReturn(resultFromGetPopulars).toString();
		ResultActions response = mockMvc.perform(get("/api/movie/popular"));
		response
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.page",is(0)))
				.andReturn();
	}
	@Test
	@WithMockUser
	void getTopRated() throws Exception {
		HashMap<String,Object> resultFromGetTopRated = new HashMap<>();
		resultFromGetTopRated.put("page", 0);
		given(myMoviesService.findTopRatedMovies()).willReturn(resultFromGetTopRated).toString();
		ResultActions response = mockMvc.perform(get("/api/movie/top_rated"));
		response
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.page",is(0)))
				.andReturn();
	}
	@Test
	@WithMockUser
	void getMovie() throws Exception {
		HashMap<String,Object> resultFromGetMovie = new HashMap<>();
		resultFromGetMovie.put("id", 2);
		given(myMoviesService.findMovie("2")).willReturn(resultFromGetMovie).toString();
		ResultActions response = mockMvc.perform(get("/api/movie/2"));
		response
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id",is(2)))
				.andReturn();
	}
	@Test
	@WithMockUser
	void getMovieCredits() throws Exception {
		HashMap<String,Object> resultFromGetMovieCredits = new HashMap<>();
		resultFromGetMovieCredits.put("cast", 0);
		resultFromGetMovieCredits.put("crew", 0);
		given(myMoviesService.findMovieCredits(2)).willReturn(resultFromGetMovieCredits).toString();
		ResultActions response = mockMvc.perform(get("/api/movie/2/credits"));
		response
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cast",is(0)))
				.andExpect(jsonPath("$.crew",is(0)))
				.andReturn();
	}
	@Test
	@WithMockUser
	void getMovieImages() throws Exception {
		HashMap<String,Object> resultFromGetMovieImages = new HashMap<>();
		resultFromGetMovieImages.put("id", 0);
		given(myMoviesService.findMovieImages(0)).willReturn(resultFromGetMovieImages).toString();
		ResultActions response = mockMvc.perform(get("/api/movie/0/images"));
		response
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id",is(0)))
				.andReturn();
	}
	@Test
	@WithMockUser
	void getMovieKeyword() throws Exception {
		HashMap<String,Object> resultFromGetMovieKeywords = new HashMap<>();
		resultFromGetMovieKeywords.put("keywords", 0);
		given(myMoviesService.findMovieKeywords(0)).willReturn(resultFromGetMovieKeywords).toString();
		ResultActions response = mockMvc.perform(get("/api/movie/0/keywords"));
		response
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.keywords",is(0)))
				.andReturn();
	}
	@Test
	@WithMockUser
	void getMovieRecommendations() throws Exception {
		HashMap<String,Object> resultFromGetMovieRecommendations = new HashMap<>();
		resultFromGetMovieRecommendations.put("page", 1);
		given(myMoviesService.findMovieRecommendations(2)).willReturn(resultFromGetMovieRecommendations).toString();
		ResultActions response = mockMvc.perform(get("/api/movie/2/recommendations"));
		response
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.page",is(1)))
				.andReturn();
	}
	@Test
	@WithMockUser
	void getMovieSimilars() throws Exception {
		HashMap<String,Object> resultFromGetMovieSimilars = new HashMap<>();
		resultFromGetMovieSimilars .put("page", 1);
		given(myMoviesService.findMovieSimilar(2)).willReturn(resultFromGetMovieSimilars ).toString();
		ResultActions response = mockMvc.perform(get("/api/movie/2/similar"));
		response
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.page",is(1)))
				.andReturn();
	}

	@Test
	@WithMockUser
	void patchMovie() throws Exception {
		HashMap<String,Object> resultOfPatchMovie = new HashMap<>();
		resultOfPatchMovie .put("page", 1);
		resultOfPatchMovie.put("total_pages",35032);
		resultOfPatchMovie.put("results",0);
		resultOfPatchMovie.put("total_results",35032);
		Integer id = 550;
		EntityUserMovie entityUserMovie = new EntityUserMovie();
		Principal principal = new Principal() {
			@Override
			public String getName() {
				return "admin";
			}
		};

		given(userMovieRepository.findByUsernameAndMovie(principal.getName(),id.toString())).willReturn(Optional.of(entityUserMovie));
		given(userMovieRepository.save(entityUserMovie)).willAnswer(invocation -> invocation.getArgument(0));

		ResultActions response = mockMvc.perform(patch("/api/movie/{movie_id}",id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entityUserMovie)));

		response.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.id",is(IsNull.nullValue())))
				.andExpect(jsonPath("$.username",is("user")))
				.andExpect(jsonPath("$.movie",is(id.toString())))
				.andExpect(jsonPath("$.favorite",is(false)))
				.andExpect(jsonPath("$.personal_rating",is(IsNull.nullValue())))
				.andExpect(jsonPath("$.notes",is(IsNull.nullValue())));
	}
}
