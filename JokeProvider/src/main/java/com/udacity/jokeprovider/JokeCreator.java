package com.udacity.jokeprovider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class JokeCreator {

    public List<String> getJokes()
    {

        ResourceBundle jokeBundle2 = ResourceBundle.getBundle("jokes");
        List<String> keys = Collections.list(jokeBundle2.getKeys());
        Collections.sort(keys);
        ArrayList<String> resList= new ArrayList<>();

        for (int i=0; i < keys.size(); i++)
        {
            resList.add(jokeBundle2.getString(keys.get(i)));
        }


        return resList;
    }

}
