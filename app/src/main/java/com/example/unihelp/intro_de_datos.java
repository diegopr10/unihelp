package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class intro_de_datos extends AppCompatActivity {

    private SharedPreferences myPrefs;
    private Spinner spinner1,spinner2,spinner3;
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_de_datos);
        myPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        spinner1 = (Spinner) findViewById(R.id.spinner_carreras);
        spinner2 = (Spinner) findViewById(R.id.spinner_curso);
        spinner3 = (Spinner) findViewById(R.id.spinner_idioma);
        et1 = (EditText)findViewById(R.id.campo_nombre);

        String [] opciones_de_carrera = {"","Sonido e Imagen","Teleco","Telemática","Espaciales"};
        String [] opciones_de_curso = {"","Primero","Segundo","Tercero","Cuarto"};
        String [] opciones_de_cuatri = {"","Primero","Segundo"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item_style, opciones_de_carrera);
        spinner1.setAdapter(adapter1);



        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item_style, opciones_de_curso);
        spinner2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item_style, opciones_de_cuatri);
        spinner3.setAdapter(adapter3);

    }



    public void siguiente(View view) {
        String[] cadena = {"alias", "carrera", "curso", "cuatrimestre"};
        //esto es un booleano que nos va a servir para comprobar si en el curso y cuatrimstre seleccionados hay optativas o no
        //Entonces, si hay optativas nos llevará primero a la pantalla para seleccionar l
        boolean hayOptativas = false;
        BaseDeDatos db;
        List<Asignatura> asignaturaList;
        db=BaseDeDatos.getInstance(getApplicationContext());

        int nivel = 1;
        int experiencia = 0;
        long seleccion_cautri_int;
        long seleccion_curso_int;


        String valor1_string = et1.getText().toString();
        String seleccion_carrera = spinner1.getSelectedItem().toString();
        String seleccion_curso = spinner2.getSelectedItem().toString();
        String seleccion_cautri = spinner3.getSelectedItem().toString();


        if (valor1_string.equals("") || seleccion_carrera.equals("") || seleccion_curso.equals("") || seleccion_cautri.equals("")) {
            Toast.makeText(getApplicationContext(), "Debes completar todas las opciones.", Toast.LENGTH_SHORT).show();

        }
        else {


        /*
        Los siguientes else if los he puesto porque en la base de datos la columna de cuatrimestre y curso, los he definido
        como integers, entonces como en los spinners tenemos puesto que salga "primero,segundo,tercero" etc...
        para luego poder relacioar las preferencias con la base de datos, creo variables de tipo int para
        definir el curso y el cuatrimestre.
        El motivo por el que pongo Long y no int, es porque me estaba dando problemas la base de datos al usar int, y lo he dejado
        puesto con long como tiene también boni en sus ejemplos de aplicaciones con bases de datos
        */

            if (seleccion_cautri.equals("Primero")) {
                seleccion_cautri_int = 1;
            } else {
                seleccion_cautri_int = 2;
            }


            if (seleccion_curso.equals("Primero")) {
                seleccion_curso_int = 1;
            } else if (seleccion_curso.equals("Segundo")) {
                seleccion_curso_int = 2;
            } else if (seleccion_curso.equals("Tercero")) {
                seleccion_curso_int = 3;
            } else {
                seleccion_curso_int = 4;
            }


            SharedPreferences.Editor obj_editor = myPrefs.edit();
            obj_editor.putString(cadena[0], valor1_string);
            obj_editor.apply();

            obj_editor.putString(cadena[1], seleccion_carrera);
            obj_editor.apply();

            obj_editor.putLong(cadena[2], seleccion_curso_int);
            obj_editor.apply();

            obj_editor.putLong(cadena[3], seleccion_cautri_int);
            obj_editor.apply();

            obj_editor.putInt("nivel", nivel);
            obj_editor.apply();

            obj_editor.putInt("experiencia", experiencia);
            obj_editor.apply();

            //Pone isFirstTime a false para que no vuelva a aparecer en el cuestionario al iniciar la app.
            //Luego le lleva a la pantalla de inicio mediante un intent.
            myPrefs.edit().putBoolean("isFirstTime", false).apply();

            asignaturaList = db.asignaturaDao().selectByCursoCuatriAndTitulacion(seleccion_curso_int,seleccion_cautri_int,seleccion_carrera,"comun");

            for(int i=0;i<asignaturaList.size();i++){
                if(asignaturaList.get(i).nombre.startsWith("Optativa")){
                    hayOptativas = true;
                    break;
                }
            }


            if(hayOptativas){
                Intent intent = new Intent(this, intro_optativas.class);
                startActivity(intent);
                finish();
            }


            else{
                Intent intent = new Intent(this, intro_asignaturas_principales.class);
                startActivity(intent);
                finish();
            }





        }

    }
}