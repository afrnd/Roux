package com.example.agustin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Mostrar extends AppCompatActivity {

    private ListView lv;
    private String[] items;
    private String[] items2;
    private Map<String, Float> mp;
    private ListViewAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        lv = (ListView) findViewById(R.id.lista);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            mp = (Map<String, Float>) extras.getSerializable("MAPEO");
        }
        items = new String[mp.size()];
        items2 = new String[mp.size()];

        Set<Map.Entry<String, Float>> set = mp.entrySet();

        int i = 0;

        Iterator<Map.Entry<String, Float>> it = set.iterator();

        while(it.hasNext()){
            Map.Entry<String,Float> entrada = it.next();
            items[i] =  entrada.getKey() ;
            i++;
        }

        Set<Map.Entry<String, Float>> set1 = mp.entrySet();

        int i1 = 0;

        Iterator<Map.Entry<String, Float>> it2 = set.iterator();

        while(it2.hasNext()){
            Map.Entry<String,Float> entrada = it2.next();
            items2[i1] = entrada.getValue().toString() ;
            i1++;
        }

        adaptador = new ListViewAdapter(this, items,items2,mp);
        lv.setAdapter(adaptador);

    }


    protected void onPause() {
        super.onPause();

        try
        {


            FileOutputStream fos  = this.openFileOutput("hashmap.ser", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(adaptador.getMp());
            oos.close();
            fos.close();


            FileInputStream fis = this.openFileInput("hashmap.ser");

            ObjectInputStream ois = new ObjectInputStream(fis);
            mp = (HashMap) ois.readObject();
            ois.close();
            fis.close();



            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe)
        {

            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

