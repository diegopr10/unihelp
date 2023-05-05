package com.example.unihelp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import java.text.DateFormat;
import java.util.Calendar;


public class Calendario extends AppCompatActivity {

    private TextView tv_Calendar;
    private CalendarView cal;

    private TextView tv_evento1;
    private TextView tv_evento2;
    private TextView tv_evento3;
    private TextView tv_evento4;
    private TextView tv_evento5;
    private TextView tv_evento6;

    private BaseDeDatos db;
    private List<Evento> eventoList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        tv_Calendar= findViewById(R.id.tv_Calendar);
        cal = findViewById(R.id.calendarView);
        tv_evento1= findViewById(R.id.tv_evento1);
        tv_evento2= findViewById(R.id.tv_evento2);
        tv_evento3= findViewById(R.id.tv_evento3);
        tv_evento4= findViewById(R.id.tv_evento4);
        tv_evento5= findViewById(R.id.tv_evento5);
        tv_evento6= findViewById(R.id.tv_evento6);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String fecha = day + "/"+ (month+1)+"/"+year;
                obtenerNotas(fecha);
                //Toast.makeText(Calendario.this,fecha, Toast.LENGTH_LONG).show();

            }
        });


    }
    public void agregarNota(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar nota");

        final EditText input = new EditText(this);
        input.setHint("Escribe aquí tu nota");
        builder.setView(input);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String fecha = tv_Calendar.getText().toString();
            String nota = input.getText().toString();
            guardarNota(fecha, nota);
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void guardarNota(String fecha, String nota) {

        db = BaseDeDatos.getInstance(getApplicationContext());
        Evento new_Evento = new Evento(nota,fecha);
        db.eventoDao().insert(new_Evento);
        Toast.makeText(this,"Nuevo evento añadido",Toast.LENGTH_LONG).show();

    }
    private void obtenerNotas(String fecha) {

        db = BaseDeDatos.getInstance(getApplicationContext());
        eventoList = (List<Evento>) db.eventoDao().selectByFecha(fecha);
        int legth = eventoList.size();

        for(int i = 0; i<legth; i++){
            if(i==0) tv_evento1.setText(eventoList.get(i).toString());
            if(i==1) tv_evento2.setText(eventoList.get(i).toString());
            if(i==2) tv_evento3.setText(eventoList.get(i).toString());
            if(i==3) tv_evento4.setText(eventoList.get(i).toString());
            if(i==4) tv_evento5.setText(eventoList.get(i).toString());
            if(i==5) tv_evento6.setText(eventoList.get(i).toString());
        }

    }



}
