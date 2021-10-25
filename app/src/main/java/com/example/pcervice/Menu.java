package com.example.pcervice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Se incorpora el toolbar
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        //Se incorpora el Menu lateral
        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Recuperar la opcion del menu
                return false;
            }
        });

        //incorporar Drawer
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.principal);
        //Para que se muestre el boton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Para que el boton exista
        getSupportActionBar().setHomeButtonEnabled(true);
        //Para que el menu se pueda esconder
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                dl,
                R.string.abrir_drawer,
                R.string.cerrar_drawer
        );
        //Agregar el toggle al drawer listener
        dl.addDrawerListener(toggle);
        //Agregar sincronizacion del toggle
        toggle.syncState();
        //El toolbar
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dl.isDrawerOpen(GravityCompat.START)){
                    dl.closeDrawer(GravityCompat.START);
                }else{
                    dl.openDrawer((int) Gravity.START);
                }
            }
        });
    }
    //Metodos
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        //Se incorpora el menu dentro del activity
        getMenuInflater().inflate(R.menu.content,menu);
        return true;
    }
    //Metodo para la accion del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.buscar){

        }
        return super.onOptionsItemSelected(item);
    }
}