package com.example.andrea.datasource.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.andrea.datasource.R;

import java.util.ArrayList;

/**
 * Created by andrea on 12/2/16.
 */

public class ContactsAdapter extends BaseAdapter {

    Context context;
    ArrayList<Contact> contacts;


    private static class ViewHolder {
        private TextView txtId;
        private TextView txtName;
        private TextView txtSurname;

        public ViewHolder(TextView txtId, TextView txtName, TextView txtSurname) {
            this.txtId = txtId;
            this.txtName = txtName;
            this.txtSurname = txtSurname;
        }
    }


    public ContactsAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View cell;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            cell = inflater.inflate(R.layout.cell, null);

            TextView txtId = (TextView) cell.findViewById(R.id.txtId);
            TextView txtName = (TextView) cell.findViewById(R.id.txtName);
            TextView txtSurname = (TextView) cell.findViewById(R.id.txtxSurname);

            ViewHolder holder = new ViewHolder(txtId, txtName, txtSurname);

            cell.setTag(holder);

        } else {
            cell = view;
        }


        Contact contact = getItem(i);

        ViewHolder viewHolder = (ViewHolder) cell.getTag();
        viewHolder.txtId.setText("" + contact.getId());
        viewHolder.txtName.setText(contact.getName());
        viewHolder.txtSurname.setText(contact.getSurname());

        return cell;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contacts.get(i).getId();
    }
}
