package com.example.themechanic.arc_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.themechanic.arc_project.ARC_Navigation_Drawer_Activity.ARC_NavigationActivity;
import com.example.themechanic.arc_project.ARC_Navigation_Drawer_Activity.ARC_Registration_Activity;

public class ARC_Login_Activity extends ARC_NavigationActivity
{

    TextView link_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup content = (ViewGroup) findViewById(R.id.MageNative_frame_container);
        getLayoutInflater().inflate(R.layout.activity_arc__login_, content, true);
        getSupportActionBar().hide();

        link_signup = (TextView)findViewById(R.id.link_signup);

        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent register = new Intent(ARC_Login_Activity.this, ARC_Registration_Activity.class);
                startActivity(register);
            }
        });
    }
}
