package com.learn.fauzan.moviewdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by fauzan on 11/02/18.
 */

public class AppService {

    public interface ApiService{
        @GET
        Call<Model>getNowPlaying(@Url String url);

        @GET
        Call<ModelTopRated>getTopRated(@Url String url);

        @GET
        Call<ModelPopular>getPopular(@Url String url);
    }
}
