package com.example.andrea.conternprovider.data;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andrea.conternprovider.R;

/**
 * Created by andreaits on 23/12/16.
 */

public class ContactCursorAdapter extends CursorAdapter {


    public ContactCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
    }

    class ViewHolder {
        TextView txtName, txtSurname;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell, null);

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
        viewHolder.txtSurname = (TextView) view.findViewById(R.id.txtSurname);

        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String name = cursor.getString(cursor.getColumnIndex(ContactsHelper.NAME));
        String surname = cursor.getString(cursor.getColumnIndex(ContactsHelper.SURNAME));

        viewHolder.txtName.setText(name);
        viewHolder.txtSurname.setText(surname);

    }
}
