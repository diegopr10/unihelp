package com.example.unihelp;


import android.widget.TextView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Evento {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "descripcion")
    public String des;

    @ColumnInfo(name = "asignatura")
    public String subject;

    @ColumnInfo(name = "fecha")
    public String fecha;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public Evento(String descripcion, String fecha,String asignatura) {
        this.des = descripcion;
        this.fecha = fecha;
        this.subject=asignatura;
    }
    public Evento(long id, String descripcion, String fecha, String asignatura) {
        this.id = id;
        this.des = descripcion;
        this.fecha = fecha;
        this.subject=asignatura;
    }

    public Evento( String descripcion, String fecha) {
        this.des = descripcion;
        this.fecha = fecha;
    }
    public Evento( ) {

    }

    @Override
    public String toString() {
        return des;
    }

}
