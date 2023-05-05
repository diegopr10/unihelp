package com.example.unihelp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Asignatura.class,Optativa.class,Note.class,Evento.class}, version = 1)
public abstract class BaseDeDatos extends RoomDatabase {
    private static final String PRELOADED_DATABASE_FILE = "basededatos-precargada.db";
    private static final String DB_NAME = "my-database.db";
    private static volatile BaseDeDatos instance;

    public abstract AsignaturaDao asignaturaDao();
    public abstract OptativaDao optativaDao();
    public abstract NoteDao noteDao();//Añadido por Diego para incluir calificaciones
    public abstract EventoDao eventoDao();//Añadido para incluir eventos en el calendario

    public static synchronized BaseDeDatos getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static BaseDeDatos create(Context context) {
        return Room.databaseBuilder(context, BaseDeDatos.class, DB_NAME)
                .createFromAsset(PRELOADED_DATABASE_FILE)
                .allowMainThreadQueries()
                .build();
    }
}
