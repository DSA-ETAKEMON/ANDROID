package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PerfilAct extends AppCompatActivity {

    String nameonuse;
    String nickonuse;
    String mailonuse;
    String surnameonuse;
    int idonuse;

    TextView NICKONUSE;
    TextView NAMEONUSE;
    TextView MAILONUSE;
    TextView SURNAMEONUSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Bundle intentdata = getIntent().getExtras();
        Integer idUser = intentdata.getInt("idUser");
        String nameUser = intentdata.getString("nameUser");
        String nickUser = intentdata.getString("nickUser");
        String surnameUser = intentdata.getString("surnameUser");
        String mailUser = intentdata.getString("mailUser");

        /*idonuse = idUser;
        nickonuse= nickUser;
        nameonuse=nameUser;
        surnameonuse=surnameUser;
        mailonuse= mailUser;*/

        NICKONUSE = (TextView) findViewById(R.id.nickTv);
        NAMEONUSE = (TextView) findViewById(R.id.nameET);
        SURNAMEONUSE = (TextView) findViewById(R.id.surnameET);
        MAILONUSE = (TextView) findViewById(R.id.mailET);


        if(nickUser.length()!=0) {
            nickonuse = nickUser;
            NICKONUSE.setText(nickUser);
        }
        if(nameUser.length()!=0) {
            nameonuse = nameUser;
            NAMEONUSE.setText(nameUser);
        }
        if(surnameUser.length()!=0) {
            surnameonuse = surnameUser;
            SURNAMEONUSE.setText(surnameUser);
        }
        if(mailUser.length()!=0) {
            mailonuse = mailUser;
            MAILONUSE.setText(mailUser);
        }

    }
    public void onClickUpdate (View view){

        Intent Actualizar = new Intent(this, ActualizarAct.class);
        Actualizar.putExtra("nickUser",nickonuse);
        startActivity(Actualizar);
    }
}
