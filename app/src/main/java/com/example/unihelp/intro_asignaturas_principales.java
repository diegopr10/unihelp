package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class intro_asignaturas_principales extends AppCompatActivity {

    private SharedPreferences myPrefs;
    private Spinner spinnerMejorAmbito,spinnerPeorAmbito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_asignaturas_principales);




    }

    public void siguiente2(View view) {



        Intent intent = new Intent(this,inicio.class);
        startActivity(intent);


        finish();


    }
}