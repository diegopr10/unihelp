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

    @Query("UPDATE Note SET Nombre = :title, Asignatura = :subject, Nota = :mark, Porcentaje = :percentage WHERE id = :id")
    public void updateNote(String title,String subject,int mark,int percentage,long id);

    @Query("DELETE FROM Note WHERE id = :id")
    public void deleteNote(long id);
}
