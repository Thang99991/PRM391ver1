package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Model.Account;
import com.example.projectprm.R;

import java.io.Serializable;

public class ProfileActivity extends AppCompatActivity {

 TextView ChangeName;
    private TextView tvEmail;
    private TextView tvPhoneNumber;
LinearLayout lnEmail,lnPhone,lnChangPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ChangeName = findViewById(R.id.tvChangeName);
        lnEmail = findViewById(R.id.lnEmail);
        lnPhone = findViewById(R.id.lnPhone);
        tvEmail = findViewById(R.id.tvEmail1);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber1);
        lnChangPassword = findViewById(R.id.lvChangePassword);

        Account account = (Account) getIntent().getSerializableExtra("account");
        if (account != null) {
            ChangeName.setText(account.getUsername());
            tvEmail.setText(account.getEmail());
            tvPhoneNumber.setText(account.getPhone());
        }
        ChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangeNameActivity.class);
                intent.putExtra("account", (Serializable) account);
                startActivity(intent);
            }
        });
        lnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangeEmailActivity.class);
                intent.putExtra("account", (Serializable) account);
                startActivity(intent);
            }
        });
        lnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangePhoneActivity.class);
                intent.putExtra("account", (Serializable) account);
                startActivity(intent);
            }
        });
        lnChangPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangePasswordActivity.class);
                intent.putExtra("account", (Serializable) account);
                startActivity(intent);
            }
        });
        ImageView backButton = findViewById(R.id.imageBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
