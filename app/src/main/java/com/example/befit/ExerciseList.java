package com.example.befit;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ExerciseList extends ArrayAdapter<String> {

    private String[] arrayExercise;
    private Integer[] imgId;

    private Activity context;

    public ExerciseList(Activity context, String[] arrayExercise, Integer[] imgId) {
        super(context, R.layout.activity_main_interface, arrayExercise);
        this.context = context;
        this.arrayExercise = arrayExercise;
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
