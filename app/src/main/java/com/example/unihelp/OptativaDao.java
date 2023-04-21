package com.example.unihelp;

import androidx.room.Query;

import java.util.List;

public interface OptativaDao {
    @Query("SELECT * FROM Optativa")
    List<Optativa> getAllOptativas();
}
