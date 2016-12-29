package edu.upc.jonatan.eetakemongo;

/**
 * Created by Jonatan on 26/12/2016.
 */

public class etakemonGO {
    private static etakemonGO instance = null;

    public int currentUserId;

    private etakemonGO() {}

    public static synchronized etakemonGO getInstance() {
        if (instance == null) instance = new etakemonGO();
        return instance;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public static int setCurrentUserId(int currentUserId) {
        return currentUserId;
    }
}

