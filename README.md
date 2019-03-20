## MovieGasm

>**The app that shows the movie details.
It uses OMDB api for movie details.
Retrofit is used for calling the api and Room database for storing the search history result.
The app also dynamically loads the contents of movies from server.**


**[Movies Details Json File- First Screen](https://github.com/spawn08/MovieGasm/blob/master/app/src/main/assets/movie_details.json)**

_The app uses [OMDB API](https://www.omdbapi.com/) for fetching the details of movies. The networking calls are made using Retrofit._

#### Important Classes in the App

>* [BaseMovieActivity](https://github.com/spawn08/MovieGasm/blob/master/app/src/main/java/com/spawn/moviegasm/BaseMovieActivity.java) - 
    This is the base activity of the application. The default movie UI is shown in this activity. You can dynamically change the contents of the UI by changing the json file from server.
    As per your server back end implementation, you need to make the changes in Retrofit client.
    
>* [SearchActivity](https://github.com/spawn08/MovieGasm/blob/master/app/src/main/java/com/spawn/moviegasm/SearchActivity.java) - 
   This activity is used to search the movie entered by the user on search box. This uses OMDB API as mentioned above for movie details.
   This activity also stores the latest movie search in Room database. 

>* [UserService](https://github.com/spawn08/MovieGasm/blob/master/app/src/main/java/com/spawn/moviegasm/Utils/UserService.java) - 
   This class is placed in Utils package inside source. This class is used to initialize the Room database and for retrieval/deletion.   
      
##Android Concepts Used
* [Room Database](https://developer.android.com/topic/libraries/architecture/room)
* [Glide Library](https://github.com/bumptech/glide)
* [Retrofit](https://square.github.io/retrofit/) 
* [Facebook Shimmer](https://github.com/facebook/shimmer-android)

> **Contribution to this project is most welcome. _Happy Coding_**