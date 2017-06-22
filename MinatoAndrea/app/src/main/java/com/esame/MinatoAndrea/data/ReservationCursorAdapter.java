package com.esame.MinatoAndrea.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.esame.MinatoAndrea.R;

/**
 * Created by andreaits on 24/02/17.
 */

public class ReservationCursorAdapter extends CursorAdapter {


    public ReservationCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
    }

    class ViewHolder {
        TextView txtName, txtCellPhone, txtNumber;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_reservation, null);

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
        viewHolder.txtCellPhone = (TextView) view.findViewById(R.id.txtPhone);
        viewHolder.txtNumber = (TextView) view.findViewById(R.id.txtNumber);

        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String name = cursor.getString(cursor.getColumnIndex(ReservationHelper.NAME));
        String cellPhone = cursor.getString(cursor.getColumnIndex(ReservationHelper.CELL_PHONE));
        int number = cursor.getInt(cursor.getColumnIndex(ReservationHelper.NUMBER));

        viewHolder.txtName.setText(name);
        viewHolder.txtCellPhone.setText(cellPhone);
        viewHolder.txtNumber.setText("" + number);

    }
}


