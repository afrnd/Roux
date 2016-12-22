package com.example.agustin.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Created by agustin on 20/12/2016.
 */

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    int[] imagenes;
    String[] titulos;
    String[] contenido;
    LayoutInflater inflater;
    Map<String,Float> mp;

    public ListViewAdapter(Context context, String[] titulos, String[] valoraciones , Map<String, Float> mp ){
        this.context = context;
        this.imagenes = imagenes;
        this.titulos = titulos;
        this.contenido = valoraciones;
        this.mp = mp;
    }

    @Override
    public int getCount() {
        return titulos.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        ImageView imgImg;
        TextView txtTitle;
        final RatingBar bar;
        Button bt;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row, parent, false);

        // Locate the TextViews in listview_item.xml
        imgImg = (ImageView) itemView.findViewById(R.id.imagen_single_post_circuito);
        txtTitle = (TextView) itemView.findViewById(R.id.item);
        bar = (RatingBar) itemView.findViewById(R.id.rtBar);
        bt = (Button) itemView.findViewById(R.id.botonLista);


        // Capture position and set to the TextViews
        final String titulo = titulos[position];
        txtTitle.setText(titulos[position]);
        bar.setNumStars(5);
        bar.setRating(Float.parseFloat(contenido[position]));
        bar.setEnabled(false);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.put(titulo, bar.getRating());

            }
        });




        return itemView;
    }


}
