package com.example.projectprm.Adapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectprm.Activity.AccountActivity;
import com.example.projectprm.Activity.AddAddressActivity;
import com.example.projectprm.Activity.AddressActivity;
import com.example.projectprm.Activity.ConfirmAddressActivity;
import com.example.projectprm.Activity.EditAddressActivity;
import com.example.projectprm.Activity.ProfileActivity;
import com.example.projectprm.Model.Account;
import com.example.projectprm.Model.Address;
import com.example.projectprm.R;

import java.util.List;

public class AddressAdapter extends BaseAdapter {
    private Context context;
    private List<Address> addressList;
    private Intent intent;

    public AddressAdapter(Context context, List<Address> addressList,Intent intent) {
        this.context = context;
        this.addressList = addressList;
        this.intent = intent;
    }


    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public Object getItem(int position) {
        return addressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_address, parent, false);
        }

        Address address = addressList.get(position);
        Account account = (Account) intent.getSerializableExtra("account");

        TextView tvFirstName = convertView.findViewById(R.id.tvFirstName);
        TextView tvAddress = convertView.findViewById(R.id.tvAddress);
        TextView tvPhoneNumber = convertView.findViewById(R.id.tvPhoneNumber);
        Button btnEdit = convertView.findViewById(R.id.btnEdit);
        ImageView imageViewDelete = convertView.findViewById(R.id.imageViewDelete);

        tvFirstName.setText(account.getUsername());
        tvAddress.setText(address.getAddress());
        tvPhoneNumber.setText(account.getPhone());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditAddressActivity.class);
                intent.putExtra("address", address);
                ((Activity) context).startActivityForResult(intent, 1);
            }
        });

        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConfirmAddressActivity.class);
                intent.putExtra("address", address);
                ((Activity) context).startActivityForResult(intent, 1);            }
        });

        return convertView;
    }
}
