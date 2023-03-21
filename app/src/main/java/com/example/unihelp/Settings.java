package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        Spinner spinner_prueba = findViewById(R.id.spinner_prueba);
        spinner_prueba.setAdapter(adapter);
    }

    public void goToHome(){
        Log.d("Sucess","The button works");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}