package com.example.sse.interfragmentcommratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ButtonFragment.ButtonFragmentLister{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int currDrawableIndex) {
        DrawableFragment receiveFragment = (DrawableFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        receiveFragment.changePicture(currDrawableIndex);
    }
}
