package com.example.unihelp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Optativa {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "creditos")
    public int creditos;



    public Optativa(long id, String nombre,int creditos) {
        this.id = id;
        this.nombre = nombre;
        this.creditos=creditos;

    }

    @Ignore
    public Optativa(String nombre) {
        this.nombre = nombre;

    }


}
