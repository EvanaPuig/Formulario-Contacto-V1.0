package mx.evisoft.formulariocontacto;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatosDeEntrada extends AppCompatActivity {

    private EditText tvNombreCompleto;
    private EditText tvCumpleanos;
    private EditText tvTelefono;
    private EditText tvEmail;
    private EditText tvDescripcion;

    private DatePickerDialog  dpCumpleanos;

    private SimpleDateFormat dateFormatter;

    private Button btnSiguiente;

    String nombreCompleto;
    String cumpleanos;
    String telefono;
    String email;
    String descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_de_entrada);

        buscarVistas();

        obtenerIntent();

        setTextos();

        tvCumpleanos.setInputType(InputType.TYPE_NULL);

        crearCalendario();

        onClicks();



    }

    private void buscarVistas(){
        tvNombreCompleto = (EditText) findViewById(R.id.editNombreCompleto);
        tvCumpleanos = (EditText) findViewById(R.id.editCumpleanos);
        tvTelefono = (EditText) findViewById(R.id.editTelefono);
        tvEmail = (EditText) findViewById(R.id.editEmail);
        tvDescripcion = (EditText) findViewById(R.id.editDescripcion);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
    }

    private void obtenerIntent(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null){
            nombreCompleto = extras.getString(getResources().getString(R.string.param_nombre_completo));
            cumpleanos = extras.getString(getResources().getString(R.string.param_cumpleanos));
            telefono = extras.getString(getResources().getString(R.string.param_telefono));
            email = extras.getString(getResources().getString(R.string.param_email));
            descripcion = extras.getString(getResources().getString(R.string.param_descripcion));
        }

    }

    private void setTextos(){
        tvNombreCompleto.setText(nombreCompleto);
        tvCumpleanos.setText(cumpleanos);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);
    }

    private void crearCalendario(){
        dateFormatter = new SimpleDateFormat("dd/MMMM/yyyy", Locale.US);

        Calendar newCalendar = Calendar.getInstance();
        dpCumpleanos = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvCumpleanos.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void onClicks(){
        tvCumpleanos.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    dpCumpleanos.show();
                }

            }
        });

        tvCumpleanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpCumpleanos.show();
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosDeEntrada.this, DespliegueDeDatos.class);
                intent.putExtra(getResources().getString(R.string.param_nombre_completo),tvNombreCompleto.getText().toString());
                intent.putExtra(getResources().getString(R.string.param_cumpleanos),tvCumpleanos.getText().toString());
                intent.putExtra(getResources().getString(R.string.param_telefono),tvTelefono.getText().toString());
                intent.putExtra(getResources().getString(R.string.param_email),tvEmail.getText().toString());
                intent.putExtra(getResources().getString(R.string.param_descripcion),tvDescripcion.getText().toString());
                startActivity(intent);
            }
        });
    }
}
