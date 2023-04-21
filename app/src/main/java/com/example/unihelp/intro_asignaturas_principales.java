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

        spinnerMejorAmbito = (Spinner) findViewById(R.id.spinner_mejorAmbito);
        spinnerPeorAmbito = (Spinner) findViewById(R.id.spinner_PeorAmbito);
        myPrefs = getSharedPreferences("MyPrefs",MODE_PRIVATE);

        String [] stringDeAmbitos = {"Programacion","Sistemas","Circuitos","Redes"};

        //Aunque sean dos arrays iguales, he puesto dos porque en principio no sé si al hacer un arrayadapter coge una referencia o el valor como tal
        String [] stringDeAmbitos2 = {"Programacion","Sistemas","Circuitos","Redes"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item_style, stringDeAmbitos);
        spinnerMejorAmbito.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item_style, stringDeAmbitos);
        spinnerPeorAmbito.setAdapter(adapter2);


    }

    public void siguiente2(View view) {

        String[] ambitoBuenoMalo = {"ambitoBueno","ambitoMalo"};
        String seleccion1 = spinnerMejorAmbito.getSelectedItem().toString();
        String seleccion2 = spinnerPeorAmbito.getSelectedItem().toString();

        SharedPreferences.Editor obj_editor = myPrefs.edit();

        obj_editor.putString(ambitoBuenoMalo[0],seleccion1);
        obj_editor.apply();

        obj_editor.putString(ambitoBuenoMalo[1],seleccion2);
        obj_editor.apply();

        Intent intent = new Intent(this,inicio.class);
        startActivity(intent);


        finish();


    }
}