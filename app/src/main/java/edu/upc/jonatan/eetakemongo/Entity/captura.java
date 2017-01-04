package edu.upc.jonatan.eetakemongo.Entity;

import java.util.Calendar;

/**
 * Created by Jonatan on 02/01/2017.
 */

public class captura {
    private int id;
    private int idUser;
    //private User User;
    private int idEtakemon;
   // private Etakemons etakemons;
    private int idLocation;
    //private Location location;
    private Calendar date;
    private int points;
    private boolean isSuccessful;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

   /* public User getUser() {
        return User;
    }*/

    /*public void setUser(User User) {
        this.User = User;
    }*/

    public int getIdEtakemon() {
        return idEtakemon;
    }

    public void setIdProfemon(int idEtakemon) {
        this.idEtakemon = idEtakemon;
    }

  /*  public Etakemons getEtakemons() {
        return etakemons;
    }

    public void setEtakemons(Etakemons etakemons) {
        this.etakemons = etakemons;
    }*/

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

   /* public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }*/

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

}
