package com.example.projectprm.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectprm.Model.Order;
import com.example.projectprm.R;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
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

        TextView tvOrderId = convertView.findViewById(R.id.tvOrderIdLabel);
        TextView tvOrderTime = convertView.findViewById(R.id.tvOrderTime);
        TextView tvOrderStatus = convertView.findViewById(R.id.tvOrderStatus);
        TextView tvItems = convertView.findViewById(R.id.tvItems);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvName = convertView.findViewById(R.id.tvName);

        tvOrderId.setText(order.getOrderId());
        tvOrderTime.setText(order.getOrderTime());
        tvOrderStatus.setText(order.getOrderStatus());
        tvItems.setText(order.getItems());
        tvPrice.setText(order.getPrice());
        tvName.setText(order.getName());

        return convertView;
    }
}