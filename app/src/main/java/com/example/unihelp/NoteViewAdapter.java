package com.example.unihelp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteViewAdapter extends RecyclerView.Adapter<NoteViewAdapter.NoteViewHolder> {
    private Context context;
    private List<Note> notes_list;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Note> getNotes_list() {
        return notes_list;
    }

    public void setNotes_list(List<Note> notes_list) {
        this.notes_list = notes_list;
    }

    public NoteViewAdapter(Context context,List<Note> notes_list){
        this.context = context;
        this.notes_list = notes_list;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewAdapter.NoteViewHolder holder, int position) {
        Note note = notes_list.get(position);
        holder.title.setText(note.getTitle());
        holder.mark.setText(String.valueOf(note.getMark()));
        holder.subject.setText(note.getSubject());
        holder.percentage.setText(String.valueOf(note.getPercentage()));
        holder.itemView.setOnClickListener((v)->{
            Log.d("works","onclicklistener works");
            Intent intent = new Intent(context,NoteCreator.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("id", note.getId());
            intent.putExtra("title", note.getTitle());
            intent.putExtra("subject", note.getSubject());
            intent.putExtra("mark", note.getMark());
            intent.putExtra("percentage", note.getPercentage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes_list.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView subject;
        private TextView mark;
        private TextView percentage;

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getSubject() {
            return subject;
        }

        public void setSubject(TextView subject) {
            this.subject = subject;
        }

        public TextView getMark() {
            return mark;
        }

        public void setMark(TextView mark) {
            this.mark = mark;
        }

        public TextView getPercentage() {
            return percentage;
        }

        public void setPercentage(TextView percentage) {
            this.percentage = percentage;
        }
        public NoteViewHolder(@NonNull View note_view) {
            super(note_view);
            title = itemView.findViewById(R.id.note_title_view);
            subject = itemView.findViewById(R.id.note_subject_view);
            mark = itemView.findViewById(R.id.note_mark_view);
            percentage = itemView.findViewById(R.id.note_percentage_view);
        }
    }
}
