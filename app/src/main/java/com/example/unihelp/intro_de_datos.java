package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class intro_de_datos extends AppCompatActivity {

    private SharedPreferences myPrefs;
    private Spinner spinner1,spinner2,spinner3;
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_de_datos);
        myPrefs = getSharedPreferences("isFirstTime", MODE_PRIVATE);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        et1 = (EditText)findViewById(R.id.campo_nombre);

        String [] opciones1 = {"Sonido e Imagen","Teleco","Telemática","Espaciales"};
        String [] opciones2 = {"Primero.","Segundo.","Tercero.","Cuarto"};
        String [] opciones3 = {"Español","Inglés"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones1);
        spinner1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones2);
        spinner2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones3);
        spinner3.setAdapter(adapter3);
    }

    public void siguiente(View view){
        String[] cadena = {"alias","carrera","curso","idioma"};

        String valor1_string = et1.getText().toString();
        String seleccion1 = spinner1.getSelectedItem().toString();
        String seleccion2 = spinner2.getSelectedItem().toString();
        String seleccion3 = spinner3.getSelectedItem().toString();

        SharedPreferences preferencias = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();
        obj_editor.putString(cadena[0],valor1_string);
        obj_editor.commit();

        obj_editor.putString(cadena[1],seleccion1);
        obj_editor.commit();

        obj_editor.putString(cadena[2],seleccion2);
        obj_editor.commit();

        obj_editor.putString(cadena[3],seleccion3);
        obj_editor.commit();

        //Pone isFirstTime a false para que no vuelva a aparecer en el cuestionario al iniciar la app.
        //Luego le lleva a la pantalla de inicio mediante un intent.
        myPrefs.edit().putBoolean("isFirstTime", false).apply();
        Intent intent = new Intent(this,inicio.class);
        startActivity(intent);
        finish();
    }
}