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

public class ExerciseAdapter extends BaseAdapter {

    private ArrayList<Exercise> exerciseList;
    private Context context;

    public ExerciseAdapter(Context context, ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return exerciseList.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Exercise exercise = (Exercise) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.exercise, null);

        ImageView imgId = convertView.findViewById(R.id.imgId);
        TextView txtExercise = convertView.findViewById(R.id.exerciseName);
        TextView txtCalories = convertView.findViewById(R.id.calories);
        TextView txtCaloriesUnit = convertView.findViewById(R.id.caloriesUnit);

        imgId.setImageResource(exercise.getImgId());
        txtExercise.setText(exercise.getExerciseName());
        txtCalories.setText(((Integer) exercise.getCalories()).toString());


        txtExercise.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/futura-pt-demibold.otf"));
        txtCalories.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/futura-pt-heavy-oblique.otf"));
        txtCaloriesUnit.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/Futura Book.ttf"));

        return convertView;
    }
}
