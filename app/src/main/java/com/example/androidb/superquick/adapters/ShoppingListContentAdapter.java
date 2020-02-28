package com.example.androidb.superquick.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.entities.Category;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ShoppingList;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collections;
import java.util.List;

public class ShoppingListContentAdapter extends BaseAdapter {

    List<Product> shoppingListContent;
    Context context;
    List<ProductInShoppingList> productsAmount;
    ShoppingListContentAdapter adapter;

    public ShoppingListContentAdapter(List<Product> shoppingListContent, List<ProductInShoppingList> productsAmount, Context context) {
        this.shoppingListContent = shoppingListContent;
        this.context = context;
        this.productsAmount = productsAmount;
        this.adapter = this;
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
        convertView = layoutInflater.inflate(R.layout.single_list_product, null);
        TextView singleProduct = (TextView) convertView.findViewById(R.id.singleProduct);
        //Product p= (Product) shoppingListContent.get(position);
        singleProduct.setText(shoppingListContent.get(position).getProductDescription());

        EditText productAmountEditText = (EditText) convertView.findViewById(R.id.productAmountEditText);
        productAmountEditText.setText(String.valueOf(productsAmount.get(position).getProductInShoppingListAmount()));
        productAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                prevProductAmount = Integer.parseInt(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                afterProductAmount=Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                {
                    if (s.toString().equals("")) {
                        productsAmount.get(position).setProductInShoppingListAmount(0);
                    } else {
                        productsAmount.get(position).setProductInShoppingListAmount(Integer.valueOf(s.toString()));
                    }
                    productsAmount.get(position).saveInBackground();
                    adapter.notifyDataSetChanged();
                }
                ;
            }
        });

        ImageView deleteIcon = (ImageView) convertView.findViewById(R.id.productDeleteIcon);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                                          productsAmount.get(position).deleteInBackground();
                                                          adapter.notifyDataSetChanged();
                                             //   context.removeItem(position);
                                                /*if(productsAmount.size()==1){
                                    ParseQuery<ShoppingList> productToDelete = ParseQuery.getQuery("ShoppingList");
                                    productToDelete.whereEqualTo("shoppingListId",productsAmount.get(position).getProductInShoppingList_shoppingListId())
                                    productToDelete.getInBackground(,new GetCallback<ShoppingList>(){
                                        public void done(ShoppingList entity, ParseException e) {
                                            if (e == null) {
                                            }
                                        }
                                    });
                                }*/
                                                          //productsAmount.remove(productsAmount.get(position));

                                          }
                                      }
        );
        return convertView;
    }

}
