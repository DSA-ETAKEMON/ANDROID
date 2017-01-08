package edu.upc.jonatan.eetakemongo.Entity;

/**
 * Created by Jonatan on 09/01/2017.
 */

public class NickOnUse {
    private static NickOnUse instance = null;

    private String UserNick;

    private NickOnUse() {}

    public static synchronized NickOnUse getInstance() {
        if (instance == null) instance = new NickOnUse();
        return instance;
    }
    public String getUserNick() {
        return UserNick;
    }

    public void setUserNick(String currentUserId) {
        this.UserNick = currentUserId;
    }
}

