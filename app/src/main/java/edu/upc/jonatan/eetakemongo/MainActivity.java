package edu.upc.jonatan.eetakemongo;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.login);
        et2= (EditText) findViewById(R.id.pswd);
    }
    public void ingresar (View v){
        DBHelper admin = new DBHelper(this,"projectedb",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String user = et1.getText().toString();
        String password = et2.getText().toString();
        fila = db.rawQuery("SELECT nick,password FROM nick= '"+user+"' AND password '"+password+"'",null);

        while (fila.moveToFirst()== true){
            String usuario=fila.getString(0);
            String pass=fila.getString(1);
            if (user.equals (usuario) && pass.equals(pass)){
                Intent ven= new Intent (this, Menu.class);
                startActivity(ven);
                et1.setText("");
                et2.setText("");
            }
            else
                Toast.makeText(getApplicationContext(),"Usuario incorrecto",Toast.LENGTH_LONG).show();
        }
    }
    public void salir (View v){
        finish();
    }
    public void onClickRegister (View view){
        Intent inb1 = new Intent(MainActivity.this, Register.class);
        startActivity(inb1);
    }
}
