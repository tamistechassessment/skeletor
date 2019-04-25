package com.automation.techassessment.api.tmdb;

import com.automation.techassessment.api.endpoints.ApiEndpoints;
import org.testng.annotations.BeforeMethod;

/**
 * Created by slambson on 4/18/19.
 */
public class MovieSearchTests {

    private ApiEndpoints rest;

    @BeforeMethod
    public void setup() throws Exception {
        rest = ApiEndpoints.newBuilder().build();
    }

    // TODO:  convert to retrofit
//    @Test
//    public void discover_onePagePopularMovies_returnsOnePageOnly() {
//        final int pageCount = 1;
//        final int pageSize = 20;
//
//        Discover discover = apiHelper.get("/discover/movie",
//                "&&sort_by=popularity.desc&include_adult=false&include_video=false&page=" + pageCount,
//                Discover.class);
//
//        int page = discover.getPage();
//        int resultsSize = discover.getResults().size();
//
//        Assert.assertEquals(pageCount, page, "There should only be one page.");
//        Assert.assertEquals(pageSize, resultsSize, "moo");
//    }
}
