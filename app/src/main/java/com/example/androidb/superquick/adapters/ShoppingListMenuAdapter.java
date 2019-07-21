package com.example.androidb.superquick.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.LoginActivity;
import com.example.androidb.superquick.activities.ShoppingListContentActivity;
import com.example.androidb.superquick.activities.SplashActivity;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ShoppingList;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class ShoppingListMenuAdapter extends BaseAdapter {

    List<ShoppingList> shoppingLists;
    Context context;

    public ShoppingListMenuAdapter(List<ShoppingList> shoppingLists, Context context) {
        this.shoppingLists = shoppingLists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return shoppingLists.size();
    }

    @Override
    public Object getItem(int position) {
        return shoppingLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.single_shopping_list,null);

        TextView singleShoppingListName=(TextView)convertView.findViewById(R.id.singleShoppingListName);
        singleShoppingListName.setText(shoppingLists.get(position).getShoppingListName());

        TextView singleShoppingListDate=(TextView)convertView.findViewById(R.id.singleShoppingListDate);
        singleShoppingListDate.setText(shoppingLists.get(position).getShoppingListDate().toString());

        LinearLayout singleShoppingList=(LinearLayout)convertView.findViewById(R.id.singleShoppingList);


        singleShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("shoppingListId",shoppingLists.get(position).getInt(("shoppingListId")));
                intent.setClass(context, ShoppingListContentActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
