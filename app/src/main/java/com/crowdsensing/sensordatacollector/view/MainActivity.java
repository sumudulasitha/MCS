package com.crowdsensing.sensordatacollector.view;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.crowdsensing.sensordatacollector.R;
import com.crowdsensing.sensordatacollector.data.Project;
import com.crowdsensing.sensordatacollector.view.allprojects.AllProjectListFragment;
import com.crowdsensing.sensordatacollector.view.myprojects.MyProjectListFragment;
import com.crowdsensing.sensordatacollector.view.subscribedprojects.SubscribedProjectListFragment;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;

public class MainActivity extends AppCompatActivity implements SubscribedProjectListFragment.OnListFragmentInteractionListener,
AllProjectListFragment.OnListFragmentInteractionListener, MyProjectListFragment.OnListFragmentInteractionListener{

    private DrawerLayout mDrawerLayout;
    private ShakeDetector.ShakeListener shakeListener;
//    private Proxymity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Sensor Data Collector");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        createFragment(SubscribedProjectListFragment.newInstance(1));

        testActions();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        int id = item.getItemId();
                        switch(id) {
                            case R.id.nav_subscribed_projects:
                                createFragment(SubscribedProjectListFragment.newInstance(1));
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.nav_my_projects:
                                createFragment(MyProjectListFragment.newInstance(1));
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.nav_all_projects:
                                createFragment(AllProjectListFragment.newInstance(1));
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.nav_preferences:
                                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                return true;
                        }
                        return true;
                    }
                });
    }

    private void testActions(){
        Sensey.getInstance().init(this);

        shakeListener = new ShakeDetector.ShakeListener() {

            @Override public void onShakeDetected() {
                // Shake detected, do something
                Log.d("Sensey", "Shaking....");
            }

            @Override public void onShakeStopped() {
                // Shake stopped, do something
                Log.d("Sensey", "Shaking stopped....");
            }
        };


        Sensey.getInstance().startShakeDetection(shakeListener);
    }

    private void createFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment).addToBackStack(null).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Sensey.getInstance().stopShakeDetection(shakeListener);
    }

    @Override
    public void onListFragmentInteraction(Project project) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Sensey.getInstance().stop();
    }
}
