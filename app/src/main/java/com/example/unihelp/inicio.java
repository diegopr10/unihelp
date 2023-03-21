package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class inicio extends AppCompatActivity {

    private TextView alias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        alias = (TextView) findViewById(R.id.alias);

        SharedPreferences preferencias = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String cadena_alias = preferencias.getString("alias","");

        alias.setText(cadena_alias);
    }
}