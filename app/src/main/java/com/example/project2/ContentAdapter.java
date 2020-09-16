package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// Custom adapter for each row in the list
public class ContentAdapter extends BaseAdapter {

    private static final int PADDING = 8;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    private Context mContext;             // This will have to be passed to the LayoutInflater
    private List<Integer> thumbnailIds;   // Thumbnail IDs of all the images
    private String[] movieNames;          // String array for movie names
    private String[] movieYears;          // String array for movie release years

    // Save the list of image IDs, names, years and the context
    // Constructor
    public ContentAdapter(Context c, List<Integer> ids, String[] names, String[] years) {
        mContext = c;
        this.thumbnailIds = ids;
        this.movieNames = names;
        this.movieYears = years;
    }

    // Return the number of items in the Adapter
    @Override
    public int getCount() {
        return (thumbnailIds.size());
    }

    // Return the data item at position
    @Override
    public Object getItem(int position) {
        return (thumbnailIds.get(position));
    }

    // Will get called to provide the ID that
    // is passed to OnItemClickListener.onItemClick()
    @Override
    public long getItemId(int position) {
        return (thumbnailIds.get(position));
    }

    // Return an ViewHolder View for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        final View result;

        // Check to recycle the list item views
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.content_view, parent, false);
            viewHolder.movie = (TextView) convertView.findViewById(R.id.name);
            viewHolder.year = (TextView) convertView.findViewById(R.id.year);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);

            result=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        //Set the movie name, year and image in each row
        viewHolder.movie.setText(movieNames[position]);
        viewHolder.year.setText(movieYears[position]);
        viewHolder.image.setImageResource(thumbnailIds.get(position));

        return convertView;
    }

    // Class to compose of all the different views within a row
    private static class ViewHolder {

        TextView movie;
        TextView year;
        ImageView image;

    }
}


