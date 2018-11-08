package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.jokeprovider.JokeCreator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {


    @ApiMethod(name = "getJokes")
    public List<Joke> getJokes()
    {
        JokeCreator jokeCreator = new JokeCreator();
        List<Joke> jokeList = new ArrayList<>();
        List<String> jokeStrings = jokeCreator.getJokes();
        for (int i = 0; i < jokeStrings.size(); i++)
        {
            Joke joke = new Joke();
            joke.setJokeText(jokeStrings.get(i));
            jokeList.add(joke);
        }
        return jokeList;
    }

}
