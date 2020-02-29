package com.example.androidb.superquick.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.activities.MapsListMenuActivity;
import com.example.androidb.superquick.activities.ShoppingListContentActivity;
import com.example.androidb.superquick.activities.ShoppingListProcessActivity;
import com.example.androidb.superquick.activities.ShoppingListsMenuActivity;
import com.example.androidb.superquick.entities.Map;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ShoppingList;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class MapsMenuAdapter extends BaseAdapter {
    List<Map> maps;
    Context context;
    MapsMenuAdapter adapter;

    public MapsMenuAdapter(List<Map> maps, Context context) {
        this.maps = maps;
        this.context = context;
        this.adapter = this;
    }

    @Override
    public int getCount() {
        return maps.size();
    }

    @Override
    public Object getItem(int position) {
        return maps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.single_map, null);

        TextView map_super_name = (TextView) convertView.findViewById(R.id.map_super_name);
        map_super_name.setText(String.valueOf(maps.get(position).getMap_superName()));

        TextView map_shopping_lis_name = (TextView) convertView.findViewById(R.id.map_shopping_lis_name);
        map_shopping_lis_name.setText(String.valueOf(maps.get(position).getMap_shoppingListName()));

        RelativeLayout singleMap = (RelativeLayout) convertView.findViewById(R.id.singleMap);

        //delete list btn
        ImageButton mapDeleteIcon = (ImageButton) convertView.findViewById(R.id.mapDeleteIcon);
        mapDeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean erase = UserSessionData.createAlertDialog(view.getResources().getString(R.string.deleteAlertDialogMsg),
                        R.string.deleteAlertDialogTitle,
                        view.getResources().getString(R.string.deleteMapsAlertDialogMsg), ((MapsListMenuActivity) context));
                //boolean y = UserSessionData.getErase();
                //if (UserSessionData.getErase()) {
                //erase shopping list

                    maps.get(position).deleteInBackground();
                    adapter.notifyDataSetChanged();

                //}
            }
        });

        singleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //UserSessionData.getInstance().userCurrentShoppingListId = shoppingLists.get(position).getShoppingListId();
                UserSessionData.getInstance().mapShoopingListId = maps.get(position).getMap_shoppingListId();
                intent.setClass(context, ShoppingListProcessActivity.class);
                intent.putExtra("specificFragment", "SuperMapFragment");
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
