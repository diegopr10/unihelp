package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceScreen;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private SharedPreferences myPrefs;//Aunque se haya creado en otra actividad podemos acceder utilizando el mismo nombre luego en el getSharedPreferences
    private String alias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.preference_screen_container, new PreferenceFragment())
                .commit();

        myPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        myPrefs.registerOnSharedPreferenceChangeListener(this);
    }

    public void goToHome(View view){
        Log.d("Sucess","The button works");
        Intent intent = new Intent(this,inicio.class);
        startActivity(intent);

        EditText editText = findViewById(R.id.changeName);
        String new_alias = editText.getText().toString();
        if(!new_alias.equals(alias)){//Si el alias cambia
            myPrefs.edit().putString("alias", new_alias).apply();
        }
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("alias")){
            Toast.makeText(this.getApplicationContext(), "Tu alias ha cambiado", Toast.LENGTH_LONG).show();
        }
    }
}