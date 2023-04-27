package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteCreator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creator);

        EditText note_title = findViewById(R.id.note_title);
        EditText note_subject = findViewById(R.id.note_subject);
        EditText note_number = findViewById(R.id.note_number);
        EditText note_percentage = findViewById(R.id.note_percentage);
        Button add_note_button = findViewById(R.id.add_note_button);
    }

    public void goToMarks(View view) {
        Intent intent = new Intent(this, Marks.class);
        startActivity(intent);
    }


}