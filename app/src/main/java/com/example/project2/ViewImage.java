package com.example.project2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

// Activity to display the high definition image on new screen
public class ViewImage extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        // Make a new ImageView
        ImageView iv = new ImageView(getApplicationContext());

        iv.setPadding(15, 10, 15, 10);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);

        // Get the ID of the image and position of list item to display
        final int id = intent.getIntExtra(MainActivity.EXTRA_RES_ID, 0);
        final int position = intent.getIntExtra(MainActivity.EXTRA_RES_POS, 0);

        // Set image in ImageView
        iv.setImageResource(id);

        setContentView(iv);

        // On click listener to take user to imdb page on clicking anywhere on the image
        iv.setOnClickListener(new View.OnClickListener(){


            public void onClick(View view) {

                Intent intent2 = new Intent();
                Uri wikipedia = Uri.parse(getResources().getStringArray(R.array.imdb_url)[position]);
                intent2.setAction(Intent.ACTION_VIEW);
                intent2.setData(wikipedia);
                startActivity(intent2);

            }});
    }
}

