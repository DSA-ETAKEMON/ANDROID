package edu.upc.jonatan.eetakemongo.API;

import java.io.UnsupportedEncodingException;
import android.content.Context;
import android.util.Log;

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

public class APIClient {
    private static final String TAG="PokEETAC";
    private static String BASE_URL = "http://192.168.1.133:8080/etakemon";

    private static AsyncHttpClient client = new AsyncHttpClient();


    public static StringEntity getObjectAsStringEntity(Object object) {

        StringEntity entity = null;
        try {
            entity = new StringEntity(new Gson().toJson(object));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url, StringEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(50000);
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
