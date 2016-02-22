package comp3350.assignment.riseandshine.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import comp3350.assignment.riseandshine.controllers.AlarmController;
import comp3350.assignment.riseandshine.fragments.*;
import comp3350.assignment.riseandshine.R;

public class Home extends AppCompatActivity {

    static int currentMode = 0;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(Home.this, CreateAlarmActivity.class);
                startActivity(intent);
                //For adding animation to activity transition
                overridePendingTransition(R.anim.abc_fade_in, 100000);
            }
        });

        //init alarmList and fill with test data
        AlarmController alarmController = AlarmController.sharedController();
        AlarmController.populateAlarms();

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);

        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new AlarmsFragment();
                case 1:
                    return new TasksFragment();
                case 2:
                    return new SettingsFragment();
            }
            return null;

        }

        @Override
        public int getCount() {

            return 3;

        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return AlarmsFragment.TAB_TITLE;
                case 1:
                    return TasksFragment.TAB_TITLE;
                case 2:
                    return SettingsFragment.TAB_TITLE;
            }
            return null;

        }

    }

}
