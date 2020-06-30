package com.example.prueba1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    //Declarar componentes
    private EditText codigo1, fruta1, precio1;
    private Button create1;
    private TextView Lista1;

    //Librerias de Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Asignacion de componentes con la vista
        codigo1 = (EditText)findViewById(R.id.txtCodigo);
        fruta1 = (EditText)findViewById(R.id.txtFruta);
        precio1 = (EditText)findViewById(R.id.txtPrecio);
        create1 = (Button)findViewById(R.id.btnCreate);
        Lista1 = (TextView)findViewById(R.id.btnFrutas);

        iniciarFirebase();
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void Create(View view){
        String codigo = codigo1.getText().toString();
        String fruta = fruta1.getText().toString();
        String precio= precio1.getText().toString();

        if(codigo.equals("")){
            codigo1.setError("Requerid");
        }else if(fruta.equals("")){
            fruta1.setError("Requerid");
        }else if(precio.equals("")){
            precio1.setError("Requerid");
        }else{
            Frutas f = new Frutas();
            f.setCodigo(codigo);
            f.setFruta(fruta);
            f.setPrecio(precio);
            databaseReference.child("Frutas").child(f.getCodigo()).setValue(f);
            Toast.makeText(Register.this, "Agregado", Toast.LENGTH_LONG).show();
            limpiarDatos();
            //Intent i = new Intent();
        }

    }

    public void limpiarDatos(){
        codigo1.setText("");
        fruta1.setText("");
        precio1.setText("");
    }

    public void Frutas(View view){
        Intent i = new Intent(Register.this, MainActivity.class);
        startActivity(i);
    }
}