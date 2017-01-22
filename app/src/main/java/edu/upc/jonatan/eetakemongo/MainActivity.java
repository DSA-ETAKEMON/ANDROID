package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.upc.jonatan.eetakemongo.Entity.*;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.FormatException;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    Button ingresarbtn;
    Integer idOnUse;
    String nickOnUse;
    String nameOnUse;
    String surnameOnUse;
    String mailOnUse;
    int puntosOnUse;
    int totalEtaOnUse;

    private static final String TAG="LOGIN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.login);
        et2= (EditText) findViewById(R.id.pswdConfET);
        ingresarbtn = (Button) findViewById((R.id.button2));
    }
    public void ingresar (View view) {
        final User usr1 = new User();

        try {
            usr1.setNick(et1.getText().toString());
            usr1.setPassword(et2.getText().toString());
        }catch (FormatException e)
        {
            Log.e(TAG, e.toString());
            Toast.makeText(getApplicationContext(), "-------- " + e.toString(), Toast.LENGTH_LONG).show();

        }

        APIClient.post(this, "/user/login", APIClient.getObjectAsStringEntity(usr1), "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "Error logging in");
                Toast.makeText(getApplicationContext(), "Error logging in. Please try again!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "Success logging in: " + responseString);
                Gson json = new Gson();
                User usrRes = json.fromJson(responseString, User.class);
                Log.i(TAG, "Login is successful: " + usrRes);
                if (usrRes.getNick().equals(usr1.getNick())) {

                    Toast.makeText(getApplicationContext(), "Welcome " + usr1.getNick() + "!", Toast.LENGTH_SHORT).show();
                    idOnUse=usrRes.getId();
                    nameOnUse=usrRes.getName();
                    nickOnUse=usrRes.getNick();
                    surnameOnUse=usrRes.getSurname();
                    mailOnUse=usrRes.getEmail();
                    puntosOnUse=usrRes.getPuntuacionTotal();
                    totalEtaOnUse=usrRes.getTotalEtakemons();

                    Intent menu = new Intent(MainActivity.this, MenuAct.class);
                    menu.putExtra("idUser",idOnUse);
                    menu.putExtra("nameUser",nameOnUse);
                    menu.putExtra("nickUser",nickOnUse);
                    menu.putExtra("surnameUser",surnameOnUse);
                    menu.putExtra("mailUser",mailOnUse);
                    menu.putExtra("puntosUser",puntosOnUse);
                    menu.putExtra("etakemonsUser",totalEtaOnUse);
                    startActivity(menu);
                } else {
                    Toast.makeText(getApplicationContext(), "Username or password incorrect. Please try again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void salir (View v){
        Intent inb1 = new Intent(MainActivity.this, StartAct.class);
        startActivity(inb1);
    }
    public void onClickRegister (View view){
        Intent inb1 = new Intent(MainActivity.this, Register.class);
        startActivity(inb1);
    }
}
