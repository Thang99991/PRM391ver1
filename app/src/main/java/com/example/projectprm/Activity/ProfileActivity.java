package com.example.projectprm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.R;

public class ProfileActivity extends AppCompatActivity {

 TextView ChangeName;
LinearLayout lnEmail,lnPhone,lnChangPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ChangeName = findViewById(R.id.tvChangeName);
        lnEmail = findViewById(R.id.lnEmail);
        lnPhone = findViewById(R.id.lnPhone);

        lnChangPassword = findViewById(R.id.lvChangePassword);
        ChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangeNameActivity.class);
                startActivity(intent);
            }
        });
        lnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangeEmailActivity.class);
                startActivity(intent);
            }
        });
        lnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangePhoneActivity.class);
                startActivity(intent);
            }
        });
        lnChangPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangePasswordActivity.class);
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
