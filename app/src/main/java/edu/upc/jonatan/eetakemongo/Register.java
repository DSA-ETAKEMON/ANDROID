package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import edu.upc.jonatan.eetakemongo.Entity.User;
import cz.msebera.android.httpclient.Header;
import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.FormatException;

/**
 * Created by Jonatan on 01/01/2017.
 */

public class Register extends AppCompatActivity {

        private static final String TAG = "REGISTER";
        EditText nick;
        EditText name;
        EditText surname;
        EditText mail;
        EditText password;
        EditText pasword2;
        Button registerbtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_register);

            name = (EditText) findViewById(R.id.edName);
            password = (EditText) findViewById(R.id.etPassword);
            mail = (EditText) findViewById(R.id.etMail);
            nick = (EditText) findViewById(R.id.etNick);
            surname = (EditText) findViewById(R.id.etSurname);
            pasword2 = (EditText) findViewById(R.id.etConfirmPass);
            registerbtn = (Button) findViewById(R.id.btRegister);
        }

        public void OnClickRegistrar(View view) {
            final User user = new User();
            try {
                user.setNick(nick.getText().toString());
            } catch (FormatException e) {
                e.printStackTrace();
            }
            try {
                user.setPassword(password.getText().toString());
                user.setEmail(mail.getText().toString());
                user.setName(name.getText().toString());
                user.setSurname(surname.getText().toString());
                user.setPassword(pasword2.getText().toString());
            }catch(FormatException e)
            {
                Log.i(TAG, e.toString());
            }
            if (name.getText().length()!=0 ){
                /* || password.getText().length()==0 ||
                    mail.getText().length()==0 || nick.getText().length()==0 ||
                    surname.getText().length()==0 || pasword2.getText().length()==0*/
               /* Toast.makeText(getApplicationContext(), "Escribe en todos los campos.", Toast.LENGTH_SHORT).show();
            }else if (password.getText() != pasword2.getText()){
                Toast.makeText(getApplicationContext(), "El password y su confirmación no coinciden.", Toast.LENGTH_SHORT).show();
            }*/

                APIClient.post(this, "/user/register", APIClient.getObjectAsStringEntity(user), "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e(TAG, "Error registering from hello");
                        Toast.makeText(getApplicationContext(), "Error al resgistrar, por favor, vuelve a intentarlo", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        if (responseString == null){
                            Toast.makeText(getApplicationContext(), "El nick ya existe.", Toast.LENGTH_LONG).show();
                        }else {
                            Log.i(TAG, "Success registering: " + responseString);
                            User usrReg = new Gson().fromJson(responseString, User.class);
                            Log.i(TAG, "Register is successful: " + usrReg.getNick());
                            if (usrReg.getNick() != null) {
                                Toast.makeText(getApplicationContext(), "Login para empezar a jugar " + user.getNick() + "!", Toast.LENGTH_LONG).show();
                                Intent ib1 = new Intent(Register.this, MainActivity.class);
                                startActivity(ib1);
                            } else {
                                Toast.makeText(getApplicationContext(), "Error al registrar", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    }
                });
            }
        }

        public void onClickCancel(View view) {
            Intent ib1 = new Intent(Register.this, MainActivity.class);
            startActivity(ib1);
        }

    }
