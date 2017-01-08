package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAct extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void onClickCazar (View view){
        Intent Cazar = new Intent(MenuAct.this, CazarAct.class);
        startActivity(Cazar);
    }
    public void onClickPerfil (View view){
        Intent Cazar = new Intent(MenuAct.this, Register.class);
        startActivity(Cazar);
    }
    /*public void onClickRanking (View view){
        Intent Cazar = new Intent(MenuAct.this, Cazar.class);
        startActivity(Cazar);
    }*/
    public void onClickEetakedex (View view){
        Bundle intentdata = getIntent().getExtras();
        String nick = intentdata.getString("nick");
        Intent Etakedex = new Intent(MenuAct.this, EtakedexActivity.class);
        Etakedex.putExtra("nick2",nick);
        startActivity(Etakedex);
    }
    public void onClickExit (View view){
        Intent inicio = new Intent (MenuAct.this, MainActivity.class);
        startActivity(inicio);

    }
}
