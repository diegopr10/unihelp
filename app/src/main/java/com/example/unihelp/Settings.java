package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import androidx.preference.PreferenceManager;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private SharedPreferences myPrefs;//Aunque se haya creado en otra actividad podemos acceder utilizando el mismo nombre luego en el getSharedPreferences
    private String alias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        // Create a new instance of the PreferenceFragment
        PreferenceFragment fragment = new PreferenceFragment();

        // Add the fragment to the activity's LinearLayout container
        getSupportFragmentManager().beginTransaction()
                .add(R.id.preference_screen_container, fragment)
                .commit();
    }

    public void goToHome(View view) {
        Log.d("Sucess", "The button works");

        myPrefs = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        String new_name = myPrefs.getString("alias", "jaja");
        Toast.makeText(this,"Ahora tu nombre es: " + new_name,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, inicio.class);
        startActivity(intent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
}