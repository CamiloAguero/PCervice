package com.example.pcervice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Se realiza un public para el "ONCLICK"
    public void login(View v){
        //Recuperar campos
        EditText co = this.findViewById(R.id.correo);
        EditText con = this.findViewById(R.id.contra);
        //Se requieren convertir a "String"
        String correo = co.getText().toString();
        String contra = con.getText().toString();
        //Se validan los datos
        if(correo.equals("ca") && contra.equals("123")){
            Intent i = new Intent(this, Menu.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
    //Se realiza un public para otra accion a realizar
    public void registro(View v){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }
}