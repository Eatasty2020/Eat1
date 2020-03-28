package com.appforbit.eatasty;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public class pruebaparagithub{
    }
    //Lanzador de Login
    public void Login(View view){
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }

    //Lanzador de Eventos
    public void Evento(View view){
        Intent evento = new Intent(this, Eventos.class);
        startActivity(evento);
    }
}
