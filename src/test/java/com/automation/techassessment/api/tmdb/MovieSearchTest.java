package com.automation.techassessment.api.tmdb;

import com.automation.techassessment.api.endpoints.ApiEndpoints;
import com.automation.techassessment.api.endpoints.movies.MovieResponse;
import com.automation.techassessment.api.endpoints.movies.MovieResult;
import com.automation.techassessment.api.endpoints.movies.SearchResponseMovie;
import com.automation.techassessment.api.endpoints.tv.SearchResponseTV;
import com.automation.techassessment.api.endpoints.tv.TVResponse;
import com.automation.techassessment.api.endpoints.tv.TVResult;
import com.automation.techassessment.api.model.Genre;
import com.slickqa.webdriver.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

import static com.automation.techassessment.api.errors.RestAssertions.assertCallSucceeds;


public class MovieSearchTest {
    private ApiEndpoints rest;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setup() throws Exception {
        rest = ApiEndpoints.newBuilder().build();
    }

    @Test
    public void search_moviesreturned_forprimaryreleaseyearonly() {
        final String searchQuery = "2012";
        final int searchYear = 2012;
        String releasedYear;

        try {
            SearchResponseMovie search = assertCallSucceeds(rest.movie.searchMovieByYear(searchQuery, "1", searchYear)).body();
            int numPages = search.getTotalPages();
            for (int x = 1; x <= numPages; x++) {
                List<MovieResult> results = search.getResults();
                Assert.assertTrue(results.size() > 0, "There should be at least one search result");
                for (MovieResult result : results) {
                    releasedYear = result.getReleaseDate();
                    if (!releasedYear.contains(Integer.toString(searchYear))) {
                        Assert.fail("The search results contained movies released in a year not searched for.");
                    }
                }
                search = assertCallSucceeds(rest.movie.searchMovieByYear(searchQuery, Integer.toString(x + 1), searchYear)).body();  //Gets next page of results
            }
            Assert.assertTrue(true); //If movies for the wrong year are found this statement won't be executed.
        }catch (Exception e) {
                logger.debug("Failed during search_moviesreturned_forprimaryreleaseyearonly method", e);
                throw e;
            }
    }

    @Test
    public void search_adultmovies_notinresults() {
        final String searchQuery = "Avengers";
        boolean adultMovie;

        try{
            SearchResponseMovie search = assertCallSucceeds(rest.movie.searchMovie(searchQuery,"1", false)).body();
            int numPages = search.getTotalPages();
            for (int x=1; x<=numPages; x++){
                List<MovieResult> results = search.getResults();
                Assert.assertTrue(results.size() > 0, "There should be at least one search result");
                for(MovieResult result : results) {
                    adultMovie = result.getAdult();
                    if(adultMovie){
                        Assert.fail("The search results contained adult movies.");
                    }
                }
                search = assertCallSucceeds(rest.movie.searchMovie(searchQuery, Integer.toString(x+1), false)).body();  //Gets next page of results
            }
            Assert.assertTrue(true); //If adult movies are found this statement won't be executed.
        }catch (Exception e) {
            logger.debug("Failed during search_adultmovies_notinresults method", e);
            throw e;
        }
    }

    @Test
    public void verify_avengersendgame_ismostpopularavengersmovie() {
        final String searchQuery = "Avengers";
        double highestPopularity = 0.0;
        double popularity;
        String mostPopularAvengersMovieTitle = "";
        String expectedMostPopularAvengersMovieTitle = "Avengers: Endgame";

        try{
            SearchResponseMovie search = assertCallSucceeds(rest.movie.searchMovie(searchQuery,"1", false)).body();
            int numPages = search.getTotalPages();
            for (int x=1; x<=numPages; x++){
                List<MovieResult> results = search.getResults();
                Assert.assertTrue(results.size() > 0, "There should be at least one search result");
                for(MovieResult result : results) {
                    popularity = result.getPopularity();
                    if (popularity > highestPopularity) {
                        highestPopularity = popularity;
                        mostPopularAvengersMovieTitle = result.getTitle();
                    }
                }
                search = assertCallSucceeds(rest.movie.searchMovie(searchQuery, Integer.toString(x+1), false)).body();  //Gets next page of results
            }
            Assert.assertEquals(mostPopularAvengersMovieTitle, expectedMostPopularAvengersMovieTitle,"The movie expected to be the most popular did not match the actual results.");
        }catch (Exception e) {
            logger.debug("Failed during search_adultmovies_notinresults method", e);
            throw e;
        }
    }

    @Test
    public void search_videoavailable_forsearchedmovie() {
        final String searchQuery = "Avengers";
        int id = 448364;
        boolean videoAvailable = false;
        boolean movieFound = false;

        try{
            SearchResponseMovie search = assertCallSucceeds(rest.movie.searchMovie(searchQuery,"1", false)).body();
            int numPages = search.getTotalPages();
            for (int x=1; x<=numPages; x++){
                List<MovieResult> results = search.getResults();
                Assert.assertTrue(results.size() > 0, "There should be at least one search result");
                for(MovieResult result : results) {
                    if(result.getId() == id){
                        movieFound = true;
                        videoAvailable = result.getVideo();
                    }
                }
                search = assertCallSucceeds(rest.movie.searchMovie(searchQuery, Integer.toString(x+1), false)).body();  //Gets next page of results
            }
            Assert.assertTrue(movieFound,"The movie searched for, with id '"+ id +"', was not found.");
            Assert.assertTrue(videoAvailable,"The movie searched for did not contain a video.");
        }catch (Exception e) {
            logger.debug("Failed during search_adultmovies_notinresults method", e);
            throw e;
        }
    }

    @Test
    public void search_bothadultandnonadult_moviesinresults() {
        //ToDo: Verify movies of both adult and non-adult movies can be searched.
        // Provide true for "include-adult" in the request query.
        // Search a movie title that will return both adult and no-adult movies.
    }

    @Test
    public void verify_videonotavailable_forsearchedmovie() {
        //ToDo: Verify a video is not available for a specified movie.
        // Provide the movie search query and movie id for a that doesn't have a video available and verify the response value is false.
    }

    @Test
    public void verify_responsetitle_withrequestquerylanguageequalhi() {
        //ToDo: Verify the title in the results are in Hindi, for an American movie, when the language provided in the request query is 'hi'.
        // Provide the movie search query and language value of 'hi' in the request query. Verify the title in the response results is in Hindi language.
    }

    @Test
    public void verify_eachpageuptototalnumberofpages_hasresults() {
        //ToDo: Verify in the response results that each page up to the "total_pages" have results.
        // Provide a search query that will have more than one page of results.
        // Get the "total_pages", then provide the request for each page and verify each page contains results.
    }

    @Test
    public void search_specialcharacters_insearchquery() {
        //ToDo: Verify the search query, in the request, can have special character.
        // Provide a search query that will have special character such as "$1-!@#%^&*()?/>.<,'";:[{]}\|-=`~".
        // Verify results are returned.
    }

    @Test
    public void search_genreids_areasexpected() {
        //ToDo: Verify the results contain all expected genre ids.
    }

    @Test
    public void search_newmovieinresults_thatwasaddedviapostaddmovie() {
        //ToDo: Verify the search returns results for a movie title, in Search Movie, after posting via the Add Movie endpoint.
    }

    @Test
    public void verify_votecountincrease_afterpostingrating() {
        //ToDo: Verify the vote count increases, in the results of Search Movie, after posting a rating via the Rate Movie endpoint.
    }
}
