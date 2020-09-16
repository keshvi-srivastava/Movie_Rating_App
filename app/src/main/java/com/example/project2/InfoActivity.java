package com.example.project2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

// Activity to display the movie information through context menu
public class InfoActivity extends Activity {

    protected ImageView movie_image ;
    protected TextView year ;
    protected TextView duration ;
    protected TextView director ;
    protected TextView stars ;
    protected TextView rotten_rating ;
    protected TextView imdb_rating ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();

        movie_image = (ImageView) findViewById(R.id.movie_image);
        year = (TextView) findViewById(R.id.year);
        duration = (TextView) findViewById(R.id.duration);
        director = (TextView) findViewById(R.id.director);
        stars = (TextView) findViewById(R.id.stars);
        rotten_rating = (TextView) findViewById(R.id.rotten_rating);
        imdb_rating = (TextView) findViewById(R.id.imdb_rating);

        // Get the ID of the image and position of the item in list for other arrays
        final int id = intent.getIntExtra(MainActivity.EXTRA_RES_ID, 0);
        final int position = intent.getIntExtra(MainActivity.EXTRA_RES_POS, 0);

        movie_image.setImageResource(id);
        year.setText("Year Released: " + getResources().getStringArray(R.array.date_released)[position]);
        duration.setText("Duration: " +getResources().getStringArray(R.array.duration)[position]);
        director.setText("Director: " + getResources().getStringArray(R.array.director)[position]);
        stars.setText("Stars: " + getResources().getStringArray(R.array.stars)[position]);
        rotten_rating.setText(getResources().getStringArray(R.array.rotten_rating)[position] + "%");
        imdb_rating.setText(getResources().getStringArray(R.array.imdb_rating)[position] + "/10");

    }
}
