package com.example.projectprm.Adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.projectprm.Model.ProductCart;
import com.example.projectprm.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ProductViewHolder> {

    private List<ProductCart> productList;
    private List<String> imageUrls;
    private Handler handler = new Handler();
    private int currentPosition = 0;


    public ProductCartAdapter(List<ProductCart> productList) {
        this.productList = productList;
        startAutomaticImageSwitch();

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

        double price = Double.parseDouble(product.getPrice());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedPrice = formatter.format(price) + " VND";
        holder.priceTextView.setText(formattedPrice);

        List<String> imageUrls = product.getImg();
        Picasso.get().load(imageUrls.get(currentPosition % imageUrls.size())).into(holder.imageView);
    }
    private void startAutomaticImageSwitch() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPosition = (currentPosition + 1) % getMaxImageCount(); // Chuyển đổi giữa các ảnh
                notifyDataSetChanged(); // Cập nhật lại RecyclerView để hiển thị ảnh mới
                handler.postDelayed(this, 5000); // Lặp lại sau mỗi 5 giây
            }
        }, 15000); // Bắt đầu sau 15 giây
    }

    private int getMaxImageCount() {
        int maxCount = 0;
        for (ProductCart product : productList) {
            maxCount = Math.max(maxCount, product.getImg().size());
        }
        return maxCount;
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
