package com.example.befit;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ComidaList extends ArrayAdapter<String> {

    private String[] arrayComida;
    private Integer[] imgId;

    private Activity context;

    public ComidaList(Activity context, String[] arrayComida, Integer[] imgId) {
        super(context, R.layout.activity_main_interface, arrayComida);
        this.context = context;
        this.arrayComida = arrayComida;
        this.imgId = imgId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    class ViewHolder {

    }
}