package com.example.unihelp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OptativaDao {
    @Query("SELECT * FROM Optativa")
    List<Optativa> selectAll();

    @Query("SELECT * FROM Optativa WHERE id=:id")
    Optativa selectById(long id);

    @Query("SELECT * FROM Optativa WHERE nombre LIKE :nombre LIMIT 1")
    Optativa selectByNombre(String nombre);

    @Insert
    long insert(Optativa optativa);

    @Update
    int update(Optativa optativa);

    @Delete
    int delete(Optativa optativa);
}
