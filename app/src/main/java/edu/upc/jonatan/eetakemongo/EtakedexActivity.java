package edu.upc.jonatan.eetakemongo;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.NickOnUse;
import edu.upc.jonatan.eetakemongo.Entity.User;
import edu.upc.jonatan.eetakemongo.Entity.etakemons;


public class EtakedexActivity extends ListActivity {
    final String TAG="ETAKEDEX";
    NickOnUse nickOnUse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etakedex);
        nickOnUse = NickOnUse.getInstance();
        getElementList();
    }
    public void getElementList (){

        //final etakemons Etakemons = new etakemons();

        APIClient.get("/etakemon/misestakemons" + nickOnUse.getUserNick()  ,null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "Error taking info from Server");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "Showing Eetakemons");
                Type listType = new TypeToken<ArrayList<etakemons>>(){}.getType();
                List <etakemons> eta2 = new Gson().fromJson(responseString, listType);
                //etakemons Etake = new Gson().fromJson(responseString, etakemons.class);
                setListAdapter(new EetakedexAdapter(EtakedexActivity.this, eta2));
            }
        });
    }

}

