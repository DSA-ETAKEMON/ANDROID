package edu.upc.jonatan.eetakemongo.Entity;

/**
 * Created by Jonatan on 03/01/2017.
 */

public class FormatException extends Exception{
    public FormatException(){super();};
    public  FormatException(String err){
        super(err);
    }
}
