package com.andrea.calcolomedico.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andrea.calcolomedico.MainActivity;
import com.andrea.calcolomedico.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Andrea on 23/06/17.
 */

public class AtFragment extends Fragment {


    private static final String PESO = "PESO";
    private static final String RESULT = "RESULT";
    private static final String LIV = "LIV";
    private static final String ATTIVITA = "ATTIVITA";
    private Unbinder unbinder;

    @BindView(R.id.txtPesoAt)
    EditText txtPeso;
    @BindView(R.id.txtLiv)
    EditText txtLivello;
    @BindView(R.id.txtAttivita)
    EditText txtAttivita;
    @BindView(R.id.txtResultAt)
    TextView txtResult;

    @BindView(R.id.btnSubmit)
    Button btnUnder2;


    public AtFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vView = inflater.inflate(R.layout.fragment_at, container, false);

        unbinder = ButterKnife.bind(this, vView);

        if (savedInstanceState != null) {
            txtPeso.setText(savedInstanceState.getString(PESO));
            txtLivello.setText(savedInstanceState.getString(LIV));
            txtAttivita.setText(savedInstanceState.getString(ATTIVITA));
            txtResult.setText(savedInstanceState.getString(RESULT));
        }

        return vView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putString(PESO, txtPeso.getText().toString());
        outState.putString(LIV, txtLivello.getText().toString());
        outState.putString(ATTIVITA, txtAttivita.getText().toString());

        outState.putString(RESULT, txtResult.getText().toString());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private String getResult() {
        String vPeso = txtPeso.getText().toString();
        String vLivello = txtLivello.getText().toString();
        String vAttivita = txtAttivita.getText().toString();
        if (!"".equals(vPeso) && !"".equals(vLivello) && !"".equals(vAttivita))
            return String.format(Locale.ITALY, "%.1f AT III", Integer.parseInt(vPeso) * (Integer.parseInt(vLivello) - Integer.parseInt(vAttivita)) / 1.5f);
        return "...";
    }

    @OnClick(R.id.btnSubmit)
    public void submit() {
        txtResult.setText(getResult());
    }
}
