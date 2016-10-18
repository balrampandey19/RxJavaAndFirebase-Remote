package com.brp.Retrofit;

import com.brp.Model.GettyConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;



public interface ApiInterface {


    @GET("search/images")
    rx.Observable<GettyConfig> getImage(@Query("file_types") String file_types);


}
