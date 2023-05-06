package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class intro_asignaturas_principales extends AppCompatActivity {

    private SharedPreferences preferencias;

    private Spinner spinnerAsig1,spinnerAsig2,spinnerAsig3,spinnerAsig4,spinnerAsig5,spinnerAsig6,spinnerAsig7;

    private TextView textAsig1,textAsig2,textAsig3,textAsig4,textAsig5,textAsig6,textAsig7;


    private BaseDeDatos db;

    private long numero_curso;

    private long numero_cuatrimestre;

    private List<Asignatura> asignaturaList;

    private String titulacion;

    private String arrayDeDificultades[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_asignaturas_principales);

        preferencias = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        numero_curso = preferencias.getLong("curso",0);
        numero_cuatrimestre = preferencias.getLong("cuatrimestre",0);
        titulacion = preferencias.getString("carrera","");




        db=BaseDeDatos.getInstance(getApplicationContext());

        //aqui lo que hago es usar una "función" de sql que he definidido en el dao de la asignatura
        //la cual nos devuelve las asignaturas que coincidan con el curso y el cuatrimestre introducido en los spinners
        //de la pantalla de intro de datos
        asignaturaList = db.asignaturaDao().selectByCursoCuatriAndTitulacion(numero_curso,numero_cuatrimestre,titulacion,"comun");
        arrayDeDificultades = new String[asignaturaList.size()];

        textAsig1=(TextView) findViewById(R.id.asignatura1);
        textAsig2=(TextView) findViewById(R.id.asignatura2);
        textAsig3=(TextView) findViewById(R.id.asignatura3);
        textAsig4=(TextView) findViewById(R.id.asignatura4);
        textAsig5=(TextView) findViewById(R.id.asignatura5);
        textAsig6=(TextView) findViewById(R.id.asignatura6);
        textAsig7=(TextView) findViewById(R.id.asignatura7);

        spinnerAsig1=(Spinner) findViewById(R.id.spinner_asignatura1);
        spinnerAsig2=(Spinner) findViewById(R.id.spinner_asignatura2);
        spinnerAsig3=(Spinner) findViewById(R.id.spinner_asignatura3);
        spinnerAsig4=(Spinner) findViewById(R.id.spinner_asignatura4);
        spinnerAsig5=(Spinner) findViewById(R.id.spinner_asignatura5);
        spinnerAsig6=(Spinner) findViewById(R.id.spinner_asignatura6);
        spinnerAsig7=(Spinner) findViewById(R.id.spinner_asignatura7);

        //aqui sencillamente defino el adaptador para todos los spinners de dificultad de cada asigantura
        String [] opciones_de_dificultad = {"","Bien","Regular","Mal"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item_style, opciones_de_dificultad);
        spinnerAsig1.setAdapter(adapter1);
        spinnerAsig2.setAdapter(adapter1);
        spinnerAsig3.setAdapter(adapter1);
        spinnerAsig4.setAdapter(adapter1);
        spinnerAsig5.setAdapter(adapter1);
        spinnerAsig6.setAdapter(adapter1);
        spinnerAsig7.setAdapter(adapter1);
/*

//Estas lineas las podeis utilizar para dejar preseleccioado el primer item del spinner para hacer pruebas más rapido
        spinnerAsig1.setSelection(1);
        spinnerAsig2.setSelection(1);
        spinnerAsig3.setSelection(1);
        spinnerAsig4.setSelection(1);
        spinnerAsig5.setSelection(1);
        spinnerAsig6.setSelection(1);
        spinnerAsig7.setSelection(1);

*/


        //En estos if, else if y else, lo que hago es comprobar el tamaño de la lista que obtengo de la base de datos, debido a que
        //en los cursos de nuestra carrera (suponiendo que tenemos solamente asignaturas del mismo curso) podemos tener entre 5 y 7 asignaturas
        //entonces en los casos en los que haya menos de 7 asignaturas, oculto el sexto y/o séptimo spinner

        if(asignaturaList.size()==4){



            textAsig5.setVisibility(View.GONE);
            spinnerAsig5.setVisibility(View.GONE);
            textAsig6.setVisibility(View.GONE);
            spinnerAsig6.setVisibility(View.GONE);
            textAsig7.setVisibility(View.GONE);
            spinnerAsig7.setVisibility(View.GONE);

            textAsig1.setText(asignaturaList.get(0).nombre);
            textAsig2.setText(asignaturaList.get(1).nombre);
            textAsig3.setText(asignaturaList.get(2).nombre);
            textAsig4.setText(asignaturaList.get(3).nombre);
        }
        if(asignaturaList.size()==5){


            textAsig6.setVisibility(View.GONE);
            textAsig7.setVisibility(View.GONE);
            spinnerAsig6.setVisibility(View.GONE);
            spinnerAsig7.setVisibility(View.GONE);

            textAsig1.setText(asignaturaList.get(0).nombre);
            textAsig2.setText(asignaturaList.get(1).nombre);
            textAsig3.setText(asignaturaList.get(2).nombre);
            textAsig4.setText(asignaturaList.get(3).nombre);
            textAsig5.setText(asignaturaList.get(4).nombre);
        }
        if(asignaturaList.size()==6){

            textAsig7.setVisibility(View.GONE);
            spinnerAsig7.setVisibility(View.GONE);

            textAsig1.setText(asignaturaList.get(0).nombre);
            textAsig2.setText(asignaturaList.get(1).nombre);
            textAsig3.setText(asignaturaList.get(2).nombre);
            textAsig4.setText(asignaturaList.get(3).nombre);
            textAsig5.setText(asignaturaList.get(4).nombre);
            textAsig6.setText(asignaturaList.get(5).nombre);

        }

        if(asignaturaList.size()==7){
            textAsig1.setText(asignaturaList.get(0).nombre);
            textAsig2.setText(asignaturaList.get(1).nombre);
            textAsig3.setText(asignaturaList.get(2).nombre);
            textAsig4.setText(asignaturaList.get(3).nombre);
            textAsig5.setText(asignaturaList.get(4).nombre);
            textAsig6.setText(asignaturaList.get(5).nombre);
            textAsig7.setText(asignaturaList.get(6).nombre);
        }






    }

    public void siguiente2(View view) {

        boolean todosCamposSeleccionados = true;

        arrayDeDificultades[0] = spinnerAsig1.getSelectedItem().toString();
        arrayDeDificultades[1] = spinnerAsig2.getSelectedItem().toString();
        arrayDeDificultades[2] = spinnerAsig3.getSelectedItem().toString();
        arrayDeDificultades[3] = spinnerAsig4.getSelectedItem().toString();


        if(arrayDeDificultades[0].equals("")||arrayDeDificultades[1].equals("")
                || arrayDeDificultades[2].equals("") || arrayDeDificultades[3].equals("")){
            todosCamposSeleccionados = false;
        }

        if(asignaturaList.size()==5){
            arrayDeDificultades[4] = spinnerAsig5.getSelectedItem().toString();
            if(arrayDeDificultades[4].equals(""))
                todosCamposSeleccionados = false;
        }

        if(asignaturaList.size()==6){
            arrayDeDificultades[4] = spinnerAsig5.getSelectedItem().toString();
            arrayDeDificultades[5] = spinnerAsig6.getSelectedItem().toString();
            if(arrayDeDificultades[5].equals(""))
                todosCamposSeleccionados = false;
        }

        if(asignaturaList.size()==7){
            arrayDeDificultades[4] = spinnerAsig5.getSelectedItem().toString();
            arrayDeDificultades[5] = spinnerAsig6.getSelectedItem().toString();
            arrayDeDificultades[6] = spinnerAsig7.getSelectedItem().toString();

            if(arrayDeDificultades[4].equals("")||arrayDeDificultades[5].equals("")||arrayDeDificultades[6].equals(""))
                todosCamposSeleccionados = false;
        }


        if(todosCamposSeleccionados){

            for(int i = 0;i<asignaturaList.size();i++){
                long id = asignaturaList.get(i).id;
                //Log.d("MiAplicacion", "Mensaje de depuracion" + i+  " " + arrayDeDificultades[i]);

                db.asignaturaDao().actualizarDificultad(id,arrayDeDificultades[i]);
            }

            Intent intent = new Intent(this,inicio.class);
            startActivity(intent);


            finish();

        }

        else{
            Toast.makeText(getApplicationContext(), "Debes completar todos los campos.", Toast.LENGTH_SHORT).show();
        }



    }
}