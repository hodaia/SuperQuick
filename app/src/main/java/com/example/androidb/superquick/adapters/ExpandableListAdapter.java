package com.example.androidb.superquick.adapters;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.SubCategory;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private List<SubCategory> parentList;
    private HashMap<SubCategory,List<Product>> expandableList;
    int size;
    Object obj;
    int childPosition;

    public ExpandableListAdapter(Context context, List<SubCategory> parentList, HashMap<SubCategory,List<Product>> expandableList) {
        this.context = context;
        this.parentList = parentList;
        this.expandableList = expandableList;

    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int getChildrenCount(final int groupPosition) {

         return this.expandableList.get(parentList.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(final int groupPosition) {

        return parentList.get(groupPosition);

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableList.get(parentList.get(groupPosition)).get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.single_expandable_list, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.expandableParent);
        listTitleTextView.setText(parentList.get(groupPosition).getSubCategoryName());
        return convertView;

    }


    int prevProductAmount;
    int afterProductAmount;

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.sigle_product, null);
        }
         TextView expandedListTextView = (TextView) convertView.findViewById(R.id.singleProduct);
        TextView productAmountEditText = (TextView) convertView.findViewById(R.id.productAmountEditText);

        final View finalConvertView = convertView;
        productAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                prevProductAmount = Integer.parseInt(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                afterProductAmount=Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView productAmountEditText = (TextView) finalConvertView.findViewById(R.id.productAmountEditText);

                if (prevProductAmount == 0)
                    //create a new ProductInShoppingList
                    UserSessionData.getInstance().userShoppingListContent.add(new ProductInShoppingList(UserSessionData.getInstance().userShoppingList.getShoppingListId(), expandableList.get(parentList.get(groupPosition)).get(childPosition).getProductId(),Integer.parseInt(productAmountEditText.getText().toString()) ));
                else {
                    for(ProductInShoppingList p:UserSessionData.getInstance().userShoppingListContent){
                       if(p.productInShoppingList_productId == expandableList.get(parentList.get(groupPosition)).get(childPosition).getProductId())
                           p.setProductInShoppingListAmount(Integer.parseInt(productAmountEditText.getText().toString()));
                    };
                }
            }
        });

        expandedListTextView.setText(expandableList.get(parentList.get(groupPosition)).get(childPosition).getProductDescription());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
