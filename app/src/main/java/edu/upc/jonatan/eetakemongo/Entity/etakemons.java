package edu.upc.jonatan.eetakemongo.Entity;

/**
 * Created by Jonatan on 02/01/2017.
 */

public class etakemons {
    int id;
    int puntos;
    String tipo, name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

}
