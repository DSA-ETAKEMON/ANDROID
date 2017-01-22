package edu.upc.jonatan.eetakemongo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

import static com.google.android.gms.analytics.internal.zzy.n;

public class JuegoAct extends AppCompatActivity {

    int numberRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        numberRandom = (int) (Math.random() * 6) + 1;
        System.out.println("numero:  " + numberRandom);
    }

    public void onClickBack (View view){
        this.finish();
    }
}
