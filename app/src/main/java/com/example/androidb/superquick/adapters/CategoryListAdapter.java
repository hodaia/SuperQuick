package com.example.androidb.superquick.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.dialogs.ProductDialogFragment;
import com.example.androidb.superquick.entities.Category;
import com.example.androidb.superquick.entities.ShoppingList;
import com.example.androidb.superquick.fragments.ShoppingCategoriesFragment;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class CategoryListAdapter extends BaseAdapter {
    Context context;
    List<Category> categories;
    int shoppintListId;


    int categoryId;
    public CategoryListAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
        this.shoppintListId=shoppintListId;
    }


    @Override
    public int getCount()
    {
       return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.single_category, null);

        TextView singleCategory = (TextView) convertView.findViewById(R.id.singleCategory);
        singleCategory.setText(categories.get(position).getString("categoryName"));


        singleCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categoryId=categories.get(position).getInt("categoryId");

                FragmentManager ft = ((AppCompatActivity) context).getSupportFragmentManager();
                ProductDialogFragment productDialogFragment = new ProductDialogFragment(categoryId);
                productDialogFragment.show(ft, "i");
            }
        });

        return convertView;
    }
}
