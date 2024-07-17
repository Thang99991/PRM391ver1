package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.Account;
import com.example.projectprm.R;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePhoneActivity extends AppCompatActivity {

    EditText edtPhone;
    Button btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_number);
        edtPhone = findViewById(R.id.edtPhone);
        ImageView backButton = findViewById(R.id.imageBack);
        btSave = findViewById(R.id.btnSave);


        Account account = (Account) getIntent().getSerializableExtra("account");

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edtPhone.getText() != null ? edtPhone.getText().toString().trim() : "";
                if (isValidPhone(phone)) {
                    account.setPhone(phone);
                    updatePhone(account);
                } else {
                    Toast.makeText(ChangePhoneActivity.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean isValidPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        } else {
            // Kiểm tra số điện thoại với độ dài ít nhất 10 chữ số
            String regex = "^[0-9]{10,}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(phone);
            return matcher.matches();
        }
    }
    public  void updatePhone(Account updatedAccount){
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Account> call = apiService.updateAccount(updatedAccount);

        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Account updatedAccount = response.body();
                    Intent intent = new Intent(ChangePhoneActivity.this, ProfileActivity.class);
                    intent.putExtra("account", (Serializable) updatedAccount);
                    startActivity(intent);
                    finish();
                    Toast.makeText(ChangePhoneActivity.this, "Phone number updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangePhoneActivity.this, "Failed to update phone number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(ChangePhoneActivity.this, "Failed to update phone number: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
