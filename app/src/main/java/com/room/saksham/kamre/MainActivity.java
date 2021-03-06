package com.room.saksham.kamre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
-     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private HomeSectionsPagerAdapter mHomeSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section cont*/
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager mViewPager;

    //navigation drawer
    private DrawerLayout mDrawer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tracking number of times app is opened by user
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mHomeSectionsPagerAdapter = new HomeSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mHomeSectionsPagerAdapter);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.setDrawerListener(toggle);
        //sync the toggle state
        toggle.syncState();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add new listing", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Toast.makeText(MainActivity.this, "1 of 4", Toast.LENGTH_LONG).show();
                Intent addListingIntent = new Intent(MainActivity.this, AddRoomActivity.class);
                startActivity(addListingIntent);
            }
        });

    }

    private void navToLogin() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        //intentLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentLogin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //get the search view and set the searchable configuration
        /*SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        //assumes the current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));*/

        return true;
    }

    @Override
    public boolean onSearchRequested() {
        //pauseSomeStuff();
        return super.onSearchRequested();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeFragment/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        ParseUser currentUser = ParseUser.getCurrentUser();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_add_listing:
                Intent addListingIntent = new Intent(this, AddRoomActivity.class);
                startActivity(addListingIntent);
                break;
            case R.id.action_sign_in:
                if(currentUser == null){
                    //navigate to login
                    navToLogin();
                }else{
                    //tell user is already signed in
                    Toast.makeText(this, "You are already logged in as" + currentUser.getUsername().toString(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.action_sign_out:
                //log current user out
                //check if there is a user then log her out
                if(currentUser != null){
                    //log em out
                    Toast.makeText(this, "Logging out", Toast.LENGTH_LONG).show();
                    ParseUser.logOut();
                    navToLogin();
                }else{
                    Toast.makeText(this, "Not logged in, please log in", Toast.LENGTH_LONG).show();
                    showLoginDialog();
                }
                break;
            case R.id.action_chat:
                Intent chat = new Intent(this, Home.class);
                startActivity(chat);
                break;
            case R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //switch between the selected items and act accordingly
        int selection = item.getItemId();
        //the fragments to load on selection
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (selection){
            case R.id.nav_browse_rooms:
                //launch rooms fragments
                Toast.makeText(this, "Fetching rooms", Toast.LENGTH_LONG).show();
                fragmentClass = HomeFragment.class;

                break;
            case R.id.nav_my_rooms:
                fragmentClass = MyRoomsFragment.class;
                //
                /*try {

                    //fragment = new MyRoomsFragment();
                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();*/
                //check if logged in
                if(ParseUser.getCurrentUser() == null){
                    //ask to login
                    Toast.makeText(this, "Please login to access your rooms", Toast.LENGTH_LONG).show();
                    //login dialog
                    showLoginDialog();
                }else {
                    //launch my rooms fragment
                    Toast.makeText(this, "My rooms", Toast.LENGTH_LONG).show();
                    //direct to users' rooms
                    Intent myRoomsIntent = new Intent(this, MyRoomsActivity.class);
                    startActivity(myRoomsIntent);
                }
                break;
            case R.id.nav_share:
                //share the app
                Toast.makeText(this, "Sharing the app", Toast.LENGTH_LONG).show();

                Intent shareAppIntent = new Intent();
                shareAppIntent.setAction(Intent.ACTION_SEND);
                shareAppIntent.putExtra(Intent.EXTRA_TEXT, "Don't forget to share our Rooms app.\nYou can download the app from here:-\nhttps://drive.google.com/file/d/1--qwD3lfnBl3bsHVL5Ma81S7VMhhOQ3l/view?usp=drivesdk");
                shareAppIntent.setType("text/plain");
                Intent chooser = Intent.createChooser(shareAppIntent, "Share App Via:");
                //verify whether there is an app capable of handling the intent
                if (shareAppIntent.resolveActivity(getPackageManager()) != null){
                   startActivity(chooser);
                }
                break;
            case R.id.nav_feedback:
                //launch contact us activity/fragment
                Toast.makeText(this, "Give us a shout", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_contact_us:
                //launch contact us
                Toast.makeText(this, "Contact us", Toast.LENGTH_LONG).show();
                Intent aboutIntent = new Intent(this, ContactUsActivity.class);
                startActivity(aboutIntent);
                break;
        }

        //highlight the selected item
        //item.setChecked(true);

        //close the drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showLoginDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
        loginDialogFragment.show(fragmentManager, "fragment_alert");
    }
}
