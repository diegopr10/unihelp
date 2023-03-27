package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

    public void go_to_modo_estudio(View view){
        Intent nuevoIntent = new Intent(this,ModoEstudio.class);
        startActivity(nuevoIntent);
    }

    public void go_to_settings(View view){
        Intent nuevoIntent = new Intent(this,Settings.class);
        startActivity(nuevoIntent);
    }

}