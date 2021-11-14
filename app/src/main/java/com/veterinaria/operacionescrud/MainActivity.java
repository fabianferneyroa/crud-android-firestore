package com.veterinaria.operacionescrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private EditText edtxDocumento,edtxNombre,edtxApellido;

    private Button btnRegistrar,btnEditar,btnEliminar,btnConsultar;

FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtxDocumento=findViewById(R.id.edtxDocumento);
        edtxNombre=findViewById(R.id.edtxNombre);
        edtxApellido=findViewById(R.id.edtxApellido);

        btnRegistrar=findViewById(R.id.btnRegistrar);
        btnEditar=findViewById(R.id.btnEditar);
        btnEliminar=findViewById(R.id.btnEliminar);
        btnConsultar=findViewById(R.id.btnConsultar);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        });


    }



    private void registrar(){

        Map<String,Object> persona = new HashMap<>();
        persona.put("nombre",edtxNombre.getText().toString());
        persona.put("apellido",edtxApellido.getText().toString());


        firestore.collection("Personas").document(edtxDocumento.getText().toString()).set(persona);

    }

    private void editar(){

        Map<String,Object> persona = new HashMap<>();
        persona.put("nombre",edtxNombre.getText().toString());
        persona.put("apellido",edtxApellido.getText().toString());


        firestore.collection("Personas").document(edtxDocumento.getText().toString()).update(persona);

    }

    private void eliminar(){

        firestore.collection("Personas").document(edtxDocumento.getText().toString()).delete();
    }

    private void consultar(){

        startActivity(new Intent(MainActivity.this,MostrarDatosActivity.class));

    }






}