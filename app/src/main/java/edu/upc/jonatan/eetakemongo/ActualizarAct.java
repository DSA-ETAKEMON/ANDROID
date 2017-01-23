package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.FormatException;
import edu.upc.jonatan.eetakemongo.Entity.User;

public class ActualizarAct extends AppCompatActivity {

    EditText actualpassET;
    EditText newpassET;
    EditText confirmpassET;

    String nickonuse;
    String newPass;
    String confirmPass;

    private static final String TAG="ACTUALIZAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_actualizar);

        actualpassET = (EditText) findViewById(R.id.ActualPassET);
        newpassET = (EditText) findViewById(R.id.newPassET);
        confirmpassET = (EditText) findViewById(R.id.pswdConfET);

        Bundle intentdata = getIntent().getExtras();
        String nickUser = intentdata.getString("nickUser");
        nickonuse = nickUser;

    }
    public void onClickCambiar (View view){

        newPass = newpassET.getText().toString();
        confirmPass = confirmpassET.getText().toString();

        final User usr1 = new User();

        try {
            usr1.setNick(nickonuse);
            usr1.setPassword(actualpassET.getText().toString());
        }catch (FormatException e)
        {
            Log.e(TAG, e.toString());
        }
        APIClient.post(this, "/user/login", APIClient.getObjectAsStringEntity(usr1), "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "Error pass");
                Toast.makeText(getApplicationContext(), "Error, actual password no existe", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "actual pass correct " + responseString);
                Gson json = new Gson();
                User usrRes = json.fromJson(responseString, User.class);
                Log.i(TAG, "Login is successful: " + usrRes);
                if (usrRes.getNick().equals(usr1.getNick())) {
                    System.out.println("paso 1");
                    System.out.println("newpass:   " + newPass);
                    System.out.println("confirmpass:    " + confirmPass);
                    if (newPass.equals(confirmPass)&& newPass.length()!=0) {
                        try {
                            usrRes.setPassword(newPass);
                        } catch (FormatException e) {
                            System.out.println("erroor     " + e);
                        }
                        APIClient.post(ActualizarAct.this, "/user/update", APIClient.getObjectAsStringEntity(usrRes), "application/json", new TextHttpResponseHandler() {

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Log.e(TAG, "Error pass");
                                Toast.makeText(getApplicationContext(), "Error, password no es correcto", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                System.out.println("paso 3");
                                Log.i(TAG, "change ok " + responseString);
                                Gson json = new Gson();
                                User usrRes = json.fromJson(responseString, User.class);
                                Log.i(TAG, "Change pass succesful " + usrRes);
                                Toast.makeText(getApplicationContext(), "Password cambiado correctamente", Toast.LENGTH_LONG).show();
                                finish();

                            }
                        });
                    } else {
                        System.out.println("Nuevo password i confirmación de nuevo password no coinciden");
                        Toast.makeText(getApplicationContext(), "Nuevo password i confirmación de nuevo password no coinciden", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No es posible cambiar el password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
