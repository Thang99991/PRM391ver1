package com.example.projectprm.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Adapter.OrderAdapter;
import com.example.projectprm.Model.Order;
import com.example.projectprm.R;

import java.util.ArrayList;
import java.util.List;
public class OrderActivity extends AppCompatActivity {

    private ListView listView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ImageView backButton = findViewById(R.id.imageBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView = findViewById(R.id.listView);

        orderList = new ArrayList<>();
        orderList.add(new Order("LQNSU346JK", "12:30 PM", "Delivered", "Item 1, Item 2", "$30.00", "John Doe"));
        orderList.add(new Order("LQNSU124JK", "12:30 PM", "Delivered", "Item 3", "$50.00", "John Doe"));

        // Thêm nhiều đơn hàng khác vào list

        orderAdapter = new OrderAdapter(this, orderList);
        listView.setAdapter(orderAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderActivity.this, OderDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
