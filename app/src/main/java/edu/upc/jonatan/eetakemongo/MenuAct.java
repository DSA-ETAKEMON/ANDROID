package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuAct extends AppCompatActivity {

    int idonuse;
    String nameonuse;
    String nickonuse;
    String surnameonuse;
    String mailonuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
            Bundle intentdata = getIntent().getExtras();
            Integer idUser = intentdata.getInt("idUser");
            String nameUser = intentdata.getString("nameUser");
            String nickUser = intentdata.getString("nickUser");
            String surnameUser = intentdata.getString("surnameUser");
            String mailUser = intentdata.getString("mailUser");

            idonuse = idUser;
            nickonuse = nickUser;
            nameonuse = nameUser;
            surnameonuse = surnameUser;
            mailonuse = mailUser;
    }
    public void onClickCazar (View view){
        Intent Cazar = new Intent(MenuAct.this, MapAct.class);
        startActivity(Cazar);
    }
    public void onClickPerfil (View view){
        Intent perfil = new Intent(MenuAct.this, PerfilAct.class);
        perfil.putExtra("idUser",idonuse);
        perfil.putExtra("nameUser",nameonuse);
        perfil.putExtra("nickUser",nickonuse);
        perfil.putExtra("surnameUser",surnameonuse);
        perfil.putExtra("mailUser",mailonuse);
        startActivity(perfil);
    }
    public void onClickRanking (View view){
        try {
            Intent Ranking = new Intent(MenuAct.this, RankingAct.class);
            startActivity(Ranking);
        }catch (Exception e){
            System.out.println("errooooooor                       "+e);
        }
    }
    public void onClickEetakedex (View view){

        Intent Etakedex = new Intent(this, EtakedexActivity.class);
        Etakedex.putExtra("idUser",idonuse);
        startActivity(Etakedex);
    }
    public void onClickBack (View view){
        Intent inicio = new Intent (this, MainActivity.class);
        startActivity(inicio);

    }
    public void onClickWikilist (View view){

        Intent wikilist = new Intent(this, WikilistAct.class);
        startActivity(wikilist);
    }
}
