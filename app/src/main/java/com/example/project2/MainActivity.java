package com.example.project2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    protected static final String EXTRA_RES_ID = "ID";
    protected static final String EXTRA_RES_POS = "POS";

    // Custom adapter
    ContentAdapter adapter;

    // Array list of movie images
    private ArrayList<Integer> mThumbIdsMovies = new ArrayList<Integer>(
            Arrays.asList(R.drawable.revenant, R.drawable.logan, R.drawable.drishyam,
                    R.drawable.gifted, R.drawable.star_is_born, R.drawable.andhadhun,
                    R.drawable.me_before_you, R.drawable.martian,
                    R.drawable.dogs_purpose, R.drawable.black_panther, R.drawable.snowden,
                    R.drawable.venom));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView lv = (ListView) findViewById(R.id.movie_list);

        adapter = new ContentAdapter(this, mThumbIdsMovies, getResources().getStringArray(R.array.movies),
                getResources().getStringArray(R.array.date_released));

        lv.setAdapter(adapter);

        // On click listener for when item inlist clicked
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                //Create an Intent to start the ImageViewActivity
                Intent intent = new Intent(MainActivity.this, ViewImage.class);

                // Add the ID of the thumbnail and position of list item to display as an Intent Extra
                intent.putExtra(EXTRA_RES_ID, (int) id);
                intent.putExtra(EXTRA_RES_POS, position);

                // Start the ViewImage Activity
                startActivity(intent);

            }
        });

        // Register for the long click context menu
        registerForContextMenu(lv);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo mAdapter = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        super.onContextItemSelected(item);
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.movie_info:
                // Send intent to open InfoActivity
                intent.setClass(MainActivity.this, InfoActivity.class);
                intent.putExtra(EXTRA_RES_POS, (int) mAdapter.position);
                intent.putExtra(EXTRA_RES_ID, (int) mAdapter.id);

                // Start the InfoActivity
                startActivity(intent);
                return true;

            case R.id.trailer:
                // Send intent to open youtube link
                Uri trailer = Uri.parse(getResources().getStringArray(
                        R.array.youtube_url)[mAdapter.position]);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(trailer);
                startActivity(intent);
                return true;

            case R.id.wiki:
                // Send intent to open director wikipedia link
                Uri wikipedia = Uri.parse(getResources().getStringArray(
                        R.array.wiki_url)[mAdapter.position]);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(wikipedia);
                startActivity(intent);
                return true;

            case R.id.imdb:
                // Send intent to open imdb link
                Uri imdb = Uri.parse(getResources().getStringArray(
                        R.array.imdb_url)[mAdapter.position]);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(imdb);
                startActivity(intent);
                return true;

            default:
                return false;
        }

    }

    // Explicit garbage collection on returning to the activity
    @Override
    public void onResume(){
        super.onResume();
        System.gc();
    }

}
