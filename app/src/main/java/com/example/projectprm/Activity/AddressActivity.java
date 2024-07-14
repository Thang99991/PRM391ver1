package com.example.projectprm.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Adapter.AddressAdapter;
import com.example.projectprm.Model.Address;
import com.example.projectprm.R;

import java.util.ArrayList;
import java.util.List;
public class AddressActivity extends AppCompatActivity {
  private Button addAddress;
    private ListView listView;
    private AddressAdapter addressAdapter;
    private List<Address> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        addAddress = findViewById(R.id.btnAddAddress);
        listView = findViewById(R.id.listView);
        ImageView backButton = findViewById(R.id.imageBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addressList = new ArrayList<>();
        addressList.add(new Address("John", "123 Main St, City, State, Country", "+123456789"));
        addressList.add(new Address("John", "123 Main St, City, State, Country", "+123456789"));

        // Thêm các địa chỉ khác vào danh sách

        addressAdapter = new AddressAdapter(this, addressList);
        listView.setAdapter(addressAdapter);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this,AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }
}