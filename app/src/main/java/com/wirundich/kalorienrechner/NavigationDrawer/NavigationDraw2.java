package com.wirundich.kalorienrechner.NavigationDrawer;


import android.app.ActionBar;
import android.app.Activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wirundich.kalorienrechner.R;
import com.wirundich.kalorienrechner.dataclasses.DataBus;
import com.wirundich.kalorienrechner.dataclasses.OnItemChangedListener;
import com.wirundich.kalorienrechner.fragments.AddItem;
import com.wirundich.kalorienrechner.fragments.DayView;
import com.wirundich.kalorienrechner.fragments.DayViewOverview;
import com.wirundich.kalorienrechner.fragments.ListExpandableItems;
import com.wirundich.kalorienrechner.fragments.UserOverview;

import java.util.ArrayList;

public class NavigationDraw2 extends FragmentActivity implements OnItemChangedListener,UserOverview.OnFragmentInteractionListener {
    private DrawerLayout mDrawerLayout;
    ViewPager pager;
    private ListView mDrawerList;
    private DataBus db;
    private String[] mTitles;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = (DataBus)getApplicationContext();
        setContentView(R.layout.drawer_layout);
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.END);
        mDrawerList = (ListView)findViewById(R.id.left_drawer);
        fragments.add(new DayView());
        fragments.add(new AddItem());
        fragments.add(new ListExpandableItems());
//        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getActionBar().setCustomView(R.layout.actionbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_closed){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        mTitles = new String[fragments.size()];

        for(int i = 0; i<fragments.size();i++){
            mTitles[i]= fragments.get(i).toString();
        }
        mTitles[0]=getString(R.string.DayViewName);
        mTitles[1]=getString(R.string.AddItemName);
        mTitles[2]=getString(R.string.ListExpandableItemsName);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item,mTitles);



        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragments.get(0)).commit();
        mDrawerList.setItemChecked(0,true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onTrimMemory(TRIM_MEMORY_COMPLETE);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    //COPYPASTE end

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void onItemAdded() {
        DataBus db = (DataBus) getApplicationContext();
        DayViewOverview dayViewOverview = (DayViewOverview) getSupportFragmentManager().findFragmentById(R.id.vpPager);
        if(dayViewOverview != null){
            dayViewOverview.updateView(db.getActDay(), db.getUser());
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentManager fragmentManager = getFragmentManager();
            fragments.get(position).onResume();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragments.get(position)).commit();
            getActionBar().setTitle(mTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

}

