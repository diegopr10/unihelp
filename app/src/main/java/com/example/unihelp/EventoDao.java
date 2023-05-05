package com.example.unihelp;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface EventoDao {

    @Query("SELECT * FROM Evento WHERE fecha=:fecha")
    List<Evento> selectByFecha(String fecha);

    @Insert
    public void insert(Evento evento);

    @Delete
    public void delete(Evento evento);


}
