package com.example.unihelp;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface AsignaturaDao {
    @Query("SELECT * FROM Asignatura")
    List<Asignatura> selectAll();

    @Query("SELECT * FROM Asignatura WHERE id=:id")
    Asignatura selectById(long id);

    @Query("SELECT * FROM Asignatura WHERE nombre LIKE :nombre LIMIT 1")
    Asignatura selectByTitle(String nombre);

    @Insert
    long insert(Asignatura notes);

    @Update
    int update(Asignatura notes);

    @Delete
    int delete(Asignatura notes);
}
