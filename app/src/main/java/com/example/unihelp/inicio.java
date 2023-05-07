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
    private TextView alias;
    private ImageView fotoPerfil;
    static final int REQUEST_IMAGE_GET = 1;
    Uri fullPhotoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        alias = (TextView) findViewById(R.id.alias);
        fotoPerfil = (ImageView)findViewById(R.id.fotoDePerfil);

        SharedPreferences preferencias = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String cadena_alias = preferencias.getString("alias","");
        alias.setText(cadena_alias);

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
    public void go_to_marks(View view){
        Intent nuevoIntent = new Intent(this,Marks.class);
        startActivity(nuevoIntent);
    }
    public void go_to_calendario(View view){
        Intent nuevoIntent = new Intent(this,Calendario.class);
        startActivity(nuevoIntent);
    }

    //public void go_to_links(View view){
        //Intent nuevoIntent = new Intent(this,Links.class);
      //  startActivity(nuevoIntent);
    //}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {

            fullPhotoUri = data.getData();
            fotoPerfil.setImageURI(fullPhotoUri);
        }
    }
}