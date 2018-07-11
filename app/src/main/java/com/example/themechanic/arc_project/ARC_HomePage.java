package com.example.themechanic.arc_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.themechanic.arc_project.ARC_Navigation_Drawer_Activity.ARC_NavigationActivity;

public class ARC_HomePage extends ARC_NavigationActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup content = (ViewGroup) findViewById(R.id.MageNative_frame_container);
        getLayoutInflater().inflate(R.layout.activity_arc__home_page, content, true);


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
