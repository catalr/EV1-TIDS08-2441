package cl.inacap.misConciertos.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.misConciertos.dto.Evento;

public class EventosDAO {
    private static List<Evento> eventos = new ArrayList<>();

    public void add(Evento e){
        eventos.add(e);
    }

    public List<Evento> getAll(){
        return eventos;
    }
}
