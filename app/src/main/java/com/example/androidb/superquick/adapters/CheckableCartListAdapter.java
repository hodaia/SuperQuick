package com.example.androidb.superquick.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ProductInSuper;

import java.util.List;

public class CheckableCartListAdapter extends BaseAdapter {

    Context context;
    List<Product> shoppingListContent;
    List<ProductInShoppingList> productsAmount;

    //com.example.androidb.superquick.adapters.ShoppingListContentAdapter adapter;

    public CheckableCartListAdapter(Context context, List<Product> shoppingListContent, List<ProductInShoppingList> productsAmount) {
        this.context = context;
        this.shoppingListContent = shoppingListContent;
        this.productsAmount = productsAmount;
        //this.adapter=this;
    }

    @Override
    public int getCount() {
        return shoppingListContent.size();
    }

    @Override
    public Object getItem(int position) {
        return shoppingListContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.single_cart_checkable_product, null);

        TextView cartProductName = (TextView) convertView.findViewById(R.id.cartProductName);
        cartProductName.setText(shoppingListContent.get(position).getProductDescription());

        TextView cartProductAmount = (TextView) convertView.findViewById(R.id.cartProductAmount);
        cartProductAmount.setText(String.valueOf(productsAmount.get(position).getProductInShoppingListAmount()));

        return convertView;
    }
}

