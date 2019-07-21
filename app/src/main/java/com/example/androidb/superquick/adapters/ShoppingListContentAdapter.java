package com.example.androidb.superquick.adapters;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.parse.ParseObject;

import java.util.List;

public class ShoppingListContentAdapter extends BaseAdapter {

    List<Product> shoppingListContent;
    Context context;
    List<ProductInShoppingList> productsAmount;

    public ShoppingListContentAdapter(List<Product> shoppingListContent, List<ProductInShoppingList> productsAmount, Context context) {
        this.shoppingListContent= shoppingListContent;
        this.context = context;
        this.productsAmount=productsAmount;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.sigle_product,null);
        TextView singleProduct=(TextView)convertView.findViewById(R.id.singleProduct);
        //Product p= (Product) shoppingListContent.get(position);
        singleProduct.setText(shoppingListContent.get(position).getProductDescription());

        EditText productAmountEditText = (EditText) convertView.findViewById(R.id.productAmountEditText);
        productAmountEditText.setText(String.valueOf(productsAmount.get(position).getProductInShoppingListAmount()));


        return convertView;
    }
}
