package cl.inacap.misConciertos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import cl.inacap.misConciertos.dao.EventosDAO;
import cl.inacap.misConciertos.dto.Evento;

public class MainActivity extends AppCompatActivity {

    private EditText artistaTxt;
    private EditText fechaTxt;
    private EditText priceTxt;
    private Spinner calificacionSpn;
    private Spinner generoSpn;
    private Button submitBtn;
    private ListView eventosLv;
    private EventoAdapter listAdapter;
    private DatePickerDialog picker;
    private ArrayAdapter<String> genreAdapter;
    private ArrayAdapter<String> gradeAdapter;
    private EventosDAO EvenDAO = new EventosDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.artistaTxt = findViewById(R.id.artista);
        this.fechaTxt = findViewById(R.id.fecha);
        this.priceTxt = findViewById(R.id.valorEntrada);
        this.calificacionSpn = findViewById(R.id.grade_spinner);
        this.generoSpn = findViewById(R.id.genre_spinner);
        this.submitBtn = findViewById(R.id.submitButton);
        this.eventosLv = findViewById(R.id.listaArtistas);
        this.fechaTxt.setInputType(InputType.TYPE_NULL);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.genreAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,
                Arrays.asList(Evento.getGeneros()));
        this.genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.gradeAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,
                Arrays.asList(Evento.getCalificaciones()));
        this.gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.generoSpn.setAdapter(genreAdapter);
        this.calificacionSpn.setAdapter(gradeAdapter);

        this.fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fechaTxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


        this.submitBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                List<String> errores = new ArrayList<>();
                String artista = artistaTxt.getText().toString().trim();
                String entradaStr = priceTxt.getText().toString().trim();
                String fecha = fechaTxt.getText().toString().trim();
                //TODO: verificar fecha<=today
                String genero = generoSpn.getSelectedItem().toString().trim();
                int calificacion = calificacionSpn.getSelectedItemPosition()+1;
                int entrada = 0;

                if (artista.matches("")){
                    errores.add("-Por favor ingrese el nombre de un artista");
                }

                if (fecha.matches("")){
                    errores.add("-Por favor ingrese fecha del evento");
                }

                try{
                    entrada = Integer.parseInt(entradaStr);
                    if(entrada <= 0){
                        throw new NumberFormatException();
                    }
                }catch(NumberFormatException ex){
                    //what if it was a free show huh
                    errores.add("-El precio de la entrada debe ser mayor a 0");
                }

                if(errores.isEmpty()){
                    Evento evento = new Evento(artista,fecha,genero,entrada,calificacion);
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    //TODO: give a chance to cancel event creation
                    alertBuilder.setTitle("Evento Creado ¿Ingresar?")//Define el titulo
                            .setMessage(evento.toString())//Define el mensaje del dialogo
                            .setPositiveButton("Aceptar", null)//Agrega el boton aceptar
                            .create()
                            .show();
                    EvenDAO.add(evento);
                    actualizarListaEventos();
                }else {
                    mostrarErrores(errores);
                }

            }
        });

    }

    private void actualizarListaEventos() {
        listAdapter = new EventoAdapter(this,EventosDAO.getAll());
        eventosLv.setAdapter(listAdapter);
        eventosLv.setVisibility(View.VISIBLE);
    }

    private void mostrarErrores(List<String> errores){
        String mensaje = "";
        for(String e: errores){
            mensaje += e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Error en el Formulario")//Define el titulo
                .setMessage(mensaje)//Define el mensaje del dialogo
                .setPositiveButton("Aceptar", null)//Agrega el boton aceptar
                .create()
                .show();
    }
}