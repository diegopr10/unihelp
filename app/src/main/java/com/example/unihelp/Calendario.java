package com.example.unihelp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import java.text.DateFormat;
import java.util.Calendar;


public class Calendario extends AppCompatActivity {

    private CalendarView cal;


    RecyclerView recycler;

    private BaseDeDatos db;
    private List<Evento> eventoList;

    private List<Asignatura> asignaturaList;

    private String selectedDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        cal = findViewById(R.id.calendarView);


        recycler = (RecyclerView) findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        EventViewAdapter adapter = new EventViewAdapter(this,eventoList);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                GregorianCalendar calendar = new GregorianCalendar(year, month, day);
                Date date = calendar.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = formatter.format(date);
                selectedDate = fecha;
                obtenerNotas(fecha);

            }
        });


    }
    public void agregarNota(View view) {
        asignaturaList=db.asignaturaDao().selectAsignaturasEnCurso();

        String[] subjects_array = new String[asignaturaList.size()];
        for(int i=0;i<subjects_array.length;i++){
            subjects_array[i] = asignaturaList.get(i).nombre;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.drop_down_item,subjects_array);
        Spinner spinner = new Spinner(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText input = new EditText(this);
        input.setHint("Escribe aquí tu nota");

        spinner.setAdapter(adapter);

        layout.addView(input);
        layout.addView(spinner);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar evento");
        builder.setView(layout);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String fecha = selectedDate;
            String nota = input.getText().toString();
            String asignatura = spinner.getSelectedItem().toString();
            guardarNota(fecha, nota,asignatura);
            obtenerNotas(fecha);
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void guardarNota(String fecha, String nota, String asignatura) {


        BaseDeDatos db = BaseDeDatos.getInstance(getApplicationContext());
        Evento new_Evento = new Evento(nota,fecha,asignatura);
        db.eventoDao().insert(new_Evento);
        Toast.makeText(this,"Nuevo evento añadido",Toast.LENGTH_LONG).show();

    }
    private void obtenerNotas(String fecha) {

        db = BaseDeDatos.getInstance(getApplicationContext());
        eventoList = (List<Evento>) db.eventoDao().selectByFecha(fecha);
        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        EventViewAdapter event_view_adapter = new EventViewAdapter(getApplicationContext(),eventoList);
        recycler_view.setAdapter(event_view_adapter);
        event_view_adapter.notifyDataSetChanged();

    }



}
