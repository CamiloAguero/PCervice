package com.example.pcervice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PreguntaRC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_rc);
    }

    public void cerrar(View v){
        Intent i = new Intent(this, RegistroTecnicos.class);
        startActivity(i);
    }
}