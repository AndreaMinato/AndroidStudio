package com.andrea.workouttimer.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.andrea.workouttimer.R;

import java.util.Date;


public class WorkoutAdapter extends CursorAdapter {


    public WorkoutAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
    }

    class ViewHolder {
        TextView txtPlace, txtDate, txtDuration;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_workout, null);

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.txtPlace = (TextView) view.findViewById(R.id.txtWorkoutPlace);
        viewHolder.txtDate = (TextView) view.findViewById(R.id.txtWorkoutDate);
        viewHolder.txtDuration = (TextView) view.findViewById(R.id.txtWorkoutDuration);

        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String place = cursor.getString(cursor.getColumnIndex(WorkoutHelper.PLACE));
        Date date = new Date(cursor.getLong(cursor.getColumnIndex(WorkoutHelper.WORKOUT_DATE)));
        int duration = cursor.getInt(cursor.getColumnIndex(WorkoutHelper.DURATION));

        viewHolder.txtPlace.setText(place);
        viewHolder.txtDate.setText(date.toString());
        viewHolder.txtDuration.setText("" + duration);

    }
}