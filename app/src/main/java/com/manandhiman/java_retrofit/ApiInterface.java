package com.manandhiman.java_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("products/get_popular_products.php")
    Call<List<Model>> getPopularProducts();
}
