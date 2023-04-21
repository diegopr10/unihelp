package com.example.unihelp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AsignaturaDao {
    @Query("SELECT * FROM Asignatura")
    List<Asignatura> selectAll();

    @Query("SELECT * FROM Asignatura WHERE id=:id")
    Asignatura selectById(long id);

    @Query("SELECT * FROM Asignatura WHERE nombre LIKE :nombre LIMIT 1")
    Asignatura selectByNombre(String nombre);

    @Insert
    long insert(Asignatura asignatura);

    @Update
    int update(Asignatura asignatura);

    @Delete
    int delete(Asignatura asignatura);
}
