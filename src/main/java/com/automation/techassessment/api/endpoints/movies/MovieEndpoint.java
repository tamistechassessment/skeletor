package com.automation.techassessment.api.endpoints.movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieEndpoint {
    String BASE_URL = "movie";

    @GET(BASE_URL + "/{movieId}")
    Call<MovieResponse> getMovieById(@Path("movieId") String movieId);

    @GET("search/" + BASE_URL)
    Call<SearchResponseMovie> searchMovie(@Query("query") String query,@Query("page") String page,@Query("include_adult") boolean include_adult);

    @GET("search/" + BASE_URL)
    Call<SearchResponseMovie> searchMovieByYear(@Query("query") String query,@Query("page") String page,@Query("primary_release_year") Integer primary_release_year);
}