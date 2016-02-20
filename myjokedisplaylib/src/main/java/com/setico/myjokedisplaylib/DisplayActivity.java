package com.setico.myjokedisplaylib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    private TextView jokeTextView;
    private static final String JOKE_KEY="joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String joke = null;
        if(getIntent().hasExtra(JOKE_KEY))
            joke = getIntent().getStringExtra(JOKE_KEY);
        jokeTextView = (TextView) findViewById(R.id.joke_textview);
        if(joke!=null)
            jokeTextView.setText(joke);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
