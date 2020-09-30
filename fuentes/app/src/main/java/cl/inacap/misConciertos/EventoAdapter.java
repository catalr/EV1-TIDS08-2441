package cl.inacap.misConciertos;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.inacap.misConciertos.dto.Evento;

public class EventoAdapter extends ArrayAdapter<Evento> {
    private Context context;
    private List<Evento> eventos;


    public EventoAdapter(@NonNull Context context, @NonNull List<Evento> eventos) {
        super(context,0,eventos);
        this.context = context;
        this.eventos = eventos;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View item = convertView;
        if(item == null){
            item = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        }
        Evento ev = eventos.get(position);

        TextView fecha = (TextView)item.findViewById(R.id.fechsV);
        fecha.setText(ev.getFecha());

        TextView artista = (TextView)item.findViewById(R.id.artistaV);
        artista.setText(ev.getArtista());

        TextView valor = (TextView)item.findViewById(R.id.precioV);
        valor.setText(String.valueOf(ev.getValorEntrada()));

        ImageView grade = (ImageView)item.findViewById(R.id.gradeicV);
        Drawable icon;
        int nota = ev.getCalificacion();
        if(nota <= 3){
            icon = context.getResources().getDrawable(R.drawable.vinyl_broken1);
        }else if (nota <= 5){
            icon = context.getResources().getDrawable(R.drawable.vinyl_unbroken);
        }else{
            icon = context.getResources().getDrawable(R.drawable.vinyl_shiny);
        }
        grade.setImageDrawable(icon);

        return item;


    }
}
