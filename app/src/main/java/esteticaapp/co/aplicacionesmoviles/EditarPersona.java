package esteticaapp.co.aplicacionesmoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditarPersona extends AppCompatActivity {

    DatabaseReference databaseReference;

    TextView idEditar;
    EditText nombreEdit;
    EditText edadEdit;
    RadioButton generoMedit , generoFedit;
    Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_persona);


        databaseReference= FirebaseDatabase.getInstance().getReference();

        idEditar = findViewById(R.id.textId);
        nombreEdit = findViewById(R.id.nombreEdit);
        edadEdit = findViewById(R.id.edadEdit);
        generoMedit = findViewById(R.id.radioButtonEditar);
        generoFedit = findViewById(R.id.radioButton2Editar);
        btnEditar = findViewById(R.id.botonEditar);

        idEditar.setText(getIntent().getExtras().getString("id"));
        nombreEdit.setHint(getIntent().getExtras().getString("nombre"));

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreP = nombreEdit.getText().toString();
                int edadP = Integer.valueOf(edadEdit.getText().toString());
                String id = idEditar.getText().toString();
                String generoP;
                if(generoMedit.isChecked()){
                    generoP="masculino";
                }else{
                    generoP="femenino";
                }
                Persona persona=new Persona(nombreP,edadP,generoP);
                databaseReference.child("persona").child(id).setValue(persona);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Usuario modificado", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }

    }

