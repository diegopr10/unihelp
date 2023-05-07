package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class intro_optativas extends AppCompatActivity {
    private SharedPreferences preferencias;

    private Spinner spinnerAsig1,spinnerAsig2;

    private BaseDeDatos db;

    private List<Optativa> optativaList;

    private List<Asignatura> asignaturaList;

    private long numero_curso;

    private long numero_cuatrimestre;

    private String titulacion;

    private String arrayDeOptativas[];





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_optativas);

        preferencias = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        numero_curso = Long.parseLong(preferencias.getString("curso"," "));
        numero_cuatrimestre = Long.parseLong(preferencias.getString("cuatrimestre"," "));
        titulacion = preferencias.getString("carrera","");

        db=BaseDeDatos.getInstance(getApplicationContext());

        if(titulacion.equals("Teleco")&&numero_cuatrimestre==1){
            optativaList = db.optativaDao().selectByCreditos(6);
        }

        else{
            optativaList = db.optativaDao().selectByCreditos(3);
        }


        arrayDeOptativas = new String[optativaList.size()];

        for(int i=0;i< arrayDeOptativas.length;i++){
            arrayDeOptativas[i] = optativaList.get(i).nombre;
        }

        spinnerAsig1=(Spinner) findViewById(R.id.spinner_optativa1);
        spinnerAsig2=(Spinner) findViewById(R.id.spinner_optativa2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_style, arrayDeOptativas);
        spinnerAsig1.setAdapter(adapter);
        spinnerAsig2.setAdapter(adapter);
    }

    public void go_to_asig_principales(View view){

        String newName1 =spinnerAsig1.getSelectedItem().toString();
        String newName2 = spinnerAsig2.getSelectedItem().toString();

        if(newName1.equals(newName2)){
            Toast.makeText(getApplicationContext(), "Debes elegir dos asignaturas distintas", Toast.LENGTH_SHORT).show();
        }

        else{
            if (numero_curso == 3) {
                db.asignaturaDao().asignarOptativa("Optativa 1",newName1);
                db.asignaturaDao().asignarOptativa("Optativa 2",newName2);
            }

            else if(numero_curso == 4 && numero_cuatrimestre == 1){
                db.asignaturaDao().asignarOptativa("Optativa principal 1",newName1);
                db.asignaturaDao().asignarOptativa("Optativa principal 2",newName2);
            }

            else{
                db.asignaturaDao().asignarOptativa("Optativa 3",newName1);
                db.asignaturaDao().asignarOptativa("Optativa 4",newName2);
            }

            Intent intent = new Intent(this, intro_asignaturas_principales.class);


            startActivity(intent);
            finish();

        }



    }
}