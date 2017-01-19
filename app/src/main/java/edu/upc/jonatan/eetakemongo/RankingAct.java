package edu.upc.jonatan.eetakemongo;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.User;
import edu.upc.jonatan.eetakemongo.Entity.etakemons;

public class RankingAct extends ListActivity {

    final String TAG="RANKING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getElementList();
    }
    public void getElementList (){

        APIClient.get("/fight/GetTopUsers", null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "Error taking info from Server");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "Showing TopUsers");
                Type listType = new TypeToken<ArrayList<User>>(){}.getType();
                List<User> user2 = new Gson().fromJson(responseString, listType);
                setListAdapter(new RankingAdapter(RankingAct.this, user2));
            }
        });
    }
}
