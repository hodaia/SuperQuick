package com.example.androidb.superquick.adapters;

import android.content.Context;
import android.content.Intent;
import android.icu.text.TimeZoneFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.ShoppingListContentActivity;
import com.example.androidb.superquick.dialogs.CartDialogFragment;
import com.example.androidb.superquick.dialogs.CitiesFragmentDialog;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ProductInSuper;
import com.example.androidb.superquick.entities.Super;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.parse.ParseObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SuperListAdapter extends BaseAdapter {
    List<Super> supers;
    Context context;
    Adapter adapter;

    public SuperListAdapter(List<Super> supers, Context context) {
        this.supers = supers;
        this.context = context;
        adapter = this;
    }

    @Override
    public int getCount() {
        return supers.size();
    }

    @Override
    public Object getItem(int position) {
        return supers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.single_super, null);

        TextView singleSuper = (TextView) convertView.findViewById(R.id.singleSuper);
        //singleSuper.setText(supers.get(position).getSuperName());
        singleSuper.setText(UserSessionData.getInstance().superstotalPrice.get(position).getSuperName());
        TextView totalPriceTextView = (TextView) convertView.findViewById(R.id.totalPriceTextView);

        float totalPrice = ProductInSuper.shoppingListCostInSuper(supers.get(position).getSuperId());
//                        supers.get(position).setTotalPrice((int)totalPrice);
//                        supers.get(position).saveInBackground();

        // totalPriceTextView.setText(String.valueOf(totalPrice));
        if (UserSessionData.getTotalPrice(position) == 0) {
            UserSessionData.setTotalPrice(position, (int) totalPrice);
        }
        totalPriceTextView.setText(String.valueOf(UserSessionData.getTotalPrice(position)));

        //}else{
        // totalPriceTextView.setText(String.valueOf(supers.get(position).getTotalPrice()));
        //}
        //ProductInSuper.shoppingListCostInSuper(supers.get(position).getSuperId(),shoppingListId);
        singleSuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSessionData.ChosenSuper(supers.get(position).getSuperId());
                FragmentManager ft = ((AppCompatActivity) context).getSupportFragmentManager();
                CartDialogFragment cartDialogFragment = new CartDialogFragment();
                cartDialogFragment.show(ft, "i");

            }
        });

        return convertView;
    }

    public void sort() {
        Collections.sort(UserSessionData.getInstance().superstotalPrice);
    }
}

