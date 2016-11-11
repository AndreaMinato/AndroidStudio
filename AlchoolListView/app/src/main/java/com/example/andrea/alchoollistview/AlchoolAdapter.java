package com.example.andrea.alchoollistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andrea on 11/11/16.
 */

public class AlchoolAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Alchool> alchools;

    private static class ViewHolder {
        private TextView txtName;
        private TextView txtLiter;

        public ViewHolder(TextView txtName, TextView txtLiter) {
            this.txtName = txtName;
            this.txtLiter = txtLiter;
        }
    }

    public AlchoolAdapter(Context context, ArrayList<Alchool> alchools) {
        this.context = context;
        this.alchools = alchools;
    }

    @Override
    public int getCount() {
        return alchools.size();
    }

    @Override
    public Alchool getItem(int i) {
        return alchools.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alchools.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View alchoolCell;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            alchoolCell = inflater.inflate(R.layout.alchool_layout, null);

            TextView txtName = (TextView) alchoolCell.findViewById(R.id.txtName);
            TextView txtLiter = (TextView) alchoolCell.findViewById(R.id.txtLiter);

            ViewHolder holder = new ViewHolder(txtName, txtLiter);

            alchoolCell.setTag(holder);

        } else {
            alchoolCell = view;
        }


        Alchool alchool = getItem(i);

        ViewHolder viewHolder = (ViewHolder) alchoolCell.getTag();
        viewHolder.txtName.setText(alchool.getName());
        viewHolder.txtLiter.setText(alchool.getLiter() + " lt");

        return alchoolCell;
    }
}
