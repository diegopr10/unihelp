package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class inicio extends AppCompatActivity {

    private TextView alias,nivel,experiencia;
    private ImageView fotoPerfil;
    static final int REQUEST_IMAGE_GET = 1;
    Uri fullPhotoUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        alias = (TextView) findViewById(R.id.alias);
        nivel = (TextView) findViewById(R.id.level_value);
        experiencia = (TextView) findViewById(R.id.exp_value);
        fotoPerfil = (ImageView)findViewById(R.id.fotoDePerfil);


        SharedPreferences preferencias = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String cadena_alias = preferencias.getString("alias","");
        alias.setText(cadena_alias);

        int int_nivel = preferencias.getInt("nivel",1);
        String cadena_nivel = String.valueOf(int_nivel);
        nivel.setText(cadena_nivel);

        int int_exp = preferencias.getInt("experiencia",0);
        String cadena_exp = String.valueOf(int_exp) + "/100";
        experiencia.setText(cadena_exp);

        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE_GET);

            }
        });



    }

    public void go_to_modo_estudio(View view){
        Intent nuevoIntent = new Intent(this,ModoEstudio.class);
        startActivity(nuevoIntent);
    }

    public void go_to_settings(View view){
        Intent nuevoIntent = new Intent(this,Settings.class);
        startActivity(nuevoIntent);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {

            fullPhotoUri = data.getData();
            fotoPerfil.setImageURI(fullPhotoUri);
        }
    }


}