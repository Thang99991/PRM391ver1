package com.example.projectprm.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.Cart;
import com.example.projectprm.Model.CartResponse;
import com.example.projectprm.Model.ProductCart;
import com.example.projectprm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView imgProduct;
    private TextView tvProductName, tvProductPrice, tvDescription, tvQuantity;
    private Button btnAddToCart;
    private ImageButton btnBack, btnIncrease, btnDecrease;
    private String productId;
    private int quantity = 1; // Initial quantity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetail_page);

        imgProduct = findViewById(R.id.imgProduct);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvDescription = findViewById(R.id.tvDescription);
        tvQuantity = findViewById(R.id.tvQuantity);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBack = findViewById(R.id.btnBack);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);

        productId = getIntent().getStringExtra("product_id");
        fetchProductDetails();

        // Set the initial quantity
        tvQuantity.setText(String.valueOf(quantity));

        // Set up back button click listener
        btnBack.setOnClickListener(v -> onBackPressed());

        // Set up increase button click listener
        btnIncrease.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        // Set up decrease button click listener
        btnDecrease.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        // Add to cart button click listener
        btnAddToCart.setOnClickListener(v -> {
            // Retrieve userId from SharedPreferences
            SharedPreferences sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
            String userId = sharedPref.getString("userId", null);

            if (userId == null) {
                Toast.makeText(ProductDetailActivity.this, "Please log in to add items to cart", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create Cart object
            Cart cart = new Cart(userId, productId, quantity);

            // Call the API to add the product to cart
            addToCart(cart);
        });
    }

    private void fetchProductDetails() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ProductCart> call = apiService.getProductById(productId);

        call.enqueue(new Callback<ProductCart>() {
            @Override
            public void onResponse(Call<ProductCart> call, Response<ProductCart> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductCart product = response.body();
                    tvProductName.setText(product.getName());
                    tvProductPrice.setText(product.getPrice());

                    tvDescription.setText(product.getDescription());
                    List<String> images = product.getImg();
                    if (images != null && !images.isEmpty()) {
                        Picasso.get().load(images.get(0)).into(imgProduct); // Load the first image
                    }
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Failed to load product details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductCart> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToCart(Cart cart) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<CartResponse> call = apiService.AddToCart(cart);

        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    finish();
                    Toast.makeText(ProductDetailActivity.this, "Added to cart successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
