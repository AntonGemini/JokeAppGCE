package com.udacity.gradle.builditbigger.backend;

/**
 * Created by ASassa on 05.03.2018.
 */

public class Joke {

    private String jokeText;

    public String getJokeText()
    {
        return jokeText;
    }

    public void setJokeText(String data)
    {
        this.jokeText = data;
    }

    public String toString()
    {
        return jokeText;
    }
}
