package com.example.notesapp.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Notes {
    public Notes() {
    }

    @PrimaryKey(autoGenerate = true)
    int id = 0;
    @ColumnInfo(name = "title")
    String title = "";
    @ColumnInfo(name = "content")
    String Content = "";
    @ColumnInfo(name = "date")
    String Date = "";

    public Notes(String title, String content, String date) {
        this.title = title;
        Content = content;
        Date = date;
    }

    public Notes(String title, String content) {
        this.title = title;
        Content = content;
    }

    public Notes(int id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        Content = content;
        Date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
