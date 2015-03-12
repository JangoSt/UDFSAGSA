//package com.wirundich.kalorienrechner.NavigationDrawer;
//
//
//import android.app.Activity;
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.content.Context;
//
//import android.content.res.Configuration;
//import android.support.v4.app.ActionBarDrawerToggle;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//
//import android.os.Bundle;
//
//import android.util.AttributeSet;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import com.wirundich.kalorienrechner.R;
//import com.wirundich.kalorienrechner.dataclasses.DataBus;
//import com.wirundich.kalorienrechner.fragments.AddItem;
//import com.wirundich.kalorienrechner.fragments.DayView;
//import com.wirundich.kalorienrechner.fragments.ListExpandableItems;
//
//import java.util.ArrayList;
//
//public class NavigationDraw extends Activity {
//    private DrawerLayout mDrawerLayout;
//    private ListView mDrawerList;
//    private DataBus db;
//    private String[] mTitles;
//    private ActionBarDrawerToggle mDrawerToggle;
//    private CharSequence mDrawerTitle;
//    private CharSequence mTitle;
//
//    ArrayList<Fragment> fragments = new ArrayList<Fragment>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        db = (DataBus)getApplicationContext();
//        setContentView(R.layout.drawer_layout);
//        mTitle = mDrawerTitle = getTitle();
//        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//        mDrawerList = (ListView)findViewById(R.id.left_drawer);
//        fragments.add(new DayView());
//        fragments.add(new AddItem());
//        fragments.add(new ListExpandableItems());
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
//        mDrawerToggle = new ActionBarDrawerToggle(
//                this,
//                mDrawerLayout,
//                R.drawable.ic_drawer,
//                R.string.drawer_open,
//                R.string.drawer_closed){
//            public void onDrawerClosed(View view) {
//                getActionBar().setTitle(mTitle);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//            }
//
//            public void onDrawerOpened(View drawerView) {
//                getActionBar().setTitle(mDrawerTitle);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//            }
//        };
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//
//        mTitles = new String[fragments.size()];
//        for(int i = 0; i<fragments.size();i++){
//            mTitles[i]= fragments.get(i).toString();
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item,mTitles);
//
//
//
//        mDrawerList.setAdapter(adapter);
//        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
//        getFragmentManager().beginTransaction().replace(R.id.pager, fragments.get(0)).commit();
//        mDrawerList.setItemChecked(0,true);
//    }
//    //COPYPASTE start
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.menu_main, menu);
////        return super.onCreateOptionsMenu(menu);
////    }
//
//    /* Called whenever we call invalidateOptionsMenu() */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//         // The action bar home/up action should open or close the drawer.
//         // ActionBarDrawerToggle will take care of this.
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        // Handle action buttons
//        switch(item.getItemId()) {
//        default:
//            return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
//    }
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Pass any configuration change to the drawer toggls
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }
//    //COPYPASTE end
//
//    @Override
//    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//
//        return super.onCreateView(parent, name, context, attrs);
//    }
//
//
//    private class DrawerItemClickListener implements ListView.OnItemClickListener {
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            FragmentManager fragmentManager = getFragmentManager();
//            fragments.get(position).onResume();
//            fragmentManager.beginTransaction().replace(R.id.pager, fragments.get(position)).commit();
//            mDrawerLayout.closeDrawer(mDrawerList);
//        }
//    }
//}
//
