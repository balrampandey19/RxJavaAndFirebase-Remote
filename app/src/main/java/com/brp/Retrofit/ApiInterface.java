package com.brp.Retrofit;

import com.brp.Model.GettyConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Gamezop on 11/06/16.
 */
public interface ApiInterface {


    @GET("search/images")
    rx.Observable<GettyConfig> getImage(@Query("file_types") String file_types);


}
