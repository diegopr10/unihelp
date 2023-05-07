package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class Marks extends AppCompatActivity {

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        BaseDeDatos db = BaseDeDatos.getInstance(getApplicationContext());
        List<Note> notes_list = db.noteDao().selectAll();
        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        NoteViewAdapter note_view_adapter = new NoteViewAdapter(getApplicationContext(),notes_list);
        recycler_view.setAdapter(note_view_adapter);
        note_view_adapter.notifyDataSetChanged();
    }
    public void goToNoteCreator(View view){
        Intent intent = new Intent(this, NoteCreator.class);
        startActivity(intent);
        finish();
    }
    public void goToMedias(View view){
        Intent intent = new Intent(this, Medias.class);
        startActivity(intent);
        finish();
    }
}