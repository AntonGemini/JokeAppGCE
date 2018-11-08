package com.udacity.jokepresenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sassa_000 on 11.03.2018.
 */

public class JokeAdapter extends ArrayAdapter<String> {

    List<String> jokes;
    Context context;

    public JokeAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, R.layout.joke_list_item, objects);
        jokes = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.joke_list_item,parent,false);
        TextView jokeTextView =  view.findViewById(R.id.tv_jokeitem);
        jokeTextView.setText(jokes.get(position));
        return view;
    }


}
