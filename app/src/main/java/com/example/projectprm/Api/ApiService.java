package com.example.projectprm.Api;

import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.LoginResponse;
import com.example.projectprm.Model.ProductCart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("product")
    Call<List<ProductCart>> getProducts();

    @POST("user/login")
    Call<LoginResponse> checkLogin(@Body Account account);
  
}
