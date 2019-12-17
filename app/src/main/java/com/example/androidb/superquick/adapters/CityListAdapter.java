
package com.example.androidb.superquick.adapters;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.content.Intent;
        import android.icu.text.TimeZoneFormat;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.example.androidb.superquick.General.UserSessionData;
        import com.example.androidb.superquick.R;
        import com.example.androidb.superquick.activities.ShoppingListContentActivity;
        import com.example.androidb.superquick.entities.City;
        import com.example.androidb.superquick.entities.ProductInShoppingList;
        import com.example.androidb.superquick.entities.ProductInSuper;
        import com.example.androidb.superquick.entities.Super;
        import com.parse.ParseObject;

        import java.util.List;

        import static com.example.androidb.superquick.R.color.myOrange;

public class CityListAdapter extends BaseAdapter {
    List<City> cities;
    Context context;

    public CityListAdapter(List<City> cities, Context context) {
        this.cities = cities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.single_city,null);
        TextView singleCity=(TextView) convertView.findViewById(R.id.singleCity);
        singleCity.setText(cities.get(position).getCityName());
        final RelativeLayout cityRelativeLayout=(RelativeLayout) convertView.findViewById(R.id.cityRelativeLayout);

        singleCity.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                UserSessionData.getInstance().userCityId=cities.get(position).getCityId();
            }
        });
        /*singleCity.setOnHoverListener(new View.OnHoverListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onHover(View v, MotionEvent event) {
                cityRelativeLayout.setBackgroundColor(myOrange);
                //cityRelativeLayout.
                return false;
            }
        });
       final View finalConvertView = convertView;
      /*   convertView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                cityRelativeLayout.setBackgroundColor(myOrange);
            }
        });*/
        return convertView;
    }
}


