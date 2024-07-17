package com.example.projectprm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.Activity.ProductDetailActivity;
import com.example.projectprm.Model.ProductCart;
import com.example.projectprm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ProductViewHolder> {

    private List<ProductCart> productList;
    private Context context;

    public ProductCartAdapter(Context context, List<ProductCart> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductCart product = productList.get(position);
        holder.titleTextView.setText(product.getName());
        holder.priceTextView.setText(product.getPrice());
        Picasso.get().load(product.getImg()).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_id", product.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView priceTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
        }
    }
}
