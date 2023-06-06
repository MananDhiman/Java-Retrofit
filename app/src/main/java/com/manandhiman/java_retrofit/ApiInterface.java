package com.manandhiman.java_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("products/get_popular_products.php")
    Call<List<Model>> getPopularProducts();

    @FormUrlEncoded
    @POST("products/search_products.php?id=2")
    Call<List<Model>> getProductById(
            @Field("id") String id
    );
}
