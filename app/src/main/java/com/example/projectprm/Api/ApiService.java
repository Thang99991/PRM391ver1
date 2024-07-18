package com.example.projectprm.Api;

import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.Cart;
import com.example.projectprm.Model.CartResponse;
import com.example.projectprm.Model.Address;
import com.example.projectprm.Model.LoginResponse;
import com.example.projectprm.Model.Order;
import com.example.projectprm.Model.Product;
import com.example.projectprm.Model.ProductCart;
import com.example.projectprm.Model.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("product")
    Call<List<ProductCart>> getProducts();

    @POST("user/login")
    Call<LoginResponse> checkLogin(@Body Account account);
    @POST("user/register")
    Call<RegisterResponse> checkRegister(@Body Account account);

    @PATCH("user")
    Call<Account> updateAccount(@Body Account account);

    @GET("address/{id}")
    Call<List<Address>> getAddressById(@Path("id") String id);
    @PATCH("address")
    Call<Address> updateAddress(@Body Address address);
    @POST("address")
    Call<Address> Add_Address(@Body Address address);
    @DELETE("address/{id}")
    Call<Address> deleteAddress(@Path("id") String id);

    @GET("order/{id}")
    Call<List<Order>> getOrder(@Path("id") String id);
    @GET("order-detail/{id}")
    Call<List<Product>> getOrderDetail(@Path("id") String id);

    @GET("product/get/{id}")
    Call<ProductCart> getProductById(@Path("id") String id);
    @GET("product/search")
    Call<List<ProductCart>> searchProduct(
            @Query("name") String name,
            @Query("category_id") String categoryId
    );
    @POST("cart/add")
    Call<CartResponse> AddToCart(@Body Cart cart);

    @GET("cart/{id}")
    Call<List<ProductCart>> getCartById(@Path("id") String id);
}
