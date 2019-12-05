package com.example.androidb.superquick.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.LoginActivity;
import com.example.androidb.superquick.activities.ShoppingListContentActivity;
import com.example.androidb.superquick.activities.ShoppingListsMenuActivity;
import com.example.androidb.superquick.activities.SplashActivity;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ShoppingList;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class ShoppingListMenuAdapter extends BaseAdapter {

    List<ShoppingList> shoppingLists;
    Context context;
    ShoppingListMenuAdapter adapter;

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
        singleShoppingListDate.setText(String.valueOf(shoppingLists.get(position).getCreatedAt().getDay()+"/"+
                shoppingLists.get(position).getCreatedAt().getMonth())+"/"+
                shoppingLists.get(position).getCreatedAt().getYear());

        LinearLayout singleShoppingList=(LinearLayout)convertView.findViewById(R.id.singleShoppingList);

        ImageButton productDeleteIcon=(ImageButton)convertView.findViewById(R.id.productDeleteIcon);
        productDeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserSessionData.createAlertDialog(view.getResources().getString(R.string.deleteAlertDialogMsg),
                        R.string.deleteAlertDialogTitle,
                        view.getResources().getString(R.string.deleteShoppingListAlertDialogMsg),((ShoppingListsMenuActivity) context));
                boolean y=UserSessionData.getErase();
                if(UserSessionData.getErase()) {
                    //erase shopping list
                    ParseQuery<ShoppingList> shoppingListToDelete = ParseQuery.getQuery("ShoppingList");
                    shoppingListToDelete.getInBackground(shoppingLists.get(position).getObjectId(), new GetCallback<ShoppingList>() {
                        public void done(final ShoppingList entity, ParseException e) {
                            if (e == null) {
                                ParseQuery<ProductInShoppingList> productToDelete = ParseQuery.getQuery("ProductInShoppingList");
                                productToDelete.whereEqualTo("productInShoppingList_shoppingListId", shoppingLists.get(position).getShoppingListId());
                                productToDelete.findInBackground(new FindCallback<ProductInShoppingList>() {
                                    @Override
                                    public void done(List<ProductInShoppingList> list, ParseException e) {

                                        if (e == null) {
                                            for (ProductInShoppingList productInShoppingList : list) {
                                                productInShoppingList.deleteInBackground();
                                            }
                                            entity.deleteInBackground();
                                            adapter.notifyDataSetChanged();
                                        }
                                    }

                                });
                            }
                        }
                    });

                }}});
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
