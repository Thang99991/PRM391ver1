package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Adapter.AddressAdapter;
import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.Address;
import com.example.projectprm.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmAddressActivity extends AppCompatActivity {
    Button backButton,btn_delete;
    Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_address);

         backButton = findViewById(R.id.btn_back);
         btn_delete = findViewById(R.id.btn_delete);
         address = (Address) getIntent().getSerializableExtra("address");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAddress(address.getId());
            }
        });
    }

    private void DeleteAddress(String id) {
        try {
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Call<Address> call = apiService.deleteAddress(id);

            call.enqueue(new Callback<Address>() {
                @Override
                public void onResponse(Call<Address> call, Response<Address> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Address address = response.body();
                        Intent intent = new Intent(ConfirmAddressActivity.this, AddressActivity.class);
                        startActivity(intent);
                        Toast.makeText(ConfirmAddressActivity.this, "Address Delete Success", Toast.LENGTH_SHORT).show();
                        finish();


                    } else {
                        Toast.makeText(ConfirmAddressActivity.this, "Address Delete available", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Address> call, Throwable t) {
                    Toast.makeText(ConfirmAddressActivity.this, "Failed to load Address", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Loi", e.getMessage());
        }

    }
}
