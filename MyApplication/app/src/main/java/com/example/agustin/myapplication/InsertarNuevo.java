package com.example.agustin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class InsertarNuevo extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private RatingBar rt;
    private EditText txt;
    private Button bt;
    private Map<String, Float> mp;

    public static String filename = "hashmap.ser";
    private int cat;
    private Spinner sp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_nuevo);

        rt = (RatingBar) findViewById(R.id.rtBar);
        txt = (EditText) findViewById(R.id.etTexto);
        bt = (Button) findViewById(R.id.btSub);

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
                mp.put(cat+name,cant);

                Toast toast = Toast.makeText(getApplicationContext(),"Entidad insertada correctamente!", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        sp = (Spinner) this.findViewById(R.id.spinner);
        String array[] = {"Cine y series", "Comidas y bebidas" , "Restaurantes", "Deportes", "Libros", "Hoteles", "Lugares", "Teatros y obras", "Hospitales"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(spinnerArrayAdapter);
        sp.setOnItemSelectedListener(this);
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


            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe)
        {

            ioe.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        cat = parent.getSelectedItemPosition();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
