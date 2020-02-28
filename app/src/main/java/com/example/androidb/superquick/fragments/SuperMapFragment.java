package com.example.androidb.superquick.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.ActionMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidb.superquick.General.UserSessionData;
import com.example.androidb.superquick.R;
import com.example.androidb.superquick.adapters.CartListAdapter;
import com.example.androidb.superquick.adapters.CheckableCartListAdapter;
import com.example.androidb.superquick.entities.Column;
import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ProductInSuper;
import com.example.androidb.superquick.entities.Row;
import com.example.androidb.superquick.entities.ShoppingList;
import com.example.androidb.superquick.entities.Super;
import com.example.androidb.superquick.entities.Users;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SuperMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuperMapFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int STATUS_WIDTH_TOP = 1;
    private static final int STATUS_WIDTH_BOTTOM = 2;
    private static final int STATUS_HEIGHT_RIGHT = 3;
    private static final int STATUS_HEIGHT_LEFT = 4;
    private static final int STATUS_SPACE = 5;
    private static final int STATUS_CASH = 6;
    private static final int STATUS_ENTRANCE = 7;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout storeLinearLayout;

    public SuperMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuperMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuperMapFragment newInstance(String param1, String param2) {
        SuperMapFragment fragment = new SuperMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Context context;
    List<Product> shoppingListContent;
    List<ProductInShoppingList> productsAmount;
    List<ProductInSuper> productPrice;
    CheckableCartListAdapter checkableCartListAdapter;
    ListView shoppingListView;
    TextView shoppingListNameTextView;
    TextView shoppingListDateTextView;
   static int column_number = 1;
   static int row_number = 1;

    View fragmentView;
    HashMap<String, String> hashMap;
    ImageView iconView;
    RelativeLayout relativeLayout;
    static int rowSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_super_map, container, false);

        hashMap = ProductInSuper.cartProductLocationInSuper();
        relativeLayout = new RelativeLayout(getActivity());

        storeLinearLayout = fragmentView.findViewById(R.id.store);
        buildSuperMap();

        //
        dl = (DrawerLayout) fragmentView.findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(getActivity(), dl, R.string.Open, R.string.Close);

        t.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(t);
        t.syncState();
        //dl.setScrimColor(getActivity().getResources().getColor(R.color.myOrange));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) fragmentView.findViewById(R.id.nav_view);

        //
        settingHeaderContent();
        settingShoppingListContent();
        return fragmentView;
    }

    public void settingShoppingListContent() {
        //getting the  item from menu (the item number 0- it wasnt possible in different ways)
        //and converting it to list view by getActionView()
        shoppingListView = (ListView) nv.getMenu().getItem(0).getActionView();

        // select the lists for the adapter
        shoppingListContent = ProductInShoppingList.getProductsOfShoppingList();
        productsAmount = ProductInShoppingList.getProductsInShoppingList();

        // call the adapter for the cartProductView
        checkableCartListAdapter = new CheckableCartListAdapter(getActivity(), shoppingListContent, productsAmount);
        shoppingListView.setAdapter(checkableCartListAdapter);

       // HashMap<String, Product> map = new HashMap<String, Product>();

    }

    public void settingHeaderContent() {
        String shoppingListName = null;
        String shoppingListDate = "";
        if (UserSessionData.getInstance().userCurrentShoppingListId == 0) {
            if(UserSessionData.getInstance().mapShoopingListId==0){
            shoppingListName = UserSessionData.getInstance().userShoppingList.getShoppingListName();
            shoppingListDate = String.valueOf(UserSessionData.getInstance().userShoppingList.getShoppingListDate().getDate() + "/" +
                    UserSessionData.getInstance().userShoppingList.getShoppingListDate().getMonth()) + "/" +
                    UserSessionData.getInstance().userShoppingList.getShoppingListDate().getYear();}
            else{
                shoppingListName = String.valueOf(UserSessionData.getInstance().mapShoopingListId);
            }
        } else {
            ShoppingList sh_l;
            sh_l = ShoppingList.getShoppingListByListId();
            shoppingListName = sh_l.getShoppingListName();
            shoppingListDate = String.valueOf(sh_l.getShoppingListDate().getDate() + "/" +
                    sh_l.getShoppingListDate().getMonth()) + "/" +
                    sh_l.getShoppingListDate().getYear();
        }

        View header = nv.getHeaderView(0);
        shoppingListNameTextView = header.findViewById(R.id.shoppingListNameTextView);
        shoppingListNameTextView.setText(shoppingListName);

        shoppingListDateTextView = header.findViewById(R.id.shoppingListDateTextView);
        shoppingListDateTextView.setText(shoppingListDate);

        /*shoppingListView=header.findViewById(R.id.shoppingListView);

        // select the lists for the adapter
        shoppingListContent=ProductInShoppingList.getProductsOfShoppingList();
        productsAmount=ProductInShoppingList.getProductsInShoppingList();
        // call the adapter for the cartProductView
        //shoppingListView = (ListView) nv.getMenu().findItem(R.id.shoppingListView);
        checkableCartListAdapter = new CheckableCartListAdapter(getActivity(),shoppingListContent,productsAmount);
        shoppingListView.setAdapter(checkableCartListAdapter);*/
    }

    public void buildSuperMap() {
        int columnSize;
        List<Column> columnList = Super.getSuperColumns();
        columnSize = columnList.size();
        buildAllColumn(columnSize);
        for (Column c : columnList) {
            buildColumn(c);
            column_number++;
        }
    }

    private void buildColumn(Column column) {
        List<Row> rowList = Column.getColumnRows(column.getColumnId());
         rowSize = rowList.size();
        LinearLayout columnViewLL = buildColumnView(rowSize);

        for (Row row : rowList) {
            switch (row.getRowStatus()) {
                case STATUS_WIDTH_TOP:
                    buildRowWidthTopView(columnViewLL, row);
                    break;
                case STATUS_WIDTH_BOTTOM:
                    buildRowWidthBottomView(columnViewLL, row);
                    break;
                case STATUS_HEIGHT_RIGHT:
                    buildRowHeightRightView(columnViewLL, row);
                    break;
                case STATUS_HEIGHT_LEFT:
                    buildRowHeightLeftView(columnViewLL, row);
                    break;
                case STATUS_SPACE:
                    buildRowEntranceView(columnViewLL, row);
                    break;
                case STATUS_CASH:
                    buildRowCashView(columnViewLL, row);
                    break;
                case STATUS_ENTRANCE:
                    buildRowEntranceView(columnViewLL, row);
                    break;
            }
            if (row_number <= rowSize)
                row_number++;
            else
                row_number = 1;
        }
    }

    private void buildRowWidthTopView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.row_top_width, null, false);
        build(rowView, columnViewLL, row);
    }

    private void buildRowWidthBottomView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.row_bottom_width, null, false);
        build(rowView, columnViewLL, row);
    }

    private void buildRowHeightRightView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.row_right_height, null, false);
        build(rowView, columnViewLL, row);
    }

    private void buildRowHeightLeftView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.row_left_height, null, false);
        build(rowView, columnViewLL, row);
    }

    private void buildRowEntranceView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.entrance, null, false);
        build(rowView, columnViewLL, row);
    }

    private void buildRowCashView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.cash, null, false);
        build(rowView, columnViewLL, row);
    }
    private void build(View rowView, LinearLayout columnViewLL, Row row) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.weight = 1;
        rowView.setLayoutParams(layoutParams);
        //relativeLayout.addView(rowView);

        if (hashMap.containsKey(column_number + " " + row_number)) {
            //columnViewLL.addView(iconView);
            //relativeLayout.addView(iconView);
            //iconView.bringToFront();
            //columnViewLL.bringChildToFront(iconView);
            //final PopupWindow socialNetworkPopUpWindow=new PopupWindow(rowView,300, RelativeLayout.LayoutParams.WRAP_CONTENT, true);

            final ImageView icon = rowView.findViewById(R.id.ic_location);
            icon.setVisibility(View.VISIBLE);
            icon.setContentDescription(hashMap.get(column_number+" "+row_number));
            icon.setTooltipText(hashMap.get(column_number+" "+row_number));

        }
        setVisibility(rowView, row);
        if (relativeLayout.getParent() != null) {
            ((LinearLayout) relativeLayout.getParent()).removeView(relativeLayout); // <- fix

        }
        columnViewLL.addView(rowView);
        //columnViewLL.addView(relativeLayout);

    }

    private void setVisibility(View rowView, Row row) {
        if (row.isRowShow()) {
            rowView.setVisibility(View.VISIBLE);
        } else {
            rowView.setVisibility(View.INVISIBLE);
        }
    }

    private LinearLayout buildColumnView(int numRow) {
        LinearLayout columnViewLL = new LinearLayout(getActivity());
        columnViewLL.setOrientation(LinearLayout.VERTICAL);
        columnViewLL.setWeightSum(numRow);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        columnViewLL.setLayoutParams(layoutParams);
        storeLinearLayout.addView(columnViewLL);

        return columnViewLL;
    }

    private void buildAllColumn(int numColumn) {
        storeLinearLayout.setWeightSum(numColumn);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
