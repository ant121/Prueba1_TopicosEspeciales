package com.example.prueba1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update extends AppCompatActivity {
    //Declarando Objetos
    private EditText codigo1, fruta1, precio1;

    //Librerias de Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //Enlace de objetos con la vista
        codigo1 = (EditText)findViewById(R.id.txtCodigo);
        fruta1 = (EditText)findViewById(R.id.txtFruta);
        precio1 = (EditText)findViewById(R.id.txtPrecio);
        iniciarFirebase();

        Frutas frutasSelected1 = (Frutas) getIntent().getSerializableExtra("Frutas");
        this.codigo1.setText(frutasSelected1.getCodigo());
        this.fruta1.setText(frutasSelected1.getFruta());
        this.precio1.setText(frutasSelected1.getPrecio());
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void Frutas(View view){
        Intent i = new Intent(Update.this, MainActivity.class);
        startActivity(i);
    }

    public void Update(View view){
        String codigo = codigo1.getText().toString();
        String fruta = fruta1.getText().toString();
        String precio= precio1.getText().toString();

        if(codigo.equals("")){
            this.codigo1.setError("Requerid");
        }else if(fruta.equals("")){
            this.fruta1.setError("Requerid");
        }else if(precio.equals("")){
            this.precio1.setError("Requerid");
        }else{
            Frutas f = new Frutas();
            f.setCodigo(codigo);
            f.setFruta(fruta);
            f.setPrecio(precio);
            databaseReference.child("Frutas").child(f.getCodigo()).setValue(f);
            Toast.makeText(Update.this, "Actualizado", Toast.LENGTH_LONG).show();
            limpiarDatos();
            //Intent i = new Intent();
        }
    }

    public void limpiarDatos(){
        codigo1.setText("");
        fruta1.setText("");
        precio1.setText("");
    }
}