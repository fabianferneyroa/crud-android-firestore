package com.veterinaria.operacionescrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.veterinaria.modelo.Persona;

public class MostrarDatosActivity extends AppCompatActivity {

    RecyclerView rv;
    PersonaAdapter adapter;
    FirebaseFirestore firestore= FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        rv= findViewById(R.id.rvPersonas);

        rv.setLayoutManager(new LinearLayoutManager(this));


        Query query = firestore.collection("Personas");

        FirestoreRecyclerOptions<Persona> firestoreRO = new FirestoreRecyclerOptions.Builder<Persona>().setQuery(query,Persona.class).build();

        adapter= new PersonaAdapter(firestoreRO);
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}