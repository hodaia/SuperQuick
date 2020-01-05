package com.example.androidb.superquick.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ProductInSuper;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class CartListAdapter extends BaseAdapter {

    Context context;
    List<Product> shoppingListContent;
    List<ProductInShoppingList> productsAmount;
    List<ProductInSuper> productPrice;
    int finalPrice;

    //com.example.androidb.superquick.adapters.ShoppingListContentAdapter adapter;

    public CartListAdapter(Context context, List<Product> shoppingListContent, List<ProductInShoppingList> productsAmount, List<ProductInSuper> productPrice) {
        this.context = context;
        this.shoppingListContent = shoppingListContent;
        this.productsAmount = productsAmount;
        this.productPrice = productPrice;
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
        convertView = layoutInflater.inflate(R.layout.single_cart_product, null);

        TextView cartProductName = (TextView) convertView.findViewById(R.id.cartProductName);
        cartProductName.setText(shoppingListContent.get(position).getProductDescription());

        TextView cartProductPriceCalculation = (TextView) convertView.findViewById(R.id.cartProductPriceCalculation);
        cartProductPriceCalculation.setText(String.valueOf(productsAmount.get(position).getProductInShoppingListAmount())+"x"+String.valueOf(productPrice.get(position).getProductInSuperPrice()));

        TextView cartProductFinalPrice = (TextView) convertView.findViewById(R.id.cartProductFinalPrice);
        finalPrice=((productsAmount.get(position).getProductInShoppingListAmount())*(productPrice.get(position).getProductInSuperPrice()));
        cartProductFinalPrice.setText(String.valueOf(finalPrice)+"שח");

        return convertView;
    }
}

