package com.example.andreaits.cityprovider.data;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andreaits.cityprovider.R;

import java.util.Date;

/**
 * Created by andreaits on 13/01/17.
 */

public class DetectionCursorAdapter extends CursorAdapter {


    public DetectionCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
    }

    private static class DetectionViewHolder {
        TextView txtCityName, txtDegrees, txtHours;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_detection, null);

        DetectionViewHolder viewHolder = new DetectionViewHolder();
        viewHolder.txtCityName = (TextView) view.findViewById(R.id.txtCityNameDetail);
        viewHolder.txtDegrees = (TextView) view.findViewById(R.id.txtDegree);
        viewHolder.txtHours = (TextView) view.findViewById(R.id.txtHour);

        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        DetectionViewHolder viewHolder = (DetectionViewHolder) view.getTag();

        //String name = cursor.getString(cursor.getColumnIndex(CityHelper.NAME));

        Long city_id = cursor.getLong(cursor.getColumnIndex(DetectionHelper.CITY_ID));
        Date date = new Date(cursor.getLong(cursor.getColumnIndex(DetectionHelper.HOUR)));
        Double degrees = cursor.getDouble(cursor.getColumnIndex(DetectionHelper.DEGREES));


        viewHolder.txtCityName.setText("" + city_id);
        viewHolder.txtDegrees.setText("" + degrees);
        viewHolder.txtHours.setText("" + date);

    }
}
