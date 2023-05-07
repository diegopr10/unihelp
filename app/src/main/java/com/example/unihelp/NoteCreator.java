package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NoteCreator extends AppCompatActivity {
    private EditText note_title;
    private AutoCompleteTextView note_subject;
    private EditText note_number;
    private EditText note_percentage;
    private TextView pageTitle;
    private Button delete_note;
    private boolean isEditMode = false;
    long last_id;
    String last_title;
    String last_subject;
    int last_mark;
    int last_percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creator);
        pageTitle = findViewById(R.id.note_creator_title);
        delete_note = this.<Button>findViewById(R.id.delete_note_button);

        //Obtener valores del cuando tocamos la nota
        last_id = getIntent().getLongExtra("id",0);
        last_title = getIntent().getStringExtra("title");
        last_subject = getIntent().getStringExtra("subject");
        last_mark = getIntent().getIntExtra("mark",0);
        last_percentage = getIntent().getIntExtra("percentage",0);

        if(last_id != 0){
            Log.d("id",String.valueOf(last_id));
            Log.d("title",last_title);
            Log.d("subject",last_subject);
            Log.d("mark",String.valueOf(last_mark));
            Log.d("percentage",String.valueOf(last_percentage));
            isEditMode = true;
        }
        else{
            Log.d("add","addmode");
        }

        if(isEditMode){
            pageTitle.setText("EDITAR CALIFICACIÓN");
            delete_note.setVisibility(View.VISIBLE);
        }
        else pageTitle.setText("NUEVA CALIFICACIÓN");

        //Mostrar las asignaturas según tu carrera, curso y cuatrimestre

        SharedPreferences preferencias = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        long curso = Long.parseLong(preferencias.getString("curso",""));
        long cuatrimestre = Long.parseLong(preferencias.getString("cuatrimestre",""));
        String titulacion = preferencias.getString("carrera","");
        BaseDeDatos db = BaseDeDatos.getInstance(getApplicationContext());
        List<Asignatura> asignaturaList = db.asignaturaDao().selectByCursoCuatriAndTitulacion(curso,cuatrimestre,titulacion,"comun");

        String[] subjects_array = new String[asignaturaList.size()];
        Log.d("# de asignaturas",String.valueOf(asignaturaList.size()));
        Log.d("curso",String.valueOf(curso));
        Log.d("cuatrimestre",String.valueOf(cuatrimestre));
        Log.d("carrera",titulacion);

        for(int i = 0; i < asignaturaList.size(); i++){
            subjects_array[i] = asignaturaList.get(i).nombre;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.drop_down_item,subjects_array);
        note_subject = findViewById(R.id.auto_complete);
        note_subject.setAdapter(adapter);

        note_title = findViewById(R.id.note_title);
        note_number = findViewById(R.id.note_number);
        note_percentage = findViewById(R.id.note_percentage);

        //Hacer que se vea lo que estaba escrito en la nota al intentar editarla
        note_title.setText(last_title);
        note_subject.setText(last_subject);
        note_number.setText(String.valueOf(last_mark));
        note_percentage.setText(String.valueOf(last_percentage));
    }
    public void delete_note(View view){

        BaseDeDatos db = BaseDeDatos.getInstance(getApplicationContext());

        db.noteDao().deleteNote(last_id);
        Toast.makeText(this,"Nota eliminada",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Marks.class);
        startActivity(intent);
        finish();
    }
    public void goToMarks(View view) {

        String note_title_text = String.valueOf(note_title.getText());
        String note_subject_text = String.valueOf(note_subject.getText());
        int note_number_int = Integer.parseInt(String.valueOf(note_number.getText()));
        int note_percentage_int = Integer.parseInt(String.valueOf(note_percentage.getText()));

        BaseDeDatos db = BaseDeDatos.getInstance(getApplicationContext());

        if(!isEditMode) {
            Note new_note = new Note(note_title_text, note_subject_text, note_number_int, note_percentage_int);
            db.noteDao().insertNote(new_note);
            Toast.makeText(this,"Nueva nota añadida",Toast.LENGTH_LONG).show();
        }
        else{

            db.noteDao().updateNote(note_title_text,note_subject_text,note_number_int,note_percentage_int,last_id);
            Toast.makeText(this,"Se han guardado los cambios",Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(this, Marks.class);
        startActivity(intent);
        finish();
    }
}