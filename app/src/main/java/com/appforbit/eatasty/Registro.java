package com.appforbit.eatasty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Registro extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputName, InputPassword, InputEmail;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        CreateAccountButton = (Button) findViewById(R.id.register_Btn);
        InputName = (EditText) findViewById(R.id.register_username_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        InputEmail = (EditText) findViewById(R.id.register_email_input);
        loadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CreateAccountButton();
            }
        });


    }


    private void CreateAccountButton()
    {
        String name = InputName.getText().toString();
        String password = InputPassword.getText().toString();
        String email = InputEmail.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Por favor, escribe tu nombre", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Por favor, escribe tu contraseña", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Por favor, escribe tu email", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Crear cuenta");
            loadingBar.setMessage("Por favor espere, verificando información.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateUsername(name, password, email);
        }
    }

    private void ValidateUsername(String name, String password, String email)
    {

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

    public int BuscarUsuario(){
        String correo = InputEmail.getText().toString();
        String password = InputPassword.getText().toString();
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
        return id;
    }

}
