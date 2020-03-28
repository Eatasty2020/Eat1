package com.appforbit.eatasty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.*;



public class Login extends AppCompatActivity {
    EditText emailET;
    EditText passwordET;
    Button btnConectar;
    Button btnRegistrarse;
    public boolean encontrado = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET = (EditText) findViewById(R.id.loginEmail);
        passwordET = (EditText) findViewById(R.id.loginPassword);
        btnConectar = (Button) findViewById(R.id.botonConectar);
        btnRegistrarse = (Button) findViewById(R.id.botonRegistrarse);
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conectarse();
            }
        });
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarse();
            }
        });
    }

    public Connection conexionBD() {
        Connection cn = null;
        String urlDB = "jdbc:jtds:sqlserver://eatasty.mssql.somee.com:1433/eatasty";
        String userDB = "glorenzo68_SQLLogin_1";
        String passwordDB = "qlwnekx8xb";
        try{
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection(urlDB,userDB,passwordDB);
            Toast.makeText(getApplicationContext(),"Enlace establecido", Toast.LENGTH_SHORT).show();
        } catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return cn;
    }

    public void conectarse(){
        String correo = emailET.getText().toString();
        String password = passwordET.getText().toString();
        int id = 0;
        try {
            Statement st = conexionBD().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM [eatasty].[dbo].[users] WHERE correoU = '"+correo+"' AND passwordU = '"+password+"'");
            while (rs.next()) {
                id=rs.getInt(1);
            }
        } catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (id>0){
            Toast.makeText(getApplicationContext(), "Usuario encontrado: "+id, Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrarse(){
        Toast.makeText(getApplicationContext(), "Aún no implementado", Toast.LENGTH_SHORT);
    }
    public class pruebaparagithub{}
}

