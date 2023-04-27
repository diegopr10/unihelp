package com.example.unihelp;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteCreator extends AppCompatActivity {

    private Note new_note;
    private BaseDeDatos db;
    String note_title_text;
    String note_subject_text;
    private int note_number_int;
    private int note_percentage_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creator);

        EditText note_title = findViewById(R.id.note_title);
        note_title_text = note_title.getText().toString();

        EditText note_subject = findViewById(R.id.note_subject);
        note_subject_text = note_subject.getText().toString();

        EditText note_number = findViewById(R.id.note_number);
        if (!String.valueOf(note_number.getText()).equals("") ) {
            note_number_int = Integer.parseInt(String.valueOf(note_number.getText()));
        }

        EditText note_percentage = findViewById(R.id.note_percentage);
        if (!String.valueOf(note_percentage.getText()).equals("") ) {
            note_percentage_int = Integer.parseInt(String.valueOf(note_percentage.getText()));
        }
    }

    public void goToMarks(View view) {

        db = BaseDeDatos.getInstance(getApplicationContext());
        new_note = new Note(note_title_text,note_subject_text,note_number_int,note_percentage_int);
        db.noteDao().insertNote(new_note);

        Toast.makeText(this,"Se han guardado los cambios",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Marks.class);
        startActivity(intent);
    }
}