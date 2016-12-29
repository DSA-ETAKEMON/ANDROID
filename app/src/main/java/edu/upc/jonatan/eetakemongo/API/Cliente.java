package edu.upc.jonatan.eetakemongo.API;

import android.provider.Settings;
import android.widget.Toast;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jonatan on 26/12/2016.
 */

public class Cliente {

    private final static String BASE_URI = "http://localhost:8080/etakemon";
    private static Cliente instance;
    private ClientConfig clientConfig = null;
    private Client client = null;

    public Cliente() {
        clientConfig = new ClientConfig();
        client = ClientBuilder.newClient(clientConfig);
    }

    public static Cliente getInstance() {
        if (instance == null)
            instance = new Cliente();
        return instance;
    }

    public boolean login(String userid, String password) {
        String loginUri = BASE_URI + "/user/login";

        boolean res=false;
        WebTarget target = client.target(loginUri);
        Form form = new Form();
        form.param("login", userid);
        form.param("password",password);
        String json="";
        try {
           json = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
        } catch (ProcessingException e)
        {
            System.out.print("*************"+ e.toString());
        }
        if(json.equals("Login ok"))
            res=true;
        return res;
    }


    public String getPokemons(String nick) {
        String uri = "";
        String res = "";
        if (nick == null) {
            uri = BASE_URI + "/myresource/list/{" + nick + "}";
        }
        WebTarget target = client.target(uri);
        Response response = target.request().get();
        if (response.getStatus() == Response.Status.OK.getStatusCode())
            res = response.readEntity(String.class);

        return res;
    }
}
