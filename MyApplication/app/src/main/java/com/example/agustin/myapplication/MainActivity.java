package com.example.agustin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    protected Button c;
    protected Map<String, Float> mp;

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
    }

    private  void agregar(){
        Intent intent = new Intent(this, InsertarNuevo.class);
        intent.putExtra("MAPEO", (Serializable) mp);
        startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();

        try
        {
            FileOutputStream fos  = new FileOutputStream("hashmap.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mp);
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
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
            FileInputStream fis = new FileInputStream("hashmap.ser");
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
