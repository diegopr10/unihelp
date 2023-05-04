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
    private EditText note_title;
    private EditText note_subject;
    private EditText note_number;
    private EditText note_percentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creator);

        note_title = findViewById(R.id.note_title);
        note_subject = findViewById(R.id.note_subject);
        note_number = findViewById(R.id.note_number);
        note_percentage = findViewById(R.id.note_percentage);
    }
    public void goToMarks(View view) {

        String note_title_text = String.valueOf(note_title.getText());
        String note_subject_text = String.valueOf(note_subject.getText());
        int note_number_int = Integer.parseInt(String.valueOf(note_number.getText()));
        int note_percentage_int = Integer.parseInt(String.valueOf(note_percentage.getText()));

        BaseDeDatos db = BaseDeDatos.getInstance(getApplicationContext());
        Note new_note = new Note(note_title_text,note_subject_text,note_number_int,note_percentage_int);
        db.noteDao().insertNote(new_note);

        Toast.makeText(this,note_title_text,Toast.LENGTH_LONG).show();
        //Toast.makeText(this,"Se han guardado los cambios",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Marks.class);
        startActivity(intent);
    }
}