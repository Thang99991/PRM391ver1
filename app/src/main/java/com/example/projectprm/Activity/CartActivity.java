package com.example.projectprm.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.Adapter.CartAdapter;
import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.ProductCart;
import com.example.projectprm.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartItemListener {

    private RecyclerView cartItemsRecyclerView;
    private TextView tvTotalPrice;
    private CartAdapter cartAdapter;
    private List<ProductCart> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);

        cartItemsRecyclerView = findViewById(R.id.cartItemsRecyclerView);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve userId from SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String userId = sharedPref.getString("userId", null);

        if (userId == null) {
            Toast.makeText(CartActivity.this, "Please log in to view your cart", Toast.LENGTH_SHORT).show();
            return;
        }

        // Fetch cart items
        fetchCartItems(userId);
    }

    private void fetchCartItems(String userId) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<ProductCart>> call = apiService.getCartById(userId);

        call.enqueue(new Callback<List<ProductCart>>() {
            @Override
            public void onResponse(Call<List<ProductCart>> call, Response<List<ProductCart>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cartItems = response.body();
                    populateCartItems(cartItems);
                } else {
                    Toast.makeText(CartActivity.this, "Failed to load cart items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductCart>> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateCartItems(List<ProductCart> cartItems) {
        cartAdapter = new CartAdapter(this, cartItems, this);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        int totalPrice = 0;
        for (ProductCart item : cartItems) {
            totalPrice += item.getQuantity() * Integer.parseInt(item.getPrice());
        }
        tvTotalPrice.setText(String.format("%,d đ", totalPrice));
    }

    @Override
    public void onIncreaseQuantity(ProductCart item) {
        int currentQuantity = item.getQuantity();
        item.setQuantity(currentQuantity + 1);
        cartAdapter.notifyDataSetChanged();
        calculateTotalPrice();
    }

    @Override
    public void onDecreaseQuantity(ProductCart item) {
        int currentQuantity = item.getQuantity();
        if (currentQuantity > 1) {
            item.setQuantity(currentQuantity - 1);
            cartAdapter.notifyDataSetChanged();
            calculateTotalPrice();
        }
    }

    @Override
    public void onRemoveItem(ProductCart item) {
        cartItems.remove(item);
        cartAdapter.notifyDataSetChanged();
        calculateTotalPrice();
    }
}
