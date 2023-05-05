package com.example.unihelp;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Evento {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "descripcion")
    public String des;

    @ColumnInfo(name = "fecha")
    public String fecha;


    public Evento(long id, String descripcion, String fecha) {
        this.id = id;
        this.des = descripcion;
        this.fecha = fecha;
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
