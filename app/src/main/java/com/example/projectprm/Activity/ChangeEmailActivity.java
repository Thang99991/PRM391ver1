package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeEmailActivity extends AppCompatActivity {
EditText etChangeEmail;
Button btnChangeEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        etChangeEmail = findViewById(R.id.etChangeEmail);
        btnChangeEmail = findViewById(R.id.btnChangeEmail);

        ImageView backButton = findViewById(R.id.imageBack);
        Account account = (Account) getIntent().getSerializableExtra("account");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etChangeEmail.getText().toString().trim();
                if (isValidEmail(email)) {
                    account.setEmail(email);
                    updateEmail(account);
                } else {
                    Toast.makeText(ChangeEmailActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void updateEmail(Account updatedAccount) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Account> call = apiService.updateAccount(updatedAccount);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Account updatedAccount = response.body();
                    Intent intent = new Intent(ChangeEmailActivity.this, ProfileActivity.class);
                    intent.putExtra("account", (Serializable) updatedAccount);
                    startActivity(intent);
                    finish();
                    Toast.makeText(ChangeEmailActivity.this, "Email updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangeEmailActivity.this, "Failed to update email", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(ChangeEmailActivity.this, "Failed to update email: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
