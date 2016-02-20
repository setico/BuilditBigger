package com.setico.builditbigger;

import android.os.AsyncTask;
import android.test.AndroidTestCase;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.setico.builditbigger.backend.model.myJokeApi.MyJokeApi;

import java.io.IOException;

/**
 * Created by setico on 17/02/2016.
 */
public class JokeTest extends AndroidTestCase {


    public void testGetJoke () throws Throwable {

        // test if there is internet connection
        assertTrue("No internet connection.", Utils.isNetworkAvailable(getContext()));

        new AsyncTask<Void, Void, String>() {
            private MyJokeApi myJokeApiService = null;

            @Override
            protected String doInBackground(Void... params) {
                if (myJokeApiService == null) {
                    MyJokeApi.Builder builder = new MyJokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            .setRootUrl(Utils.BACKEND_URL)
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });

                    myJokeApiService = builder.build();
                }

                try {
                    return myJokeApiService.getMyJoke().execute().getJoke();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                assertNotNull("No Joke", result);
            }
        }.execute();



    }


}
