package com.example.notesapp.adapters;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.MainActivity;
import com.example.notesapp.R;
import com.example.notesapp.add_note_activity;
import com.example.notesapp.databsaes.notesDB;
import com.example.notesapp.models.Notes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class notesAdapter extends RecyclerView.Adapter<notesAdapter.viewHolder>{
    public notesAdapter() {
    }

    ArrayList<Notes> arrNote;
    Context context;
    notesDB notesdb;
    RecyclerView recyclerView;

    public notesAdapter(ArrayList<Notes> arrNote, Context context, notesDB notesdb, RecyclerView recyclerView) {
        this.arrNote = arrNote;
        this.context = context;
        this.notesdb = notesdb;
        this.recyclerView = recyclerView;
    }

    private  int getRandomColor(){

        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.note1);
        colorCode.add(R.color.note2);
        colorCode.add(R.color.note3);
        colorCode.add(R.color.note4);
        colorCode.add(R.color.note5);
        colorCode.add(R.color.note6);

        Random random = new Random();
        int random_color =  random.nextInt(colorCode.size());

        return colorCode.get(random_color);

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        String date = ((MainActivity)context).date();
        viewHolder.note_title_row.setText(arrNote.get(i).getTitle());
        viewHolder.note_content_row.setText(arrNote.get(i).getContent());
        viewHolder.note_date_row.setText(date);
        viewHolder.note_date_row.setSelected(true);
        int pos = i;

        int color_code = getRandomColor();
        viewHolder.cardViewNoteRow.setCardBackgroundColor(viewHolder.itemView.getResources().getColor(color_code));

        Log.d("MYERROR","in notesAdapter class in onBindViewHolder ");
        viewHolder.cardViewNoteRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MYERROR","in notesAdapter class in onBindViewHolder method in onCLick");
                Intent i = new Intent(context,add_note_activity.class);
                i.putExtra("arrTitle", arrNote.get(pos).getTitle());
                i.putExtra("arrContent",arrNote.get(pos).getContent());
                i.putExtra("arrId",arrNote.get(pos).getId());
                Log.d("MYERROR","in notesAdapter class in onBindViewHolder method ingo adpter class id : "+arrNote.get(pos).getId());
                context.startActivity(i);
                ((MainActivity) context).finish();
            }
        });
        viewHolder.cardViewNoteRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("MYERROR","in notesAdapter class in onBindViewHolder method in onLongCLick");
                deleteNotes(pos);
                return true;
            }
        });

    }

    private void deleteNotes(int pos) {
        Log.d("MYERROR","in notesAdapter class in deleteNotes");
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are You Sure To Delete ")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notesdb.notedao().delete(new Notes(arrNote.get(pos).getId(),arrNote.get(pos).getTitle(),arrNote.get(pos).getContent(),null));
                        ((MainActivity)context).showNotes();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    @Override
    public int getItemCount() {
        return arrNote.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{

        TextView note_title_row,note_content_row,note_date_row;
        CardView cardViewNoteRow;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("MYERROR","in notesAdapter class in ViewHolder method");
            note_title_row = itemView.findViewById(R.id.notes_list_title);
            note_content_row = itemView.findViewById(R.id.note_list_content);
            note_date_row = itemView.findViewById(R.id.note_list_date);
            cardViewNoteRow = itemView.findViewById(R.id.cardNote_Row);
        }
    }
}
