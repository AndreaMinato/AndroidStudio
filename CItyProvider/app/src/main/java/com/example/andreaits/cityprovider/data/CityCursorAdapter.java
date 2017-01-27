package com.example.andreaits.cityprovider.data;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andreaits.cityprovider.R;

/**
 * Created by andreaits on 13/01/17.
 */

public class CityCursorAdapter extends CursorAdapter {

    private static class CityViewHolder {
        TextView txtCityName;
    }

    private CityCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_city, null);

        CityViewHolder viewHolder = new CityViewHolder();
        viewHolder.txtCityName = (TextView) view.findViewById(R.id.txtCityName);

        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        CityViewHolder viewHolder = (CityViewHolder) view.getTag();

        String name = cursor.getString(cursor.getColumnIndex(CityHelper.NAME));

        viewHolder.txtCityName.setText(name);
    }
}
