package com.example.agustin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class InsertarNuevo extends AppCompatActivity {

    private RatingBar rt;
    private EditText txt;
    private Button bt;
    private Map<String, Float> mp;
    private TextView texto;
    public static String filename = "hashmap.ser";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_nuevo);

        rt = (RatingBar) findViewById(R.id.rtBar);
        txt = (EditText) findViewById(R.id.etTexto);
        bt = (Button) findViewById(R.id.btSub);
        texto = (TextView) findViewById(R.id.txtMostrar);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null){
            mp =(Map<String, Float>) extras.getSerializable("MAPEO");
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float cant = rt.getRating();
                String name = txt.getText().toString();
                mp.put(name,cant);
                texto.setText(mp.toString());

            }
        });
    }

    protected void onPause() {
        super.onPause();

        try
        {

            FileOutputStream fos  = this.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mp);
            oos.close();
            fos.close();
            Toast toast3 = Toast.makeText(this, "Entre", Toast.LENGTH_SHORT);
            toast3.show();

            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe)
        {

            ioe.printStackTrace();
        }
    }
}
