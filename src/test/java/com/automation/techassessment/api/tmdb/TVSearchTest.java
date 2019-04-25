package com.automation.techassessment.api.tmdb;

import com.automation.techassessment.api.endpoints.ApiEndpoints;
import com.automation.techassessment.api.endpoints.tv.TVResponse;
import com.automation.techassessment.api.endpoints.tv.TVResult;
import com.automation.techassessment.api.endpoints.tv.SearchResponseTV;
import com.automation.techassessment.api.model.Genre;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import static com.automation.techassessment.api.errors.RestAssertions.assertCallSucceeds;


public class TVSearchTest {
    private ApiEndpoints rest;

    @BeforeMethod
    public void setup() throws Exception {
        rest = ApiEndpoints.newBuilder().build();
    }

    @Test(dataProvider = "genreList")
    public void tv_genreList_verifyMultipleGenresAreReturned(String genre) throws IOException {
        final String gameOfThronesID = "1399";

        TVResponse tvResponse = assertCallSucceeds(rest.tv.getTVShowById(gameOfThronesID)).body();
        List<Genre> genres = tvResponse.getGenres();

        Assert.assertTrue(genres.stream().anyMatch(g -> g.getName().equals(genre)),
                "The expected " + genre + " was not found within the Genre list for this TV show.");
    }

    @Test
    public void search_nonEnglishName_itemFound() {
        final String searchQuery = "ドリフェス!";
        final String dreamFestival = "Dream Festival!";

        SearchResponseTV search = assertCallSucceeds(rest.tv.searchTV(searchQuery)).body();
        List<TVResult> results = search.getResults();

        Assert.assertTrue(results.size() > 0, "There should be at least one search result");
        for(TVResult result : results) {
            String showName = result.getName();
            Assert.assertTrue(showName.contains(dreamFestival),
                    "The search results did not contain the name searched for.");
        }
    }

    @DataProvider
    public Object[][] genreList() {
        return new Object[][]{
                {"Sci-Fi & Fantasy"}, {"Drama"}, {"Action & Adventure"}
        };
    }
}
