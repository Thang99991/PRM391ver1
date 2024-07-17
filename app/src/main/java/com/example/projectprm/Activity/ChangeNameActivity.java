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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeNameActivity extends AppCompatActivity {

    EditText FirstName,LastName ;
    Button btSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        FirstName = findViewById(R.id.etFirstName);
        LastName = findViewById(R.id.etLastName);
        btSave = findViewById(R.id.btnSave);

        Account account = (Account) getIntent().getSerializableExtra("account");

        ImageView backButton = findViewById(R.id.imageBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(FirstName.getText().toString())) {
                    if (!TextUtils.isEmpty(LastName.getText().toString())) {
                        account.setUsername(FirstName.getText().toString().trim() + " "+LastName.getText().toString().trim());
                        ChangeName(account);
                    }else{
                        account.setUsername(FirstName.getText().toString().trim());
                        ChangeName(account);
                    }

                }else if(!TextUtils.isEmpty(LastName.getText().toString())){
                    account.setUsername(LastName.getText().toString().trim());
                    ChangeName(account);
                }else {
                    Toast.makeText(ChangeNameActivity.this, "Please enter both first name and last name", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void ChangeName(Account updatedAccount){
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Account> call = apiService.updateAccount(updatedAccount);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Xử lý khi cập nhật thành công
                    Account updatedAccount = response.body();
                    Intent intent = new Intent(ChangeNameActivity.this, ProfileActivity.class);
                    intent.putExtra("account", (Serializable) updatedAccount);
                    startActivity(intent);
                    finish();

                    Toast.makeText(ChangeNameActivity.this, "Username updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý khi không thành công (ví dụ: server trả về lỗi)
                    Toast.makeText(ChangeNameActivity.this, "Failed to update username", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                // Xử lý khi gặp lỗi trong quá trình gọi API
                Toast.makeText(ChangeNameActivity.this, "Failed to update username: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
