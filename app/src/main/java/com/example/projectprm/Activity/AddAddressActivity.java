package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Adapter.AddressAdapter;
import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.Address;
import com.example.projectprm.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressActivity extends AppCompatActivity {
    Account account;
   EditText etAddress;
   Button AddAddress;
    Address address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        ImageView backButton = findViewById(R.id.imageBack);
        etAddress = findViewById(R.id.etAddress);
        AddAddress = findViewById(R.id.btnAddAddress);
        account = (Account) getIntent().getSerializableExtra("account");
        address = new Address();
        AddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address.setAddress(etAddress.getText().toString().trim());

                updateAddress(address);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void updateAddress(Address AddAddress) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Address address = new Address(account.getId(),AddAddress.getAddress());

        Call<Address> call = apiService.Add_Address(address);

        call.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Address address = response.body();
                    Intent intent = new Intent(AddAddressActivity.this, AddressActivity.class);
                    intent.putExtra("address", address);

                    intent.putExtra("account", account);
                    startActivity(intent);
                    finish();
                    Toast.makeText(AddAddressActivity.this, "Add Address  successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddAddressActivity.this, "Failed to add address", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Toast.makeText(AddAddressActivity.this, "Failed to Add address: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
