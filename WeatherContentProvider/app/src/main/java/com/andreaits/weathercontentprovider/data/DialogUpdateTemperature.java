package com.andreaits.weathercontentprovider.data;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.andreaits.weathercontentprovider.R;

import java.text.Format;
import java.text.SimpleDateFormat;


public class DialogUpdateTemperature extends DialogFragment {
    private static final String TEMP_ID = "TEMP_ID";
    private static final String TEMPERATURE = "Temperature_save";
    private static final String DATE = "Date_save";
    private static final String TITLE = "MODIFICA";
    private static final String MESSAGE = "Salvare le modifice?";
    private EditText mTemperatureText;
    private TextView mDateView;
    private long mDate;
    private long mPosition;

    public static DialogUpdateTemperature getInstance(long aTempId){
        DialogUpdateTemperature vFrag = new DialogUpdateTemperature();
        Bundle vBundle = new Bundle();
        vBundle.putLong(TEMP_ID, aTempId);
        vFrag.setArguments(vBundle);
        return vFrag;
    }

    public interface IOUpdateTemperature{
        void update(long aTempID, float aTemperature);
    }

    private IOUpdateTemperature mListener = new IOUpdateTemperature() {
        @Override
        public void update(long aTempID, float aTemperature) {

        }
    };

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder vBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater vInflater = getActivity().getLayoutInflater();
        View vView = vInflater.inflate(R.layout.temperature_dialog, null);
        mTemperatureText = (EditText) vView.findViewById(R.id.temperatureEditText);
        mDateView = (TextView) vView.findViewById(R.id.dateTextView);
        vBuilder.setView(vView);

        mPosition = getArguments().getLong(TEMP_ID);

        if (savedInstanceState != null){
            mTemperatureText.setText(savedInstanceState.getString(TEMPERATURE));
            mDate = savedInstanceState.getLong(DATE);
        } else {
            Uri vUriQuery = Uri.parse(MyContentProvider.TEMPERATURE_URI + "/" + mPosition);
            Cursor vCursor = getContext().getContentResolver().query(vUriQuery,null,null,null,null);
            while (vCursor.moveToNext()){
                mTemperatureText.setText("" + vCursor.getFloat(vCursor.getColumnIndex(TemperatureHelper.TEMPERATURE)));
                mDate = vCursor.getLong(vCursor.getColumnIndex(TemperatureHelper.DATA));
            }
            vCursor.close();
        }
        Format vFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String vDate = vFormatter.format(mDate);
        mDateView.setText(vDate);

        vBuilder.setTitle(TITLE)
                .setMessage(MESSAGE)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.update(mPosition, Float.parseFloat(mTemperatureText.getText().toString().replace(",",".")));
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return vBuilder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOUpdateTemperature)
            mListener = (IOUpdateTemperature)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
