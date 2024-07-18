package com.example.projectprm.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Adapter.AddressAdapter;
import com.example.projectprm.Adapter.ProductCartAdapter;
import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.Address;
import com.example.projectprm.Model.ProductCart;
import com.example.projectprm.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressActivity extends AppCompatActivity {
  private Button addAddress;
    private ListView listView;
    private AddressAdapter addressAdapter;
    private List<Address> addressList;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        addAddress = findViewById(R.id.btnAddAddress);
        listView = findViewById(R.id.listView);
        ImageView backButton = findViewById(R.id.imageBack);
         account = (Account) getIntent().getSerializableExtra("account");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*addressList.add(new Address("1","John", "123 Main St, City, State, Country", "+123456789"));
        addressList.add(new Address("1","John", "123 Main St, City, State, Country", "+123456789"));*/

        fetchAddressData(account.getId());
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this,AddAddressActivity.class);
                intent.putExtra("account", (Serializable) account);

                startActivity(intent);
            }
        });

    }

    private void fetchAddressData(String id) {
        try {
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Call<List<Address>> call = apiService.getAddressById(id);

            call.enqueue(new Callback<List<Address>>() {
                @Override
                public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        addressList = new ArrayList<>();
                        addressList = response.body();
                        Intent intent = new Intent(AddressActivity.this, AddressAdapter.class);
                        intent.putExtra("account", account);

                        addressAdapter = new AddressAdapter(AddressActivity.this, addressList, intent);

                        listView.setAdapter(addressAdapter);

                    } else {
                        Toast.makeText(AddressActivity.this, "No Address available", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Address>> call, Throwable t) {
                    Toast.makeText(AddressActivity.this, "Failed to load Address", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Loi", e.getMessage());
        }

    }
}