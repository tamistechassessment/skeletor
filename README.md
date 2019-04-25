

## Overview and Requirements
* Java 8
***** Maven

  
  
**Part 1:  Web API Technical Assessment**

The Movie DB project (www.themoviedb.org) is a free community built database of movie and TV meta data.  TMDB also provides a free API web service that provides movie buffs with a wealth of information and details regarding their favorite movies and allows the information to be integrated with both commercial and open source media related applications.  A large consumer of TDMB data is the web applications Plex Media Server https://www.plex.tv/

The APIs are all REST and return JSON data.  Visit https://developers.themoviedb.org/3/getting-started for detailed documentation regarding the API.  Your assignment should you choose to accept it is to pretend as though you were writing automated test cases on a new release of the TMDB API.

The requirements for the assessment are as follows:
1. You’ll need to create a free developer API key that is required to use the API
2. Don’t worry about sending us the key or checking it into a repo, but it will be required for you to run the tests
3. Assume the new "feature" for the release you are testing is movie search
4. Use our existing framework and libraries to create some tests for movie search
5. Stub out all those tests with functions and using comments in the code write TODOs for the name of the tests and 1-2 sentences describing what the test will do and why
6. Implement 3-4 of the tests with actual runnable code
7. Clear, concise code is important
8. Code maintenance is paramount... any new libraries added should account for this
9. Bonus points are given for non-happy path test scenarios and more creative approaches
10. The TV search tests are samples to get you started.  While we would expect that you are following a similar **pattern** don't assume that the TV tests are complete, i.e.:
     - An **exact** copy/paste of the TV tests modified to Movie tests is not what your goal should be
     - They are probably not doing all the assertion needed
     - They are not covering all the test scenarios
     
`NOTE to run the API tests you will need to create a debug/run configuration Java VM option named tmdb.apiKey
and the value of that VM option needs to be the v3 API key that you created in step 1.  For example:  -Dtmdb.apiKey=17ff4a5d4f0b47e5f96187b898a79970a`
     
     
**Part 2:  Web UI Technical Assessment**

We would like to assess how you create a simple Web test. 

Test case:
1.	Use the website https://www.saucedemo.com/ as the test site
2.	Create a test that logs in as the user: standard_user  password: secret_sauce
3.	Add the “Sauce Labs Onesie” to the cart
4.	Add the “Sauce Labs Bike Light” to the cart
5.	Verify both items are in the cart

The requirements for the assessment are as follows:
1. Use our existing framework and libraries to create this additional test case
2. Clear, concise code is important
3. Code maintenance and robustness is paramount... any new libraries/page object added should account for this **fragile** page elements will lose points


