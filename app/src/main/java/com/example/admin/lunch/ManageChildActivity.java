package com.example.admin.lunch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.lunch.fragment.ManageChildFragment;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

public class ManageChildActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ManageChildActivity.ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_child);

        SpringDotsIndicator dotsIndicator = (SpringDotsIndicator) findViewById(R.id.dots_indicator);
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new ManageChildActivity.ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return ManageChildFragment.newInstance("", "");
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
