package com.example.unihelp;

import android.content.Context;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventViewAdapter extends RecyclerView.Adapter<EventViewAdapter.EventViewHolder> {

    private Context context;
    private List<Evento> eventsList;

    public EventViewAdapter(Context context, List<Evento> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(context).inflate(R.layout.event_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Evento event = eventsList.get(position);
        holder.des.setText(event.getDes());
        holder.subject.setText(event.getSubject());

        // Obtener una instancia de EventoDao a partir del context
        EventoDao eventoDao = BaseDeDatos.getInstance(context).eventoDao();

        // Asignar OnClickListener al botón bt_delete
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Borrar evento de la lista
                Evento evento = eventsList.get(holder.getAdapterPosition());
                eventoDao.delete(evento);
                eventsList.remove(holder.getAdapterPosition());
                // Notificar al adaptador que se eliminó un elemento
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
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
        private Button btDelete;

        public EventViewHolder(@NonNull View EventView) {
            super(EventView);
            des = itemView.findViewById(R.id.event_des_view);
            subject = itemView.findViewById(R.id.event_subject_view);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}