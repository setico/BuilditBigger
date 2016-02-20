package com.setico.builditbigger;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.setico.myjokedisplaylib.DisplayActivity;

import java.io.IOException;

import builditbigger.setico.com.myJokeApi.MyJokeApi;

public class MainActivity extends AppCompatActivity {
    private Button btnGetJoke;
    private Admob admob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admob = new Admob(this);

        btnGetJoke = (Button) findViewById(R.id.btn_getjoke);
        btnGetJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJoke();
            }
        });
    }

    private void showJoke() {

        if(Utils.isNetworkAvailable(this)){
            getJoke();


        }else {
            Toast.makeText(this, getString(R.string.error_internet), Toast.LENGTH_SHORT).show();

        }

    }

    private void getJoke(){
            new AsyncTask<Void, Void, String>() {
                private MyJokeApi myJokeApiService = null;
                private AlertDialog.Builder builder;
                public AlertDialog alertDialog;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    builder = new AlertDialog.Builder(MainActivity.this);
                    alertDialog = builder.setMessage(R.string.loading).setCancelable(false).create();
                    alertDialog.show();
                }

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
                    alertDialog.dismiss();
                    admob.showAd();
                    if(!result.isEmpty()) {
                        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                        intent.putExtra(Utils.JOKE_KEY, result);
                        startActivity(intent);
                    }else
                        Toast.makeText(MainActivity.this, getString(R.string.error_internet), Toast.LENGTH_SHORT).show();

                }
            }.execute();

    }

}
