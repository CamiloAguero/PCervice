package com.example.pcervice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }
    //Accion de registrar
    public void registrar(View v){
        //Recuperar los views
        EditText etnombre = (EditText) findViewById(R.id.et_nombre);
        EditText etapellido = (EditText) findViewById(R.id.et_ape);
        EditText etcorreo = (EditText) findViewById(R.id.et_correo);
        EditText etcontra = (EditText) findViewById(R.id.et_contra);
        EditText etrepcontra = (EditText) findViewById(R.id.et_rep_contra);
        //Recuperar el valor
        String nombre = etnombre.getText().toString();
        String apellido = etapellido.getText().toString();
        String correo = etcorreo.getText().toString();
        String contra = etcontra.getText().toString();
        String rep_contra = etrepcontra.getText().toString();
        //RadioButton
        RadioGroup rgtipo = (RadioGroup) findViewById(R.id.rb_tipo);
        int id = rgtipo.getCheckedRadioButtonId();
        String tipo = "";
        switch (id){
            case R.id.rb_user:
                tipo = "Usuario";
                break;
            case R.id.rb_tec:
                tipo = "Tecnico";
                break;
            default:
                Toast.makeText(this, "No se seleccionó un tipo", Toast.LENGTH_SHORT).show();
        }
        Spinner spciudad = (Spinner) findViewById(R.id.sp_ciudad);
        String ciudad = spciudad.getSelectedItem().toString();
        if(contra.equals(rep_contra)){
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fallo en el registro", Toast.LENGTH_SHORT).show();
        }
    }
}