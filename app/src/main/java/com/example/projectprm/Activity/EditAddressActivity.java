package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.Address;
import com.example.projectprm.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAddressActivity  extends AppCompatActivity {
     Address address;
    Account account;
    EditText etAddress;
    Button btnEditAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        etAddress = findViewById(R.id.etAddress);
        btnEditAddress = findViewById(R.id.btnEditAddress);
        account = (Account) getIntent().getSerializableExtra("account");
        address = (Address) getIntent().getSerializableExtra("address");
        ImageView backButton = findViewById(R.id.imageBack);
        etAddress.setText(address.getAddress());

        btnEditAddress.setOnClickListener(new View.OnClickListener() {
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

    private void updateAddress(Address updatedAddress) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Address address = new Address(updatedAddress.getId(),updatedAddress.getAddress());
        Call<Address> call = apiService.updateAddress(address);

        call.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Address address = response.body();
                    Intent intent = new Intent(EditAddressActivity.this, AddressActivity.class);
                    intent.putExtra("account", account);
                    intent.putExtra("address", address);

                    startActivity(intent);
                    finish();
                    Toast.makeText(EditAddressActivity.this, "Address updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditAddressActivity.this, "Failed to update address", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Toast.makeText(EditAddressActivity.this, "Failed to update address: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}