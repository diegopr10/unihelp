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



    public Optativa(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;

    }

    @Ignore
    public Optativa(String nombre) {
        this.nombre = nombre;

    }

    @Override
    public String toString() {
        return nombre;
    }
}
