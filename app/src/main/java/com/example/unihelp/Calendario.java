package com.example.unihelp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

//   Funcion para abrir un calendario como dialog en otra pestaña.

//    public void abrirCalendario(View view){
//
//
////        Calendar cal = Calendar.getInstance();
////        int year= cal.get(Calendar.YEAR);
////        int month = cal.get(Calendar.MONTH);
////        int day = cal.get(Calendar.DAY_OF_MONTH);
////
////        DatePickerDialog dialog= new DatePickerDialog(Calendario.this, new DatePickerDialog.OnDateSetListener() {
////
////            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
////                String fecha = dia + "/"+ mes+"/"+anio;
////                tv_Calendar.setText(fecha);
////            }
////        },day,month,year);
////
////        dialog.show();
//
//    }
}
