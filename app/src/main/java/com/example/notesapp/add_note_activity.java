package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.databsaes.notesDB;
import com.example.notesapp.models.Notes;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class add_note_activity extends AppCompatActivity {

    EditText add_Title,add_content;
    CardView actionBtnSave;
    notesDB notesdb;
    MainActivity context;
    int updateID;
    String updateTitle = "";
    String updateContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_note);
        actionBtnSave = findViewById(R.id.actionBtnSave);
        add_Title = findViewById(R.id.add_note_title);
        add_content = findViewById(R.id.add_note_content);

        notesdb = notesDB.getInstance(context);
        Intent mainI = getIntent();
        int value = mainI.getIntExtra("value",0);
        if(value != 101){
            Log.d("MYERROR", "in ADD NOTE Activity check if value != 101 set all view");
            Bundle bundle = getIntent().getExtras();

            updateTitle = (String) bundle.get("arrTitle");
            updateContent = bundle.getString("arrContent");
            updateID = bundle.getInt("arrId");

            if(!updateContent.equals("")){
                Log.d("MYERROR","in ADD NOTE Activity in if oldcontent are not null "+updateID);
                add_Title.setText(updateTitle);
                add_content.setText(updateContent);
            }
            else{
                Log.d("MYERROR","in ADD NOTE Activity in if oldcontent are null ");
            }
        }
        Log.d("MYERROR", "in ADD NOTE Activity ");
        actionBtnSave.setOnClickListener(V -> {
            Log.d("MYERROR", "in ADD NOTE Activity actionBtnSave onClick addNoteBtn");
            if(value != 101){
                Log.d("MYERROR", "in ADD NOTE Activity actionBtnSave if value != 101 calling update method");
                    udDate();
            }else{
                addNotes();
            }
        });
    }

    public void addNotes(){
        Log.d("MYERROR","in ADD NOTE Activity in addNotes ");
        String title = add_Title.getText().toString();
        String content = add_content.getText().toString();
//        String date = date();
        if(!content.equals("")){
            Log.d("MYERROR","in ADD NOTE in if");
            notesdb.notedao().addNote(new Notes(title,content,null));
            Intent i = new Intent(add_note_activity.this,MainActivity.class);
            startActivity(i);
            finish();
        }else{
            Log.d("MYERROR","in ADD NOTE in else");
            Toast.makeText(this, "Empty are not allowed", Toast.LENGTH_SHORT).show();
        }
    }
    public void udDate(){
        Log.d("MYERROR", "in ADD NOTE Activity in update method");
        if(!updateContent.equals("")) {
            updateTitle = add_Title.getText().toString();
            updateContent = add_content.getText().toString();
            Log.d("MYERROR", "in ADD NOTE Activity in update method not empty");
                Log.d("MYERROR","in ADD NOTE in if");
                notesdb.notedao().update(new Notes(updateID,updateTitle,updateContent,null));
                Intent i = new Intent(add_note_activity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
//        String date = date();
            else{
            Log.d("MYERROR","in ADD NOTE in else");
            Toast.makeText(this, "Empty are not allowed", Toast.LENGTH_SHORT).show();
        }
    }



//    public void updateNotes(String title,String content ,int id) {
//        Log.d("MYERROR","in update notes method title or content id ");
//
////        EditText updateTitle,updateContent;
////        CardView actionSaveBtn;
////        updateTitle = findViewById(R.id.add_note_title);
////        updateContent = findViewById(R.id.add_note_content);
////        actionSaveBtn = findViewById(R.id.actionBtnSave);
//
////        actionSaveBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Log.d("MYERROR","in notesAdapter class in updateNotes method in actionSaveBtn onClick");
////                String newTitle = updateTitle.getText().toString();
////                String newContent = updateContent.getText().toString();
////                String date = ((MainActivity)context).date();
////                Notes a = new Notes(id,newTitle,newContent,date);
////                if(!newContent.isEmpty()){
//                    Log.d("MYERROR","in notesAdapter class in updateNotes method in actionSaveBtn onClick in if");
////                    notesdb.notedao().update(a);
////                    Intent i = new Intent(add_note_activity.this,MainActivity.class);
////                    startActivity(i);
////                    finish();
////                }else {
////                    Log.d("MYERROR","in notesAdapter class in updateNotes method in actionSaveBtn onClick in else");
////                    Toast.makeText(context, "Empty are Not allow", Toast.LENGTH_SHORT).show();
////                }
//            }
//        });
//    }

}