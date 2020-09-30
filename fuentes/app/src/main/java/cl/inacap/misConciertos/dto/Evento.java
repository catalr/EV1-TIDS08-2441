package cl.inacap.misConciertos.dto;

import java.util.Date;

public class Evento {
    private String artista;
    private String fecha;
    private String genre;
    private int valorEntrada;
    private int calificacion;
    final static String[] generos = {"Rock", "Jazz", "Pop", "Regueton", "Salsa", "Metal"};
    final static String[] calificaciones = {"1", "2", "3", "4", "5", "6", "7"};


    @Override
    public String toString() {
        return
                "\nArtista: " + artista + '\n' +
                "Fecha: " + fecha + '\n' +
                "Genero Musical: " + genre + '\n' +
                "Valor Entrada: $" + valorEntrada + '\n' +
                "Calificacion: " + calificacion;
    }

    public Evento(String artista, String fecha, String genre, int valorEntrada, int calificacion) {
        this.artista = artista;
        this.fecha = fecha;
        this.genre = genre;
        this.valorEntrada = valorEntrada;
        this.calificacion = calificacion;
    }

    public static String[] getGeneros(){
        return generos;
    }

    public static String[] getCalificaciones(){
        return calificaciones;
    }

    public String getArtista() {
        return artista;
    }



    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(int valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
