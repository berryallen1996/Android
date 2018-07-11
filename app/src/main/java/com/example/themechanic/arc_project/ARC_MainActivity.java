package com.example.themechanic.arc_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.themechanic.arc_project.ARC_Navigation_Drawer_Activity.ARC_NavigationActivity;

public class ARC_MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc__main);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent in = new Intent(ARC_MainActivity.this,ARC_HomePage.class);
                startActivity(in);
            }
        }, 2000);

    }
}
