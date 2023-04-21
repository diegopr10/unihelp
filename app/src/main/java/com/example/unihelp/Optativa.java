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

    public Optativa(long id, String name) {
        this.id = id;
        this.nombre = name;
    }

    @Ignore
    public Optativa(String name) {
        this.nombre = name;
    }
}
