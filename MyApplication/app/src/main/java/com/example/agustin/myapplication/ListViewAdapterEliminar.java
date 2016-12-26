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

import java.util.Map;

/**
 * Created by a_str on 26/12/2016.
 */

public class ListViewAdapterEliminar extends BaseAdapter {
    private Context context;
    private int[] imagenes;
    private String[] titulos;
    private String[] contenido;
    private LayoutInflater inflater;
    private Map<String,Float> mp;

    public ListViewAdapterEliminar(Context context, String[] titulos, String[] valoraciones , Map<String, Float> mp ){
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

    public Map<String, Float> getMp(){
        return mp;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ImageView imgImg;

        View itemView;



        // Declare Variables

        TextView txtTitle;
        final RatingBar bar;
        Button bt;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        itemView = inflater.inflate(R.layout.list_row2, parent, false);

        // Locate the TextViews in listview_item.xml
        imgImg = (ImageView) itemView.findViewById(R.id.imagen_single_post_circuito);
        txtTitle = (TextView) itemView.findViewById(R.id.item);
        bar = (RatingBar) itemView.findViewById(R.id.rtBar);
        bt = (Button) itemView.findViewById(R.id.botonLista);


        // Capture position and set to the TextViews
        final String titulo;
        if(titulos[position].length()>0) {    // Capture position and set to the TextViews
            titulo = titulos[position].substring(1);
        }
        else{
            titulo = titulos[position];
        }
        txtTitle.setText(titulo);
        bar.setNumStars(5);
        bar.setRating(Float.parseFloat(contenido[position]));

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.remove(titulos[position]);
            }
        });



        return itemView;
    }




}


