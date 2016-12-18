package com.example.agustin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Map;

public class InsertarNuevo extends AppCompatActivity {

    private RatingBar rt;
    private EditText txt;
    private Button bt;
    private Map<String, Float> mp;
    private TextView texto;



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
}
