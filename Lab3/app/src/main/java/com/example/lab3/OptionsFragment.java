package com.example.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.Date;

public class OptionsFragment extends Fragment
{
    private OnFragmentInteractionListener mListener;
    private DbContext dbContext;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_options, container, false);
        Button button =  view.findViewById(R.id.Btn);
        Button showDB_button = view.findViewById(R.id.Btn2);
        final EditText et = view.findViewById(R.id.pwd);
        RadioButton rb1 = view.findViewById(R.id.radiobutton1);
        dbContext = new DbContext(view.getContext());
        intent = new Intent(view.getContext(), DbActivity.class);

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
        showDB_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(intent);
            }
        });
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
        SQLiteDatabase database = dbContext.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // Посылаем данные Activity
        String curDate = new Date().toString();
        contentValues.put(DbContext.KEY_RESULT, result);
        contentValues.put(DbContext.KEY_DATE, curDate);
        database.insert(DbContext.TABLE_NAME, null, contentValues);
        dbContext.close();
        mListener.onFragmentInteraction(result);
    }
}
