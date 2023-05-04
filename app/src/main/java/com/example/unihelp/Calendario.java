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

import java.text.DateFormat;
import java.util.Calendar;


public class Calendario extends AppCompatActivity {

    private TextView tv_Calendar;
    private CalendarView cal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        tv_Calendar= findViewById(R.id.tv_Calendar);
        cal = findViewById(R.id.calendarView);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String fecha = day + "/"+ (month+1)+"/"+year;
                Toast.makeText(Calendario.this,fecha, Toast.LENGTH_LONG).show();
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
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(NotasContract.NotasEntry.COLUMN_NAME_FECHA, fecha);
//        values.put(NotasContract.NotasEntry.COLUMN_NAME_TEXTO, nota);
//
//        db.insert(NotasContract.NotasEntry.TABLE_NAME, null, values);
//
    }
    private String obtenerNotas(String fecha) {

        return fecha;
    }



}
