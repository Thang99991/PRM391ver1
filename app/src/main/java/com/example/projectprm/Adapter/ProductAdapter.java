package com.example.projectprm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectprm.Model.Product;
import com.example.projectprm.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, List<Product> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_product, parent, false);
        }
        Product currentProduct = getItem(position);
        ImageView imgProduct = listItemView.findViewById(R.id.imgProduct);
        //imgProduct.setImageResource(currentProduct.getImageResId());
        imgProduct.setImageResource(R.drawable.dongho);
        TextView tvProductName = listItemView.findViewById(R.id.tvProductName);
        tvProductName.setText(currentProduct.getProduct_name());
        TextView tvProductPrice = listItemView.findViewById(R.id.tvProductPrice);

        double price = Double.parseDouble(currentProduct.getPrice_per());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedPrice = formatter.format(price) + " VND";
        tvProductPrice.setText(formattedPrice);
        TextView tvQuantity = listItemView.findViewById(R.id.tvQuantity);

        tvQuantity.setText("Quantity: "+currentProduct.getQuantity());

        return listItemView;
    }
}
