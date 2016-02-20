package com.setico.builditbigger.backend;

import com.jokelib.MyJokeData;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.setico.builditbigger.backend.model.MyJoke;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myJokeApi",
        version = "v1",
        resource = "myJoke",
        namespace = @ApiNamespace(
                ownerDomain = "com.setico.builditbigger",
                ownerName = "com.setico.builditbigger",
                packagePath = ""
        )
)
public class MyJokeEndpoint {

    private static final Logger logger = Logger.getLogger(MyJokeEndpoint.class.getName());

    @ApiMethod(name = "getMyJoke")
    public MyJoke getMyJoke() {
        // TODO: Implement this function
        MyJokeData myJokeData = new MyJokeData(10);
        logger.info("Calling getMyJoke method");
        return new MyJoke(myJokeData.getRandomJoke());
    }

//    /**
//     * This inserts a new <code>MyJoke</code> object.
//     *
//     * @param myJoke The object to be added.
//     * @return The object to be added.
//     */
//    @ApiMethod(name = "insertMyJoke")
//    public MyJoke insertMyJoke(MyJoke myJoke) {
//        // TODO: Implement this function
//        logger.info("Calling insertMyJoke method");
//        return myJoke;
//    }
}