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
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.themechanic.arc_project.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static android.view.View.GONE;
public class ARC_NavigationActivity extends AppCompatActivity implements ARC_FragmentDrawer.FragmentDrawerListener
{
    private Toolbar mToolbar;
    private ARC_FragmentDrawer drawerFragment;
    View  notifCount;
    ImageView toolimage;
    boolean ishavingdownloadableonly=false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);


        toolimage= (ImageView) findViewById(R.id.toolimage);
        mToolbar = (Toolbar) findViewById(R.id.MageNative_toolbar);
         mToolbar.setNavigationIcon(R.drawable.ham);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getAttributes().flags &= (~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

       // mToolbar.setLogo(getResources().getDrawable(R.drawable.mage));
       // mToolbar.setNavigationIcon(R.drawable.ham);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.MageNative_drawer_layout);
        Field mDragger = null;//mRightDragger for right obviously
        try {
            mDragger = mDrawerLayout.getClass().getDeclaredField("mLeftDragger");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        mDragger.setAccessible(true);
        ViewDragHelper draggerObj = null;
        try {
            draggerObj = (ViewDragHelper) mDragger.get(mDrawerLayout);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field mEdgeSize = null;
        try {
            mEdgeSize = draggerObj.getClass().getDeclaredField("mEdgeSize");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        mEdgeSize.setAccessible(true);
        int edge = 0;
        try {
            edge = mEdgeSize.getInt(draggerObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            mEdgeSize.setInt(draggerObj, edge * 2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
       // View logoView = getToolbarLogoIcon(mToolbar);
        toolimage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {


                }
                catch (Exception t)
                {
                    t.printStackTrace();
                }
            }
        });

        drawerFragment = (ARC_FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.MageNative_fragment_navigation_drawer);
        drawerFragment.setUp(R.id.MageNative_fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.MageNative_drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

    }

    public static View getToolbarLogoIcon(Toolbar toolbar){
        //check if contentDescription previously was set
        boolean hadContentDescription = android.text.TextUtils.isEmpty(toolbar.getLogoDescription());
        String contentDescription = String.valueOf(!hadContentDescription ? toolbar.getLogoDescription() : "logoContentDescription");
        toolbar.setLogoDescription(contentDescription);
        ArrayList<View> potentialViews = new ArrayList<View>();
        //find the view based on it's content description, set programatically or with android:contentDescription
        toolbar.findViewsWithText(potentialViews,contentDescription, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        //Nav icon is always instantiated at this point because calling setLogoDescription ensures its existence
        View logoIcon = null;
        if(potentialViews.size() > 0){
            logoIcon = potentialViews.get(0);
        }
        //Clear content description if not previously present
        if(hadContentDescription)
            toolbar.setLogoDescription(null);
        return logoIcon;
    }

    /*public void show()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                andro_aaka.setVisibility(GONE);
            }
        }, 5000);
    }*/
    @Override
    protected void onResume()
    {
        super.onResume();
      //  mConnectionClassManager.register(mListener);
       
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.magenative_menu_main, menu);
        MenuItem item = menu.findItem(R.id.MageNative_action_cart);
        MenuItemCompat.setActionView(item, R.layout.magenative_feed_update_count);
        notifCount = (View) MenuItemCompat.getActionView(item);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        return true;
    }
    @Override
    public void onDrawerItemSelected(View view, int position)
    {

    }

    public void openDrawer()
    {
        drawerFragment.open();
    }

}