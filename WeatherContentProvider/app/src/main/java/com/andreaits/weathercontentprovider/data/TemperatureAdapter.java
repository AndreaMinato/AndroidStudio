package com.andreaits.weathercontentprovider.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.andreaits.weathercontentprovider.R;

import java.text.Format;
import java.text.SimpleDateFormat;


public class TemperatureAdapter extends CursorAdapter{

    public TemperatureAdapter(Context context, Cursor c) {
        super(context, c, false);
    }
    class ViewHolder{
        TextView mDataLabel;
        TextView mTemperatureLabel;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View vView = LayoutInflater.from(context).inflate(R.layout.temperature_view, null);
        ViewHolder vHolder = new ViewHolder();
        vHolder.mTemperatureLabel = (TextView) vView.findViewById(R.id.temperatureLabel);
        vHolder.mDataLabel = (TextView) vView.findViewById(R.id.dataLabel);
        vView.setTag(vHolder);
        return vView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder vHolder = (ViewHolder) view.getTag();
        vHolder.mTemperatureLabel.setText("" + cursor.getFloat(cursor.getColumnIndex(TemperatureHelper.TEMPERATURE)) + "°");
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String vDate = formatter.format(cursor.getLong(cursor.getColumnIndex(TemperatureHelper.DATA)));
        vHolder.mDataLabel.setText(vDate);

    }
}
