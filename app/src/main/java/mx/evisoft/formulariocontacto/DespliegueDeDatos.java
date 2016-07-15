package mx.evisoft.formulariocontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DespliegueDeDatos extends AppCompatActivity {

    TextView tvNombreCompleto;
    TextView tvCumpleanos;
    TextView tvTelefono;
    TextView tvEmail;
    TextView tvDescripcion;

    Button btnEditar;

    String nombreCompleto;
    String cumpleanos;
    String telefono;
    String email;
    String descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.despliegue_de_datos);

        buscarVistas();

        obtenerIntent();

        setTextos();

        onClicks();
    }

    private void buscarVistas(){
        tvNombreCompleto = (TextView) findViewById(R.id.tvNombreCompleto);
        tvCumpleanos = (TextView) findViewById(R.id.tvCumpleanos);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
        btnEditar = (Button) findViewById(R.id.btnEditar);
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
        tvCumpleanos.setText("Fecha de Nacimiento:" + cumpleanos);
        tvTelefono.setText("Tel. "+ telefono);
        tvEmail.setText("Email: "+ email);
        tvDescripcion.setText("Descripción: " + descripcion);
    }

    private void onClicks(){
        btnEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DespliegueDeDatos.this, DatosDeEntrada.class);
                intent.putExtra(getResources().getString(R.string.param_nombre_completo),nombreCompleto);
                intent.putExtra(getResources().getString(R.string.param_cumpleanos),cumpleanos);
                intent.putExtra(getResources().getString(R.string.param_telefono),telefono);
                intent.putExtra(getResources().getString(R.string.param_email),email);
                intent.putExtra(getResources().getString(R.string.param_descripcion),descripcion);
                startActivity(intent);
            }
        });
    }
}
