package com.example.themechanic.arc_project.ARC_Navigation_Drawer_Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.themechanic.arc_project.R;

public class ARC_Registration_Activity extends ARC_NavigationActivity
{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup content = (ViewGroup) findViewById(R.id.MageNative_frame_container);
        getLayoutInflater().inflate(R.layout.activity_arc__registration_, content, true);
        getSupportActionBar().hide();
    }
}
