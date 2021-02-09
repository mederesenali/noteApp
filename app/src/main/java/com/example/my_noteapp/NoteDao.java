package com.example.my_noteapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getAll();

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Delete
    void delete(Note note);

}
