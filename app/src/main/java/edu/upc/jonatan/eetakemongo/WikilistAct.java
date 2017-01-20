package edu.upc.jonatan.eetakemongo;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.etakemons;

public class WikilistAct extends ListActivity {
    final String TAG = "WIKILIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wikilist);
        getElementList();
    }

    public void getElementList() {

        APIClient.get("/etakemon/etakemonslist", null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "Error taking info from Server");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "Showing Eetakemons");
                Type listType = new TypeToken<ArrayList<etakemons>>() {
                }.getType();
                List<etakemons> eta1 = new Gson().fromJson(responseString, listType);
                setListAdapter(new WikilistAdapter(WikilistAct.this, eta1));
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        etakemons et1 = (etakemons) getListAdapter().getItem(position);
        int idEta = et1.getId();
        String tipoEta = et1.getTipo();
        String nameEta = et1.getName();
        int puntosEta = et1.getPuntos();
        Intent showinfo = new Intent(this, WikilistInfoAct.class);
        showinfo.putExtra("idEtakemon", idEta);
        showinfo.putExtra("TipoEtakemon", tipoEta);
        showinfo.putExtra("NameEtakemon", nameEta);
        showinfo.putExtra("PuntosEtakemon", puntosEta);
        startActivity(showinfo);

    }
}
