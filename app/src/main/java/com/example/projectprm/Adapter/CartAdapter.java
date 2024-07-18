package com.example.projectprm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.Model.Cart;
import com.example.projectprm.Model.Product;
import com.example.projectprm.Model.ProductCart;
import com.example.projectprm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<ProductCart> cartItems;
    private CartItemListener cartItemListener;

    public CartAdapter(Context context, List<ProductCart> cartItems, CartItemListener cartItemListener) {
        this.context = context;
        this.cartItems = cartItems;
        this.cartItemListener = cartItemListener;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        ProductCart cartItem = cartItems.get(position);
        holder.tvProductName.setText(cartItem.getName());
        holder.tvProductPrice.setText(cartItem.getPrice());
        holder.tvQuantity.setText(String.valueOf(cartItem.getQuantity()));

        List<String> images = cartItem.getImg();
        if (images != null && !images.isEmpty()) {
            Picasso.get().load(images.get(0)).into(holder.imgProduct);
        }

        holder.btnIncrease.setOnClickListener(v -> cartItemListener.onIncreaseQuantity(cartItem));
        holder.btnDecrease.setOnClickListener(v -> cartItemListener.onDecreaseQuantity(cartItem));
        holder.btnRemove.setOnClickListener(v -> cartItemListener.onRemoveItem(cartItem));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public interface CartItemListener {
        void onIncreaseQuantity(ProductCart cartItem);
        void onDecreaseQuantity(ProductCart cartItem);
        void onRemoveItem(ProductCart cartItem);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvProductName, tvProductPrice, tvQuantity;
        ImageButton btnIncrease, btnDecrease, btnRemove;

        public CartViewHolder(View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}

