package com.example.notesapp.databsaes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesapp.models.Notes;

@Database(entities = Notes.class, exportSchema = false,version = 1)
public abstract class notesDB extends RoomDatabase {

    public static final String DB_NAME = "notes_db";
    public static notesDB instance;
    public static synchronized notesDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), notesDB.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract notesDAO notedao();

}
