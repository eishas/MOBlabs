package com.example.lab2;

import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class OptionsFragment extends Fragment
{
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_options, container, false);
        Button button =  view.findViewById(R.id.Btn);
        final EditText et = view.findViewById(R.id.pwd);
        RadioButton rb1 = view.findViewById(R.id.radiobutton1);

        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password
                    et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
                // задаем обработчик кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().isEmpty())
                {
                    updateInfo("");
                    Toast myToast = Toast.makeText(view.getContext(), "Заполните поле пароля", Toast.LENGTH_SHORT);
                    myToast.show();
                }
                else
                {
                    updateInfo(et.getText().toString());
                }
            }
        });

        return view;
    }

    interface OnFragmentInteractionListener
    {

        void onFragmentInteraction(String link);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "OnFragmentInteractionListener");
        }
    }
    private void updateInfo(String result)
    {
        // Посылаем данные Activity
        mListener.onFragmentInteraction(result);
    }
}
