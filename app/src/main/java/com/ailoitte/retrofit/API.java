package com.ailoitte.retrofit;






import com.ailoitte.models.ModelPhotos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {






    @GET("photos")
    Call<ModelPhotos> getPhotos(@Query("sol") String sol, @Query("api_key") String api_key);




}

