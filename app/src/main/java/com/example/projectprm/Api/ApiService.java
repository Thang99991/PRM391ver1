package com.example.projectprm.Api;

import com.example.projectprm.Model.ProductCart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("product")
    Call<List<ProductCart>> getProducts();
  
}
