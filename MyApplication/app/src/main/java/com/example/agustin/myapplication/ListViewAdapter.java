package com.example.agustin.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
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
    private Context context;
    private int[] imagenes;
    private String[] titulos;
    private String[] contenido;
    private LayoutInflater inflater;
    private Map<String,Float> mp;
    private Drawable dr [];

    public ListViewAdapter(Context context, String[] titulos, String[] valoraciones , Map<String, Float> mp ){
        this.context = context;

        this.titulos = titulos;
        this.contenido = valoraciones;
        this.mp = mp;




        imagenes = new int[]{R.drawable.movie
                ,R.drawable.comifa
                ,R.drawable.resto
                ,R.drawable.depor
                ,R.drawable.libros
                ,R.drawable.hotel
                ,R.drawable.places
                ,R.drawable.teatr
                ,R.drawable.hospi};








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

            itemView = inflater.inflate(R.layout.list_row, parent, false);

            // Locate the TextViews in listview_item.xml
            imgImg = (ImageView) itemView.findViewById(R.id.imagen);
            txtTitle = (TextView) itemView.findViewById(R.id.item);
            bar = (RatingBar) itemView.findViewById(R.id.rtBar);
            bt = (Button) itemView.findViewById(R.id.botonLista);




        final String titulo;
        if(titulos[position].length()>0) {    // Capture position and set to the TextViews
             titulo = titulos[position].substring(1);
        }
        else{
            titulo = titulos[position];
        }
        String cad="";
        cad=cad+titulos[position].charAt(0);
        int a=Integer.parseInt(cad);
        imgImg.setImageResource(imagenes[a]);






        txtTitle.setText(titulo);
            bar.setNumStars(5);
            bar.setRating(Float.parseFloat(contenido[position]));

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.put(titulos[position], bar.getRating());
                    Toast toast = Toast.makeText(context,"Modificado. Refresque la pantalla", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });



        return itemView;
    }




}
