package edu.upc.jonatan.eetakemongo.Entity;

/**
 * Created by Jonatan on 22/01/2017.
 */

public class EtakemonsPosition {


    int id, idetakemon;
    float lat;
    float lng;
    String tipoetakemon;


    public int getIdetakemon() {
        return idetakemon;
    }

    public void setIdetakemon(int idetakemon) {
        this.idetakemon = idetakemon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getTipoetakemon() {
        return tipoetakemon;
    }

    public void setTipoetakemon(String tipoetakemon) {
        this.tipoetakemon = tipoetakemon;
    }

}
