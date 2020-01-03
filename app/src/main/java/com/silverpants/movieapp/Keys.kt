package com.silverpants.movieapp

const val API_KEY = "6ee7d93d702d3f008517ef9a1d399354"
const val BASE_URL = "https://api.themoviedb.org/3/"

//image
const val IMAGE_URL = "https://image.tmdb.org/t/p/"
const val DUMMY_IMAGE_LINK = "https://image.tmdb.org/t/p/w500/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg"
const val IMAGE_SIZE = "original"
const val IMAGE_SIZE_1 = "w500"

//query
const val PAGE_QUERY = "page"
const val PRIMARY_RELEASE_YEAR_QUERY = "primary_release_year"
const val SORT_BY_QUERY = "sort_by"

//query const values
val SORT_BY_VALUES = arrayOf("popularity.desc", "popularity.asc", "vote_average.desc", "vote_average.asc", "release_date.desc", "release_date.asc", "title.asc", "title.desc")

val genres: HashMap<Int,String> = hashMapOf(28 to "Action", 12 to "Adventure",
        16 to "Animation", 35 to "Comedy", 80 to "Crime", 99 to "Documentary", 18 to "Drama",
        10751 to "Family", 14 to "Fantasy", 36 to "History", 27 to "Horror", 10402 to "Music",
        9648 to "Mystery", 10749 to "Romance", 878 to "Science Fiction", 10770 to "TV Movie",
        53 to "Thriller",10752 to "War", 37 to "Western" )


//fake real const values
const val JOKER = 475557
const val ENDGAME = 299534
const val FROZEN2 = 330457


fun getImageUrl(id: String): String {
    return IMAGE_URL + IMAGE_SIZE + id

}
