package edu.upc.jonatan.eetakemongo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.EtakemonsDescription;

public class WikilistInfoAct extends AppCompatActivity {

    final String TAG="WikiListDescrip";

    ImageView etakemonImage;
    TextView NameText;
    String nameetakemon;
    String tipoetakemon;
    int puntuacionCombate;
    TextView tipoEtakemonTV;
    TextView puntuacionCP;
    int idEta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wikilist_info);

        NameText = (TextView) findViewById(R.id.nameEta);
        etakemonImage= (ImageView) findViewById(R.id.etaImage);
        tipoEtakemonTV = (TextView) findViewById(R.id.tipoEta);
        puntuacionCP = (TextView) findViewById(R.id.puntosTV);


        Bundle intentData = getIntent().getExtras();
        String nameEta = intentData.getString("NameEtakemon");
        idEta = intentData.getInt("idEtakemon");
        int puntosEta = intentData.getInt("PuntosEtakemon");
        String tipoEta = intentData.getString("TipoEtakemon");
        if(nameEta.length()!=0) {
            nameetakemon = nameEta;
            NameText.setText("Nombre: " + nameEta);
        }
        if(tipoEta.length()!=0) {
            tipoetakemon = tipoEta;
            tipoEtakemonTV.setText("Tipo: " + tipoEta);
        }
        //puntosEtakemonTV.setText(puntosEta);
        puntuacionCombate = puntosEta;
        puntuacionCP.setText("CP: " + puntosEta + "puntos");


        APIClient.get("/etakemon/getdescription/" + idEta, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "Error taking info from Server");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Gson json = new Gson();
                EtakemonsDescription etares = json.fromJson(responseString, EtakemonsDescription.class);

                String descripcionEta;
                TextView descripcionTV = (TextView) findViewById(R.id.descriptionTV);
                descripcionEta = etares.getPoder();

                descripcionTV.setText("Poder: " + descripcionEta);

            }
        });


        setEetakemonImage(tipoEta, etakemonImage);


    }
    public void setEetakemonImage(String nom, ImageView image) {
        switch (nom) {
            case "Electrico":
                image.setImageResource(R.drawable.electrico);
                break;
            case "Fuego":
                image.setImageResource(R.drawable.fuego);
                break;
            case "alumno":
                image.setImageResource(R.drawable.alumno);
                break;
            case "profesor":
                image.setImageResource(R.drawable.profesor);
                break;
            case "director":
                image.setImageResource(R.drawable.director);
                break;
            default:
                image.setImageResource(R.drawable.error_picture);
                break;
        }
    }
    public void onClickBack (View view){
        this.finish();
    }
}

