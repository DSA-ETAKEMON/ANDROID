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

           /* if (name.getText().length()!=0 || password.getText().length()!=0 ||
                    mail.getText().length()!=0 || nick.getText().length()!=0 ||
                    surname.getText().length()!=0 || pasword2.getText().length()!=0) {*/
            if (name.getText().length()==0){
                Toast.makeText(getApplicationContext(), "Write on all the fields please.", Toast.LENGTH_SHORT).show();
            } else {
                APIClient.post(this, "/user/register", APIClient.getObjectAsStringEntity(user), "application/json", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e(TAG, "Error registering from hello");
                        Toast.makeText(getApplicationContext(), "Error registering. Please try again!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.i(TAG, "Success registering: " + responseString);
                        User usrReg = new Gson().fromJson(responseString, User.class);
                        Log.i(TAG, "Register is successful: " + usrReg.getNick());
                        if (usrReg.getNick()!=null) {
                            Toast.makeText(getApplicationContext(), "Welcome " + user.getNick() + "!", Toast.LENGTH_LONG).show();
                            Intent ib1 = new Intent(Register.this, MenuAct.class);
                            startActivity(ib1);
                        } else {
                            Toast.makeText(getApplicationContext(), "Username or email already exists. Please try again!", Toast.LENGTH_LONG).show();
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
