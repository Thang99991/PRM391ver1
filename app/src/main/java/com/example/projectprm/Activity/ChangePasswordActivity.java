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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edPassOld,editPass1,edPass2;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edPassOld = findViewById(R.id.edtPassOld);
        editPass1 = findViewById(R.id.edtPass1);
        edPass2 = findViewById(R.id.edtPass2);
        btnSave = findViewById(R.id.btnSave);
        Account account = (Account) getIntent().getSerializableExtra("account");


        ImageView backButton = findViewById(R.id.imageBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = edPassOld.getText().toString().trim();
                String newPassword1 = editPass1.getText().toString().trim();
                String newPassword2 = edPass2.getText().toString().trim();

                if (isValidPassword(oldPassword, newPassword1, newPassword2,account.getPassword().toString())) {
                    account.setPassword(newPassword1); // Set the new password
                    updatePassword(account);
                } else {

                        // Show appropriate error messages based on validation failure
                        if (TextUtils.isEmpty(oldPassword)) {
                            edPassOld.setError("Please enter your old password");
                        } else if (!oldPassword.equals(account.getPassword())) {
                            edPassOld.setError("Incorrect old password");
                        }

                        if (TextUtils.isEmpty(newPassword1)) {
                            editPass1.setError("Please enter a new password");
                        } else if (newPassword1.length() < 0) {
                            editPass1.setError("Password should be at least 6 characters long");
                        }

                        if (TextUtils.isEmpty(newPassword2)) {
                            edPass2.setError("Please confirm your new password");
                        } else if (!newPassword1.equals(newPassword2)) {
                            edPass2.setError("Passwords do not match");
                        }
                    }

                }
        });

    }
    private boolean isValidPassword(String oldPassword, String newPassword1, String newPassword2, String currentPassword) {
        // Example validation: Check if passwords match and meet criteria
        return !TextUtils.isEmpty(oldPassword) &&
                !TextUtils.isEmpty(newPassword1) &&
                !TextUtils.isEmpty(newPassword2) &&
                oldPassword.equals(currentPassword) && // Check if oldPassword matches current password
                newPassword1.equals(newPassword2) &&
                newPassword1.length() >= 6; // Example criteria: password length should be at least 6 characters
    }
    private void updatePassword(Account updatedAccount) {
        // Example Retrofit call; replace with your actual API service and endpoint
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Account> call = apiService.updateAccount(updatedAccount);

        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Account updatedAccount = response.body();
                    // Example: Navigate to profile activity or previous screen
                    Intent intent = new Intent(ChangePasswordActivity.this, ProfileActivity.class);
                    intent.putExtra("account", updatedAccount); // Optional: Send updated account data
                    startActivity(intent);
                    finish(); // Finish current activity
                    Toast.makeText(ChangePasswordActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(ChangePasswordActivity.this, "Failed to update password: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}