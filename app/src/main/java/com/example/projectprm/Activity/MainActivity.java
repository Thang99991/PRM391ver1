package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.Adapter.ProductCartAdapter;
import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.ProductCart;
import com.example.projectprm.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductCartAdapter productAdapter;
    private List<ProductCart> productList;
    private LinearLayout lnAccount, lnHome, lnCart, lnExplore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        productList = new ArrayList<>();

        // Fetch data from API
        fetchProductData();

        // Initialize navigation buttons
        lnHome = findViewById(R.id.lnHome);
        lnExplore = findViewById(R.id.lnExplore);
        lnCart = findViewById(R.id.lnCart);
        lnAccount = findViewById(R.id.lnAccount);

        lnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If already on home, do nothing or refresh
                Toast.makeText(MainActivity.this, "Already on Home", Toast.LENGTH_SHORT).show();
            }
        });

//        lnExplore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate to Explore Activity
//                Intent intent = new Intent(MainActivity.this, ExploreActivity.class);
//                startActivity(intent);
//            }
//        });

        lnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Cart Activity
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        lnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                Account account = (Account) getIntent().getSerializableExtra("account");
                intent.putExtra("account", (Serializable) account);
                startActivity(intent);
            }
        });
    }

    private void fetchProductData() {
        try {
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Call<List<ProductCart>> call = apiService.getProducts();

            call.enqueue(new Callback<List<ProductCart>>() {
                @Override
                public void onResponse(Call<List<ProductCart>> call, Response<List<ProductCart>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        productList = response.body();
                        productAdapter = new ProductCartAdapter(MainActivity.this, productList);
                        recyclerView.setAdapter(productAdapter);
                    } else {
                        Toast.makeText(MainActivity.this, "No products available", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<ProductCart>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }
}
