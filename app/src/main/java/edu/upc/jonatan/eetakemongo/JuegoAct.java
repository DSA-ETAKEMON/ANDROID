package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import static com.google.android.gms.analytics.internal.zzy.n;

public class JuegoAct extends AppCompatActivity {

    int numberRandom;
    int tirada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        numberRandom = (int) (Math.random() * 6) + 1;
        System.out.println("numero:  " + numberRandom);
        tirada =1;
    }

    public void onClickBack (View view){
        this.finish();
    }

    public void onClickBT1 (View v){
        int i = 1;
        if (i==numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "CAZADO!", Toast.LENGTH_SHORT).show();

            Intent intres = getIntent();
            setResult(RESULT_OK,intres);
            finish();

        } else if(i!=numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "Has fallado, prueba otra posición", Toast.LENGTH_SHORT).show();
            tirada++;
        }else{
            Toast.makeText(getApplicationContext(), "Te has quedado sin oportunidades!", Toast.LENGTH_SHORT).show();
            Intent intres = getIntent();
            setResult(RESULT_CANCELED,intres);
            finish();
        }
    }

    public void onClickBT2 (View v){
        int i = 2;
        if (i==numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "CAZADO!", Toast.LENGTH_SHORT).show();

            Intent intres = getIntent();
            setResult(RESULT_OK,intres);
            finish();

        } else if(i!=numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "Has fallado, prueba otra posición", Toast.LENGTH_SHORT).show();
            tirada++;
        }else{
            Toast.makeText(getApplicationContext(), "Te has quedado sin oportunidades!", Toast.LENGTH_SHORT).show();
            Intent intres = getIntent();
            setResult(RESULT_CANCELED,intres);
            finish();
        }
    }

    public void onClickBT3 (View v){
        int i = 3;
        if (i==numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "CAZADO!", Toast.LENGTH_SHORT).show();

            Intent intres = getIntent();
            setResult(RESULT_OK,intres);
            finish();

        } else if(i!=numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "Has fallado, prueba otra posición", Toast.LENGTH_SHORT).show();
            tirada++;
        }else{
            Toast.makeText(getApplicationContext(), "Te has quedado sin oportunidades!", Toast.LENGTH_SHORT).show();
            Intent intres = getIntent();
            setResult(RESULT_CANCELED,intres);
            finish();
        }
    }

    public void onClickBT4 (View v){
        int i = 4;
        if (i==numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "CAZADO!", Toast.LENGTH_SHORT).show();

            Intent intres = getIntent();
            setResult(RESULT_OK,intres);
            finish();

        } else if(i!=numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "Has fallado, prueba otra posición", Toast.LENGTH_SHORT).show();
            tirada++;
        }else{
            Toast.makeText(getApplicationContext(), "Te has quedado sin oportunidades!", Toast.LENGTH_SHORT).show();
            Intent intres = getIntent();
            setResult(RESULT_CANCELED,intres);
            finish();
        }
    }

    public void onClickBT5 (View v){
        int i = 5;
        if (i==numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "CAZADO!", Toast.LENGTH_SHORT).show();

            Intent intres = getIntent();
            setResult(RESULT_OK,intres);
            finish();

        } else if(i!=numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "Has fallado, prueba otra posición", Toast.LENGTH_SHORT).show();
            tirada++;
        }else{
            Toast.makeText(getApplicationContext(), "Te has quedado sin oportunidades!", Toast.LENGTH_SHORT).show();
            Intent intres = getIntent();
            setResult(RESULT_CANCELED,intres);
            finish();
        }
    }
    public void onClickBT6 (View v){
        int i = 6;
        if (i==numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "CAZADO!", Toast.LENGTH_SHORT).show();

            Intent intres = getIntent();
            setResult(RESULT_OK,intres);
            finish();

        } else if(i!=numberRandom && tirada<3){
            Toast.makeText(getApplicationContext(), "Has fallado, prueba otra posición", Toast.LENGTH_SHORT).show();
            tirada++;
        }else{
            Toast.makeText(getApplicationContext(), "Te has quedado sin oportunidades!", Toast.LENGTH_SHORT).show();
            Intent intres = getIntent();
            setResult(RESULT_CANCELED,intres);
            finish();
        }
    }
}
