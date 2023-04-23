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
    public long curso;

    @ColumnInfo(name = "dificultad")
    public String dificultad;

    @ColumnInfo(name = "cuatrimestre")
    public long cuatrimestre;

    public Asignatura(long id, String nombre, long curso, String dificultad,long cuatrimestre) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.dificultad = dificultad;
        this.cuatrimestre=cuatrimestre;
    }

    @Ignore
    public Asignatura(String nombre, long curso, String dificultad,long cuatrimestre) {
        this.nombre = nombre;
        this.curso = curso;
        this.dificultad = dificultad;
        this.cuatrimestre=cuatrimestre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
