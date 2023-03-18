package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");
        Log.d("Sucess","App is running");
        getSupportActionBar().hide();
        mySharedPreferences = getSharedPreferences("com.example.unihelp.MY_PREFS", MODE_PRIVATE);

        String checkUserStatus = mySharedPreferences.getString("accessToApp", "first");

        if(checkUserStatus.equals("first")){
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("accessToApp", "not_first");
            editor.apply();
            Log.d("Change","now is not_first");
        }
    }
    public void goToSettings(View view){
        Log.d("Sucess","The button works");
        Intent intent = new Intent(this,Settings.class);
        startActivity(intent);
    }
}