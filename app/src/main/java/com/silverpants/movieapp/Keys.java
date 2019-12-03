package com.silverpants.movieapp;

public class Keys {
    public static final String API_KEY = "6ee7d93d702d3f008517ef9a1d399354";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    //image
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/";
    public static final String SIZE_1 = "w342";
    public static final String DUMMY_IMAGE ="https://image.tmdb.org/t/p/w500/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg";

    //query
    public static final String PAGE_QUERY = "page";
    public static final String PRIMARY_RELEASE_YEAR_QUERY = "primary_release_year";
    public static final String SORT_BY_QUERY ="sort_by";

    //query values
    public static final String[] SORT_BY_VALUES = {
            "popularity.desc",
            "popularity.asc",
            "vote_average.desc",
            "vote_average.asc",
            "release_date.desc",
            "release_date.asc",
            "title.asc",
            "title.desc",
    };

    //path values
    public static final int INFINITY_WAR = 299536;

}
