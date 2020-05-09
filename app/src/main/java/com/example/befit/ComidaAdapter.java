package com.example.befit;

import android.content.Context;
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

        convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
        ImageView imgId = convertView.findViewById(R.id.imgId);
        TextView txtComida = convertView.findViewById(R.id.item_name);

        imgId.setImageResource(comida.getImgId());
        txtComida.setText(comida.getComidaName());

        return convertView;
    }
}
