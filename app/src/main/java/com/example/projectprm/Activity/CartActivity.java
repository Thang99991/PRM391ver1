package com.example.projectprm.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.CartItem;
import com.example.projectprm.Adapter.CartAdapter;
import com.example.projectprm.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;
    private ImageButton btnBack, btnIncrease, btnDecrease;
   /* private Call<List<CartItem>> cartCall;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);
        btnBack = findViewById(R.id.btnBack);
        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));

        // Initialize cartItemList
        cartItemList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartItemList, this);
        recyclerViewCart.setAdapter(cartAdapter);
        btnBack.setOnClickListener(v -> onBackPressed());

        // Retrieve user ID from shared preferences
        SharedPreferences sharedPref = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String userId = sharedPref.getString("userId", null);
        if (userId == null) {
            Toast.makeText(CartActivity.this, "Please log in to view your cart", Toast.LENGTH_SHORT).show();
        } else {
            fetchCartItems(userId);
        }
    }

    private void fetchCartItems(String userId) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<CartItem>> cartCall = apiService.getCartById(userId);

        cartCall.enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                if (response.isSuccessful()) {
                    List<CartItem> fetchedItems = response.body();
                    if (fetchedItems != null && !fetchedItems.isEmpty()) {
                        cartItemList.clear();
                        cartItemList.addAll(fetchedItems);
                        cartAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(CartActivity.this, "No items found in cart", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CartActivity.this, "Failed to fetch cart items", Toast.LENGTH_SHORT).show();
                    Log.e("CartActivity", "Response not successful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
                Log.e("CartActivity", "Error fetching cart items", t);
                Toast.makeText(CartActivity.this, "Error fetching cart items", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        if (cartCall != null) {
            cartCall.cancel();
        }
    }*/
}
