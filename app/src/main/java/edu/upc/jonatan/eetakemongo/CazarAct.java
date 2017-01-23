package edu.upc.jonatan.eetakemongo;

import android.app.Activity;
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
import android.widget.ImageView;
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
    String tipoetakemon;
    ImageView etakemonImage;
    etakemons etk = new etakemons();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cazar);
        etakemonImage = (ImageView) findViewById(R.id.etaimageD);


        Bundle intentdata = getIntent().getExtras();
        if (intentdata != null) {
            idEtakemon = intentdata.getInt("etakemonid");
            idUSer = intentdata.getInt("iduser");
            etk.setId(idEtakemon);
            tipoetakemon = intentdata.getString("etaketipo");
            System.out.println("tipooooooo     " + tipoetakemon);

            setEetakemonImage(tipoetakemon, etakemonImage);
        }
    }
    public void setEetakemonImage(String tipo, ImageView image) {
        switch (tipo) {
            case "Electrico":
                image.setImageResource(R.drawable.electrico);
                break;
            case "Fuego":
                image.setImageResource(R.drawable.fuego);
                break;
            case "alumno":
                image.setImageResource(R.drawable.alumno);
                break;
            case "profesora":
                image.setImageResource(R.drawable.profesora);
                break;
            case "director":
                image.setImageResource(R.drawable.director);
                break;
            case "alumna":
                image.setImageResource(R.drawable.alumna);
                break;
            case "profesor":
                image.setImageResource(R.drawable.profesor);
                break;
            default:
                image.setImageResource(R.drawable.error_picture);
                break;
        }
    }

    public  void onClickCapturado (View view){
        Intent juego = new Intent(CazarAct.this, JuegoAct.class);
        startActivityForResult(juego,100);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 100) && (resultCode == Activity.RESULT_OK)){

                APIClient.post(this, "/etakemon/cazar/"+ idUSer +"/"+idEtakemon, APIClient.getObjectAsStringEntity(etk), "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        String result =  responseString;
                        Toast.makeText(getApplicationContext(),result , Toast.LENGTH_SHORT).show();
                    }
                });
            this.finish();
        } else if((requestCode == 100) && (resultCode == Activity.RESULT_CANCELED)){
            Toast.makeText(getApplicationContext(), "La próxima vez tendrás más suerte!" , Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

}
