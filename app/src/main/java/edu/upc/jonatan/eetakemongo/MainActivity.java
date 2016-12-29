package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;


import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.user;
import edu.upc.jonatan.eetakemongo.ServiceLibraryResult.AuthenticationResult;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    private Cursor fila;
    Button ingresarbtn;
   // private static final String TAG="login";
   // private etakemonGO EtakemonGO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.login);
        et2= (EditText) findViewById(R.id.pswd);
        ingresarbtn = (Button) findViewById((R.id.button2));
      //  EtakemonGO = etakemonGO.getInstance();
    }
    public void ingresar (View view) throws UnsupportedEncodingException {
        final user user = new user();
        user.setNick(et1.getText().toString());
        user.setPassword(et2.getText().toString());

        RequestParams params = new RequestParams();
        params.put("nick","jona");
        params.put("password","admin");

        StringEntity entity = new StringEntity(user.toString());
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded"));
        AsyncHttpClient a = new AsyncHttpClient();

        APIClient.post(this, "/user/login", entity, "application/x-www-form-urlencoded", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Welcome " + user.getNick() + "!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getApplicationContext(), "Welcome " + user.getNick() + "!", Toast.LENGTH_LONG).show();
            }
        });

/*
            a.get("http://192.168.1.133:8080/etakemon/myresource", new AsyncHttpResponseHandler() {
           @Override
           public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
               Toast.makeText(getApplicationContext(), "Welcome " + user.getNick() + "!", Toast.LENGTH_LONG).show();
               Intent mainIntent = new Intent(MainActivity.this, Menu.class);
               startActivity(mainIntent);
           }

           @Override
           public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
               Toast.makeText(getApplicationContext(), "Error logging in. Please try again!", Toast.LENGTH_LONG).show();


           }
       });*/


      /*  APIClient.post(this, "/user/login", entity, "application/x-www-form-urlencoded", new AsyncHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(getApplicationContext(), "Welcome " + user.getNick() + "!", Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(MainActivity.this, Menu.class);
                        startActivity(mainIntent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), "Error logging in. Please try again!", Toast.LENGTH_LONG).show();

                    }
                });


        /*
        APIClient.post( "/user/login", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Error logging in. Please try again!", Toast.LENGTH_LONG).show();

            }



            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
               // Log.i(TAG, "Success logging in: " + responseString);
              //  AuthenticationResult authenticationResult = new Gson().fromJson(responseString, AuthenticationResult.class);
               // Log.i(TAG, "Login is successful: " + authenticationResult.isSuccessful);
               // if (authenticationResult.isSuccessful) {
                 //   EtakemonGO.setCurrentUserId(authenticationResult.userId);
                    Toast.makeText(getApplicationContext(), "Welcome " + user.getNick() + "!", Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(MainActivity.this, Menu.class);
                    startActivity(mainIntent);
                //} else {
                 //   Toast.makeText(getApplicationContext(), "Username or password incorrect. Please try again!", Toast.LENGTH_LONG).show();
                //}
            }
        });
        */
    }


    public void salir (View v){
        finish();
    }
    public void onClickRegister (View view){
        Intent inb1 = new Intent(MainActivity.this, Register.class);
        startActivity(inb1);
    }
}
