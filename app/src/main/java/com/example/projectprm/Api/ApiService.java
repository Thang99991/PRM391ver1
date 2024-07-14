package com.example.projectprm.Api;

import com.example.projectprm.Model.ProductCart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("Product")
    Call<List<ProductCart>> getProducts();
}
