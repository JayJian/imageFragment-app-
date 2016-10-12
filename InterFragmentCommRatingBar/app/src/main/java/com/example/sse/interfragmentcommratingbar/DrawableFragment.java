package com.example.sse.interfragmentcommratingbar;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawableFragment extends Fragment {

    ArrayList<Drawable> drawables;  //keeping track of our images
    private ImageView imgRateMe;
    private RatingBar imgRatBar;

    private HashMap<Integer,Float> rate;

    private int position;

    public DrawableFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_drawable, container, false);


        View v = inflater.inflate(R.layout.fragment_drawable, container, false);   //MUST HAPPEN FIRST, otherwise components don't exist.

//getting all drawable resources, handy third party see method below.

        getDrawables();

        rate = new HashMap<>();
        int length = drawables.size();
        for(int i=0; i<length; i++){
            rate.put(i,null);
        }

        imgRateMe = (ImageView) v.findViewById(R.id.imgRateMe);
        imgRatBar = (RatingBar) v.findViewById(R.id.ratingBar);



        return v;   //must happen last, it is a return statement after all, it can't happen sooner!
    }


    public void changePicture(int currDrawableIndex) {
        imgRateMe.setImageDrawable(drawables.get(currDrawableIndex));
        position = currDrawableIndex;

        imgRatBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate.put(position,imgRatBar.getRating());

            }
        });

        if(rate.get(position)!=null){
            imgRatBar.setRating(rate.get(position));
        } else {
            imgRatBar.setRating(0);
        }
    }


    //REF: http://stackoverflow.com/questions/31921927/how-to-get-all-drawable-resources

    public void getDrawables() {
        Field[] drawablesFields = com.example.sse.interfragmentcommratingbar.R.drawable.class.getFields();
        drawables = new ArrayList<>();

        String fieldName;
        for (Field field : drawablesFields) {
            try {
                fieldName = field.getName();
                Log.i("LOG_TAG", "com.your.project.R.drawable." + fieldName);
                if (fieldName.startsWith("animals_"))  //only add drawable resources that have our prefix.
                    drawables.add(getResources().getDrawable(field.getInt(null)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}