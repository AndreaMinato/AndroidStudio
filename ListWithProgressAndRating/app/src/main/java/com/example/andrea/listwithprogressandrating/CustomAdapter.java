package com.example.andrea.listwithprogressandrating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andrea on 11/11/16.
 */

public class CustomAdapter extends BaseAdapter {

    private ArrayList<ProgressRating> progressRatings;
    private Context context;

    public CustomAdapter(ArrayList<ProgressRating> progressRatings, Context context) {
        this.progressRatings = progressRatings;
        this.context = context;
    }

    private static class ViewHolder {
        private TextView txtName;
        private ProgressBar progressBar;
        private RatingBar ratingBar;

        public ViewHolder(TextView txtName, ProgressBar progressBar, RatingBar ratingBar) {
            this.txtName = txtName;
            this.progressBar = progressBar;
            this.ratingBar = ratingBar;
        }
    }

    @Override
    public int getCount() {
        return progressRatings.size();
    }

    @Override
    public ProgressRating getItem(int i) {
        return progressRatings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return progressRatings.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View cell;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            cell = inflater.inflate(R.layout.cell, null);

            TextView txtName = (TextView) cell.findViewById(R.id.txtName);
            ProgressBar progressBar = (ProgressBar) cell.findViewById(R.id.progressBar);
            RatingBar ratingBar = (RatingBar) cell.findViewById(R.id.ratingBar);

            ViewHolder holder = new ViewHolder(txtName, progressBar, ratingBar);

            cell.setTag(holder);
        } else {
            cell = view;
        }

        ProgressRating progressRating = getItem(i);

        ViewHolder viewHolder = (ViewHolder) cell.getTag();
        viewHolder.txtName.setText(progressRating.getName());
        viewHolder.progressBar.setProgress(progressRating.getProgress());
        viewHolder.ratingBar.setRating(progressRating.getRating());


        return cell;
    }
}
