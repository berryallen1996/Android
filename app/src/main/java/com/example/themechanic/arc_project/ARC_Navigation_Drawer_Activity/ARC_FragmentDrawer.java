/*
 * Copyright/**
 *          * CedCommerce
 *           *
 *           * NOTICE OF LICENSE
 *           *
 *           * This source file is subject to the End User License Agreement (EULA)
 *           * that is bundled with this package in the file LICENSE.txt.
 *           * It is also available through the world-wide-web at this URL:
 *           * http://cedcommerce.com/license-agreement.txt
 *           *
 *           * @category  Ced
 *           * @package   MageNative
 *           * @author    CedCommerce Core Team <connect@cedcommerce.com >
 *           * @copyright Copyright CEDCOMMERCE (http://cedcommerce.com/)
 *           * @license      http://cedcommerce.com/license-agreement.txt
 *
 */
package com.example.themechanic.arc_project.ARC_Navigation_Drawer_Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themechanic.arc_project.ARC_Login_Activity;
import com.example.themechanic.arc_project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ARC_FragmentDrawer extends Fragment
{
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    private FragmentDrawerListener drawerListener;

    View header;
    LinearLayout cmpspages;
    RelativeLayout logoutsection;
    String theme="";
    int accessresult;
    public ARC_FragmentDrawer()
    {}
    public void setDrawerListener(FragmentDrawerListener listener)
    {
        this.drawerListener = listener;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        accessresult = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = null;
        layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        logoutsection=(RelativeLayout)layout.findViewById(R.id.logoutsection);




        /*************************************Drawer Clicks**********************************************/
        logoutsection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent login = new Intent(getActivity(), ARC_Login_Activity.class);
                startActivity(login);
            }
        });

        /*************************************Drawer Clicks**********************************************/


        return layout;
    }
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar)
    {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();

            }
            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                super.onDrawerSlide(drawerView, slideOffset);

            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                mDrawerToggle.syncState();
            }
        });
    }

    public static interface ClickListener
    {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }
    public interface FragmentDrawerListener
    {
        public void onDrawerItemSelected(View view, int position);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            /*case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Intent searchscanner = new Intent(getActivity(), Ced_Scanner.class);
                    searchscanner.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(searchscanner);
                    getActivity().overridePendingTransition(R.anim.magenative_slide_in, R.anim.magenative_slide_out);
                    mDrawerLayout.closeDrawers();
                }
                else
                {

                    Toast.makeText(getActivity(), ""+getResources().getString(R.string.permissiondenied), Toast.LENGTH_LONG).show();
                }
                break;*/
        }
    }
    public void open()
    {
        mDrawerLayout.openDrawer(containerView);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        accessresult = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
       // theme=cedSessionManagement.getdrawertheme();
    }
    public static void expand(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
}