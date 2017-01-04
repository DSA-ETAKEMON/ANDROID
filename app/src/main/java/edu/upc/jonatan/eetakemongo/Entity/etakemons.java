package edu.upc.jonatan.eetakemongo.Entity;

/**
 * Created by Jonatan on 02/01/2017.
 */

public class etakemons {
    int id;
    String Puntos;
    String Tipo, name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPuntos() {
        return Puntos;
    }

    public void setPuntos(String puntos) {
        this.Puntos = puntos;
    }

}
