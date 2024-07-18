package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Api.ApiService;
import com.example.projectprm.Api.RetrofitClient;
import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.RegisterResponse;
import com.example.projectprm.R;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText Email, Password, UserName, Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        Email = findViewById(R.id.newEmail);
        Password = findViewById(R.id.newPassword);
        UserName = findViewById(R.id.userName);
        Phone = findViewById(R.id.phone);

        Button registerButton = findViewById(R.id.sign_in_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndRegister();
            }
        });
    }

    private void validateAndRegister() {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String userName = UserName.getText().toString().trim();
        String phone = Phone.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Invalid email format");
            Email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Password.setError("Password is required");
            Password.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(userName)) {
            UserName.setError("Username is required");
            UserName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            Phone.setError("Phone is required");
            Phone.requestFocus();
            return;
        }

        // Perform registration action (e.g., send a registration request to your server)
        registerAccount(email, password, userName, phone);
    }

    private void registerAccount(String email, String password, String userName, String phone) {
        try {
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Account account = new Account(email, password, userName, phone);

            Call<RegisterResponse> call = apiService.checkRegister(account);

            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Account account1 = response.body().getUser();

                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.putExtra("account", (Serializable) account1);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "No Account available", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Failed to register account", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }
}
