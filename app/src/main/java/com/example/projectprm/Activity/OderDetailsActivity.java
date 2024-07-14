package com.example.projectprm.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.Adapter.ProductAdapter;
import com.example.projectprm.Model.Product;
import com.example.projectprm.R;

import java.util.ArrayList;

public class OderDetailsActivity  extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView tvState;
    private Button btnNext;
    private int currentProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        progressBar = findViewById(R.id.progressBar);
        tvState = findViewById(R.id.tvState);
        btnNext = findViewById(R.id.btnNext);

        // Initial state
        updateState(currentProgress);

        // Handle button click to advance state
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentProgress += 25;
                if (currentProgress > 100) {
                    currentProgress = 0;
                }
                updateState(currentProgress);
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

    private void updateState(int progress) {
        progressBar.setProgress(progress);
        switch (progress) {
            case 0:
                tvState.setText("State: Not Started");
                break;
            case 25:
                tvState.setText("State: In Progress - Step 1");
                break;
            case 50:
                tvState.setText("State: In Progress - Step 2");
                break;
            case 75:
                tvState.setText("State: In Progress - Step 3");
                break;
            case 100:
                tvState.setText("State: Completed");
                break;
        }
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", "$10.00", R.drawable.ic_launcher_foreground));
        products.add(new Product("Product 2", "$20.00", R.drawable.ic_launcher_foreground));
        products.add(new Product("Product 3", "$30.00", R.drawable.ic_launcher_foreground));

        // Find ListView and set the adapter
        ListView lvProducts = findViewById(R.id.lvProducts);
        ProductAdapter adapter = new ProductAdapter(this, products);
        lvProducts.setAdapter(adapter);
    }

}
