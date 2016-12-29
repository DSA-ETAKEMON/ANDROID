package edu.upc.jonatan.eetakemongo;

import android.os.AsyncTask;

import edu.upc.jonatan.eetakemongo.API.Cliente;

/**
 * Created by Jonatan on 26/12/2016.
 */

public class LoginTask extends AsyncTask<Void, Void, Boolean>{

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean isOk = false;
        try {
            Cliente cl = Cliente.getInstance();
            isOk = cl.login("jona", "admin");

        } catch (Exception e) {
            System.out.print("******" + e.toString());
        }
        return isOk;
    }
}
