package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    private LinearLayout lnAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);
        productList = new ArrayList<>();

        lnAccount = findViewById(R.id.lnAccount);
        lnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                Account account = (Account) getIntent().getSerializableExtra("account");
                intent.putExtra("account", (Serializable) account);

                startActivity(intent);
            }
        });

        // Fetch data from API
        fetchProductData();
    }

    private void fetchProductData() {
        try{
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Call<List<ProductCart>> call = apiService.getProducts();

            call.enqueue(new Callback<List<ProductCart>>() {
                @Override
                public void onResponse(Call<List<ProductCart>> call, Response<List<ProductCart>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        productList = response.body();
                        productAdapter = new ProductCartAdapter(productList);

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
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }

    }
}