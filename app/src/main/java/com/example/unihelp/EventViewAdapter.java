package com.example.unihelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class EventViewAdapter extends RecyclerView.Adapter<EventViewAdapter.EventViewHolder> {


    private Context context;
    private List<Evento> eventsList;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Evento> getEvent_list() {
        return eventsList;
    }

    public void setEvent_list(List<Evento> eventsList) {
        this.eventsList = eventsList;
    }


    public EventViewAdapter(Context context,List<Evento> eventsList){
        this.context=context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(context).inflate(R.layout.event_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewAdapter.EventViewHolder holder, int position) {

        Evento event = eventsList.get(position);
        holder.des.setText(event.getDes());
        holder.subject.setText(event.getSubject());
    }

    @Override
    public int getItemCount() {
        if (eventsList == null || eventsList.isEmpty()) {
            return 0;
        }
        return eventsList.size();

    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView subject;
        private TextView des;


        public EventViewHolder(@NonNull View EventView) {
            super(EventView);
            des = itemView.findViewById(R.id.event_des_view);
            subject = itemView.findViewById(R.id.event_subject_view);
        }
    }
}
