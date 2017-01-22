package edu.upc.jonatan.eetakemongo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.EtakemonsPosition;
import edu.upc.jonatan.eetakemongo.Entity.etakemons;

public class CazarAct extends AppCompatActivity {
    Integer idEtakemon;
    int idUSer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        etakemons etk = new etakemons();
        System.out.println("--------------------- antes de cazar" );

        setContentView(R.layout.activity_cazar);
        Bundle intentdata = getIntent().getExtras();
        if(intentdata!=null) {
            System.out.println("--------------------- dentro de cazar" );
            idEtakemon = intentdata.getInt("etakemonid");
            idUSer = intentdata.getInt("iduser");

            etk.setId(idEtakemon);

            APIClient.post(this, "/etakemon/cazar/"+ idUSer +"/"+idEtakemon, APIClient.getObjectAsStringEntity(etk), "application/json", new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    System.out.print("tu culo jona CAZAR");
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    // Log.i(TAG, "Showing TopUsers");
                    String result =  responseString;
                    Toast.makeText(getApplicationContext(), "Resultado del intento --> " + result , Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


    public  void onClickCapturado (View view){
        Intent juego = new Intent(CazarAct.this, JuegoAct.class);
        startActivity(juego);
    }
}
