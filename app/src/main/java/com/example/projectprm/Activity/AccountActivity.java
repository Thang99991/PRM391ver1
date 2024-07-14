package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.R;

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
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this,ProfileActivity.class);
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
                startActivity(intent);
            }
        });
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AccountActivity.this,AddressActivity.class);
                startActivity(intent);
            }
        });
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AccountActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });

    }
}
