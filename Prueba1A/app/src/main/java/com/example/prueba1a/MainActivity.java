package com.example.prueba1a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Declarando componentes
    private ListView list_frutas0;
    private List<Frutas> list_Frutas1 = new ArrayList<Frutas>();
    ArrayAdapter<Frutas> arrayAdpterFruta;


    //Librerias de Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Frutas frutasSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignacion de componentes con la vista
        list_frutas0 = (ListView)findViewById(R.id.listFrutas);
        iniciarFirebase();
        listarDatos();

        //Funciones Actualizar
        list_frutas0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                frutasSelected = (Frutas) parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, Update.class);
                i.putExtra("Frutas", frutasSelected);
                startActivity(i);
            }
        });
    }

    private void listarDatos() {
        databaseReference.child("Frutas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list_Frutas1.clear();
                for(DataSnapshot objSanpshot : snapshot.getChildren()){
                    Frutas f = objSanpshot.getValue(Frutas.class);
                    list_Frutas1.add(f);

                    arrayAdpterFruta = new ArrayAdapter<Frutas>(MainActivity.this, android.R.layout.simple_list_item_1, list_Frutas1);
                    list_frutas0.setAdapter(arrayAdpterFruta);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void intentRegister(View view){
        Intent i = new Intent(MainActivity.this, Register.class);
        startActivity(i);
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void delete(View view){

    }
}