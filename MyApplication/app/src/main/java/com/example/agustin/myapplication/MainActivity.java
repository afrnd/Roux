package com.example.agustin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    protected Button c;
    protected Map<String, Float> mp;
    public static String filename = "hashmap.ser";
    protected Button mostrar;
    private Button eliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp = new HashMap<>();

        setContentView(R.layout.activity_main);
        c = (Button) findViewById(R.id.btAgregar);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar();
            }
        });
        mostrar = (Button) findViewById(R.id.btMostrar);
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar2();
            }
        });
        eliminar = (Button) findViewById(R.id.btEliminar);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar3();
            }
        });


    }

    private  void agregar(){
        Intent intent = new Intent(this, InsertarNuevo.class);
        intent.putExtra("MAPEO", (Serializable) mp);
        startActivity(intent);

    }

    private void agregar2(){
        Intent intent = new Intent(this, Mostrar.class);
        intent.putExtra("MAPEO", (Serializable) mp);
        startActivity(intent);
    }

    private void agregar3(){
        Intent intent = new Intent(this, Eliminar.class);
        intent.putExtra("MAPEO", (Serializable) mp);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();


        try
        {

            FileOutputStream fos  = this.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mp);
            oos.close();
            fos.close();

        }catch(IOException ioe)
        {

            ioe.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try
        {

            FileInputStream fis = this.openFileInput(filename);

            ObjectInputStream ois = new ObjectInputStream(fis);
            mp = (HashMap) ois.readObject();
            ois.close();
            fis.close();


        }catch(IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }





}
