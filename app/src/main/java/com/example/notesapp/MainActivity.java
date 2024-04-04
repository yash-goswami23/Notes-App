package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Dialog;
import android.app.appsearch.GetSchemaResponse;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.databsaes.notesDB;
import com.example.notesapp.adapters.notesAdapter;
import com.example.notesapp.models.Notes;

import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    CardView actionBtnAdd;

    notesDB notesDb;

    notesAdapter notesAdapter;
    TextView emptytext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        actionBtnAdd = findViewById(R.id.actionBtnAdd);
        emptytext = findViewById(R.id.emptyText);
        notesDb = notesDB.getInstance(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showNotes();

        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchItem(s);
                return true;
            }
        });

        actionBtnAdd.setOnClickListener(V->{
            Log.d("MYERROR","onClick actionBtnAdd ");
            Intent i = new Intent(MainActivity.this, add_note_activity.class);
            i.putExtra("value",101);
            startActivityForResult(i,101);
            finish();
        });
    }

    private void searchItem(String s) {
            ArrayList<Notes> arrNotes = (ArrayList<Notes>) notesDb.notedao().getNote();
            ArrayList<Notes> searchList = new ArrayList<>();
                for(Notes notes : arrNotes){
                    if(notes.getTitle().toLowerCase().contains(s.toLowerCase())||
                    notes.getContent().toLowerCase().contains(s.toLowerCase())){
                        searchList.add(notes);
                    }if(searchList.isEmpty()) {
                        Log.d("MYERROR","Not Found");
                    }else {
                        Log.d("MYERROR","Found"+"TITLE : "+searchList.get(0).getTitle());
                        setSearchList(searchList);
                    }
                }
    }

    public void setSearchList(ArrayList<Notes> notes){
        showSearchNotes(notes);
        Log.d("MYERROR","in notesAdapter class setSearchList  ");
    }


    public void showNotes() {
        Log.d("MYERROR","in Show method");
        ArrayList<Notes> arrNotes = (ArrayList<Notes>) notesDb.notedao().getNote();
        if(arrNotes.size()>0){
            Log.d("MYERROR","in Show method in if");
            recyclerView.setAdapter(new notesAdapter(arrNotes, this,notesDb,recyclerView));
            emptytext.setVisibility(View.GONE);
            searchView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }else{
            Log.d("MYERROR","in Show method in else");
            emptytext.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }
    }
    public void showSearchNotes(  ArrayList<Notes> arrNotes) {
        Log.d("MYERROR","in Show method ShowSearch");
        if(arrNotes.size()>0){
            Log.d("MYERROR","in Show method in if inShowSerach");
            recyclerView.setAdapter(new notesAdapter(arrNotes, this,notesDb,recyclerView));
            emptytext.setVisibility(View.GONE);
            searchView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }else{
            Log.d("MYERROR","in Show method in else");
            emptytext.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }
    }
    public String date(){
        Log.d("MYERROR","in Date method");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MM yyyy HH:mm a");
        Date date = new Date();
        String newDate = dateFormat.format(date);
        return  newDate;
    }
}