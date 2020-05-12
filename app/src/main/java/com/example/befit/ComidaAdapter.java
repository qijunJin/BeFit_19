package com.example.befit;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ComidaAdapter extends BaseAdapter {

    private ArrayList<Comida> comidaList;
    private Context context;

    public ComidaAdapter(Context context, ArrayList<Comida> comidaList) {
        this.comidaList = comidaList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return comidaList.size();
    }

    @Override
    public Object getItem(int position) {
        return comidaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comida comida = (Comida) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.comida, null);
        ImageView imgId = convertView.findViewById(R.id.imgId);
        TextView txtComida = convertView.findViewById(R.id.comida_name);
        TextView txtCal = convertView.findViewById(R.id.comida_cal);
        TextView txtCaloriesUnit = convertView.findViewById(R.id.caloriesUnit_food);

        imgId.setImageResource(comida.getImgId());
        txtComida.setText(comida.getComidaName());
        txtCal.setText(Integer.toString(comida.getCalPerServing()));
        txtCal.setVisibility(View.VISIBLE);

        txtComida.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/futura-pt-demibold.otf"));
        txtCal.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/futura-pt-heavy-oblique.otf"));
        txtCaloriesUnit.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/Futura Book.ttf"));

        return convertView;
    }
}

