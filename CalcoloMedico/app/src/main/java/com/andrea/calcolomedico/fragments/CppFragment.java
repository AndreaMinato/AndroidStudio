package com.andrea.calcolomedico.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andrea.calcolomedico.R;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CppFragment extends Fragment {

    private static final String PESO = "PESO";
    private static final String RESULT = "RESULT";
    private Unbinder unbinder;

    @BindView(R.id.txtPesoCpp)
    EditText txtPeso;

    @BindView(R.id.btnUnder2)
    Button btnUnder2;
    @BindView(R.id.btnBetween_2_4)
    Button btnBetween_2_4;
    @BindView(R.id.btnBetween_4_6)
    Button btnBetween_4_6;
    @BindView(R.id.btnOver6)
    Button btnOver6;
    @BindView(R.id.txtResultCpp)
    TextView txtResult;

    public CppFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vView = inflater.inflate(R.layout.fragment_cpp, container, false);

        unbinder = ButterKnife.bind(this, vView);

        if (savedInstanceState != null) {
            txtPeso.setText(savedInstanceState.getString(PESO));
            txtResult.setText(savedInstanceState.getString(RESULT));
        }


        return vView;
    }

    private String getResult(int multiplier) {
        String vPeso = txtPeso.getText().toString();
        if (!"".equals(vPeso))
            return String.format(Locale.ITALY, "%d CPP", multiplier * Integer.parseInt(vPeso));
        return "...";
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


            outState.putString(PESO, txtPeso.getText().toString());

            outState.putString(RESULT, txtResult.getText().toString());
    }


    @OnClick(R.id.btnUnder2)
    public void under2() {
        txtResult.setText(getResult(20));
    }

    @OnClick(R.id.btnBetween_2_4)
    public void between_2_4() {
        txtResult.setText(getResult(30));
    }

    @OnClick(R.id.btnBetween_4_6)
    public void between_4_6() {
        txtResult.setText(getResult(40));
    }

    @OnClick(R.id.btnOver6)
    public void over6() {
        txtResult.setText(getResult(50));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
