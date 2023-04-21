package com.example.unihelp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Asignatura {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "curso")
    public String curso;

    @ColumnInfo(name = "dificultad")
    public String dificultad;

    public Asignatura(long id, String nombre, String curso, String dificultad) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.dificultad = dificultad;
    }

    @Ignore
    public Asignatura(String nombre, String curso, String dificultad) {
        this.nombre = nombre;
        this.curso = curso;
        this.dificultad = dificultad;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
