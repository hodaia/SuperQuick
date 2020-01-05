package com.example.androidb.superquick.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.androidb.superquick.R;
import com.example.androidb.superquick.entities.Column;
import com.example.androidb.superquick.entities.Row;
import com.example.androidb.superquick.entities.Super;

import java.util.List;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_super_map, container, false);
        storeLinearLayout = fragmentView.findViewById(R.id.store);
        buildSuperMap();
        return fragmentView;
    }


    public void buildSuperMap() {
        int columnSize;
        List<Column> columnList = Super.getSuperColumns();
        columnSize = columnList.size();
        buildAllColumn(columnSize);
        for (Column c : columnList) {
            buildColumn(c);

        }
    }

    private void buildColumn(Column column) {
        List<Row> rowList = Column.getColumnRows(column.getColumnId());
        int rowSize = rowList.size();
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
        }
    }

    private void buildRowWidthTopView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.row_top_width, null, false);
        build(rowView,columnViewLL,row);
    }
    private void buildRowWidthBottomView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.row_bottom_width, null, false);
        build(rowView,columnViewLL,row);
    }
    private void buildRowHeightRightView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.row_right_height, null, false);
        build(rowView,columnViewLL,row);
    }
    private void buildRowHeightLeftView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.row_left_height, null, false);
        build(rowView,columnViewLL,row);
    }
    private void buildRowEntranceView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.entrance, null, false);
        build(rowView,columnViewLL,row);
    }
    private void buildRowCashView(LinearLayout columnViewLL, Row row) {
        View rowView = LayoutInflater.from(getActivity()).inflate(R.layout.cash, null, false);
        build(rowView,columnViewLL,row);
    }
    private void build(View rowView,LinearLayout columnViewLL, Row row) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.weight = 1;
        rowView.setLayoutParams(layoutParams);
        setVisibility(rowView, row);
        columnViewLL.addView(rowView);
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
}
