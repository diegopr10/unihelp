package com.example.unihelp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note")
    List<Note> selectAll();

    @Insert
    public void insertNote(Note note);

    @Update
    int update(Note note);

    @Delete
    public void deleteNote(Note note);

}
