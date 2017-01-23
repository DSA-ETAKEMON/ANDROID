package edu.upc.jonatan.eetakemongo.API;

import java.io.UnsupportedEncodingException;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Jonatan on 26/12/2016.
 */

public class APIClient extends AppCompatActivity{
    private static final String TAG="Eetakemon";
    private static String BASE_URL = "http://10.192.253.237:9091/etakemon";

    private static AsyncHttpClient client = new AsyncHttpClient();


    public static StringEntity getObjectAsStringEntity(Object object) {

        StringEntity entity = null;
        try {
            entity = new StringEntity(new Gson().toJson(object));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
        }
        return entity;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url, StringEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), entity, contentType, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void sayHello() {
        client.get(BASE_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "Error saying hello");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "Success saying hello: " + responseString);
            }
        });
    }
}
