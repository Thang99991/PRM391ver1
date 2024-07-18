package com.example.projectprm.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Adapter.AddressAdapter;
import com.example.projectprm.Adapter.OrderAdapter;
import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.Address;
import com.example.projectprm.Model.Order;
import com.example.projectprm.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    private ListView listView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;
    Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ImageView backButton = findViewById(R.id.imageBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView = findViewById(R.id.listView);

        orderList = new ArrayList<>();

        account = (Account) getIntent().getSerializableExtra("account");

        // Thêm nhiều đơn hàng khác vào list

        fetchOrderData(account.getId());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderActivity.this, OderDetailsActivity.class);
                intent.putExtra("order", orderList.get(position));

                startActivity(intent);
            }
        });
    }

    private void fetchOrderData(String id) {
        try {
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Call<List<Order>> call = apiService.getOrder(id);

            call.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        orderList = response.body();
                        Intent intent = new Intent(OrderActivity.this, OrderAdapter.class);
                        intent.putExtra("account", account);

                        orderAdapter = new OrderAdapter(OrderActivity.this, orderList, intent);

                        listView.setAdapter(orderAdapter);

                    } else {
                        Toast.makeText(OrderActivity.this, "No Address available", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    Toast.makeText(OrderActivity.this, "Failed to load Address", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Loi", e.getMessage());
        }

    }
}
