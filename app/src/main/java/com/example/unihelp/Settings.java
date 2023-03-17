package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
    }

    public void goToHome(){
        Log.d("Sucess","The button works");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}