package com.example.notesapp.databsaes;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesapp.models.Notes;

import java.util.List;

@Dao
public interface notesDAO {

    @Query("SELECT * FROM note ORDER BY id DESC")
    List<Notes> getNote();

    @Insert
    void addNote(Notes notes);

    @Delete
    void delete(Notes notes);

    @Update()
    void update(Notes notes);


}
