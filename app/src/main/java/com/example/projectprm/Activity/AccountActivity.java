package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Model.Account;
import com.example.projectprm.R;

import java.io.Serializable;

public class AccountActivity extends AppCompatActivity {

   Button btnProfile,btnOrder,btnAddress,btnPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        btnProfile = findViewById(R.id.btnProfile);
        btnOrder = findViewById(R.id.btnOrder);
        btnAddress = findViewById(R.id.btnAddress);
        btnPayment = findViewById(R.id.btnPayment);
        Account account = (Account) getIntent().getSerializableExtra("account");

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this,ProfileActivity.class);
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
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AccountActivity.this,OrderActivity.class);
                intent.putExtra("account", (Serializable) account);

                startActivity(intent);
            }
        });
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AccountActivity.this,AddressActivity.class);
                intent.putExtra("account", (Serializable) account);

                startActivity(intent);
            }
        });
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AccountActivity.this,PaymentActivity.class);
                intent.putExtra("account", (Serializable) account);
                startActivity(intent);
            }
        });

    }
}
