package com.appforbit.eatasty;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MenuPrincipal extends AppCompatActivity {
    private String emailSt;
    private String passwordSt;
    private boolean conectado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("En Conectar");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);
        emailSt="glorenzo68@yahoo.com";//getIntent().getStringExtra("correo");
        passwordSt="pruebaPassword";//getIntent().getStringExtra("contrasenia");
        if (conectado){
            Toast.makeText(this,"Conectado",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
        }
    }
}
