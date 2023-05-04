package com.example.unihelp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;


public class Calendario extends AppCompatActivity {

    private TextView tv_Calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        tv_Calendar= findViewById(R.id.tv_Calendar);


    }

    public void abrirCalendario(View view){

        Calendar cal = Calendar.getInstance();
        int year= cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog= new DatePickerDialog(Calendario.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                String fecha = dia + "/"+ mes+"/"+anio;
                tv_Calendar.setText(fecha);
            }
        },day,month,year);

        dialog.show();

    }
}
