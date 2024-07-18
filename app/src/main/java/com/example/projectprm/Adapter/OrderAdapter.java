package com.example.projectprm.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.Order;
import com.example.projectprm.R;

import java.text.DecimalFormat;
import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<Order> orderList;
    private Intent intent;

    public OrderAdapter(Context context, List<Order> orderList,Intent intent) {
        this.context = context;
        this.orderList = orderList;
        this.intent = intent;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_order, parent, false);
        }

        Order order = orderList.get(position);
        Account account = (Account) intent.getSerializableExtra("account");
        TextView tvOrderId = convertView.findViewById(R.id.tvOrderIdLabel);
        TextView tvOrderTime = convertView.findViewById(R.id.tvOrderTime);
        TextView tvOrderStatus = convertView.findViewById(R.id.tvOrderStatus);
        TextView tvItems = convertView.findViewById(R.id.tvItems);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvName = convertView.findViewById(R.id.tvName);

        tvOrderId.setText(order.getId());
        tvOrderTime.setText(order.getCreated_at());
        tvOrderStatus.setText(order.getStatus());
        tvItems.setText(order.getQuantity());

        double price = Double.parseDouble(order.getTotal_cost());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedPrice = formatter.format(price) + " VND";
        tvPrice.setText(formattedPrice);
        tvName.setText(account.getUsername());

        return convertView;
    }

}