package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.Header;
import edu.upc.jonatan.eetakemongo.API.APIClient;
import edu.upc.jonatan.eetakemongo.Entity.user;
import edu.upc.jonatan.eetakemongo.ServiceLibraryResult.AuthenticationResult;

public class Register extends AppCompatActivity {

    private static final String TAG = "REGISTER";
    EditText nick;
    EditText name;
    EditText surname;
    EditText mail;
    EditText password;
    EditText pasword2;
    Button registerbtn;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void OnClickRegistrar(View view) {
        final user user = new user();
        user.setNick(nick.getText().toString());
        user.setPassword(password.getText().toString());
        user.setEmail(mail.getText().toString());
        user.setName(name.getText().toString());
        user.setSurname(surname.getText().toString());
        user.setPassword(pasword2.getText().toString());

        //if (password != pasword2) {
          //  Toast.makeText(getApplicationContext(), "The password is incorrect.", Toast.LENGTH_SHORT).show();
        //} else {
            APIClient.post(this, "/user/register", APIClient.getObjectAsStringEntity(user), "application/json", new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e(TAG, "Error registering from hello");
                    Toast.makeText(getApplicationContext(), "Error registering. Please try again!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Log.i(TAG, "Success registering: " + responseString);
                    AuthenticationResult authenticationResult = new Gson().fromJson(responseString, AuthenticationResult.class);
                    Log.i(TAG, "Register is successful: " + authenticationResult.isSuccessful);
                    if (authenticationResult.isSuccessful) {
                        etakemonGO.setCurrentUserId(authenticationResult.userId);
                        Toast.makeText(getApplicationContext(), "Welcome " + user.getNick() + "!", Toast.LENGTH_LONG).show();
                        Intent ib1 = new Intent(Register.this, Menu.class);
                        startActivity(ib1);
                    } else {
                        Toast.makeText(getApplicationContext(), "Username or email already exists. Please try again!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    //}

        // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.

    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://localhost:8080/etakemon/user/register"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    // Uses AsyncTask to create a task away from the main UI thread. This task takes a
    // URL string and uses it to create an HttpUrlConnection. Once the connection
    // has been established, the AsyncTask downloads the contents of the webpage as
    // an InputStream. Finally, the InputStream is converted into a string, which is
    // displayed in the UI by the AsyncTask's onPostExecute method.
    private class OnClickRegistrar extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Se alamacenaron los datos correctamente", Toast.LENGTH_LONG).show();
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }


    public void onClickCancel(View view) {
        Intent ib1 = new Intent(Register.this, MainActivity.class);
        startActivity(ib1);
    }

}
