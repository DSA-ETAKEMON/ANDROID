package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.user;
import edu.upc.jonatan.eetakemongo.ServiceLibraryResult.AuthenticationResult;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    Button ingresarbtn;
    private static final String TAG="LOGIN";
    private etakemonGO EtakemonGO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.login);
        et2= (EditText) findViewById(R.id.pswd);
        ingresarbtn = (Button) findViewById((R.id.button2));
        EtakemonGO = etakemonGO.getInstance();
    }
    public void ingresar (View view) {
        final user user = new user();
        user.setNick(et1.getText().toString());
        user.setPassword(et2.getText().toString());

        APIClient.post(this, "/user/login", APIClient.getObjectAsStringEntity(user), "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "Error logging in");
                Toast.makeText(getApplicationContext(), "Error logging in. Please try again!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "Success logging in: " + responseString);
                AuthenticationResult authenticationResult = new Gson().fromJson(responseString, AuthenticationResult.class);
                Log.i(TAG, "Login is successful: " + authenticationResult.isSuccessful);
                if (authenticationResult.isSuccessful) {
                    EtakemonGO.setCurrentUserId(authenticationResult.userId);
                    Toast.makeText(getApplicationContext(), "Welcome " + user.getNick() + "!", Toast.LENGTH_LONG).show();

                    Intent menu = new Intent(MainActivity.this, MenuAct.class);
                    startActivity(menu);
                } else {
                    Toast.makeText(getApplicationContext(), "Username or password incorrect. Please try again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void salir (View v){
        finish();
    }
    public void onClickRegister (View view){
        Intent inb1 = new Intent(MainActivity.this, Register.class);
        startActivity(inb1);
    }
}
