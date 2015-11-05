package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.gradle.jokes.Joker;

/**
 * An endpoint class we are exposing
 */
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

    /**
     * A simple endpoint method that pulls a joke from the server
     */
    @ApiMethod(name = "pullJoke")
    public MyBean pullJoke() {

        Joker jokeSource = new Joker();
        String joke = jokeSource.getJoke();

        MyBean response = new MyBean();
        response.setData(joke);

        return response;
    }
}
