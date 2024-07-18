package com.example.projectprm.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Adapter.ProductAdapter;
import com.example.projectprm.Adapter.ProductCartAdapter;
import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.Order;
import com.example.projectprm.Model.Product;
import com.example.projectprm.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OderDetailsActivity  extends AppCompatActivity {

    private ListView listView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        listView = findViewById(R.id.lvProducts);
        ImageView backButton = findViewById(R.id.imageBack);
        Order order = (Order) getIntent().getSerializableExtra("order");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        productList = new ArrayList<>();
        fetchOrderData(order.getId());

       // ArrayList<Product> products = new ArrayList<>();
        /*products.add(new Product("Product 1", "$10.00", R.drawable.ic_launcher_foreground));
        products.add(new Product("Product 2", "$20.00", R.drawable.ic_launcher_foreground));
        products.add(new Product("Product 3", "$30.00", R.drawable.ic_launcher_foreground));*/
        // Find ListView and set the adapter


    }

    private void fetchOrderData(String id) {
        try {
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Call<List<Product>> call = apiService.getOrderDetail(id);

            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        productList = response.body();
                        productAdapter = new ProductAdapter(OderDetailsActivity.this, productList);
                        listView.setAdapter(productAdapter);
                    } else {
                        Toast.makeText(OderDetailsActivity.this, "No orders available", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Toast.makeText(OderDetailsActivity.this, "Failed to load orders", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

}
