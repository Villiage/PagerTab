package com.cyd.pagertab;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ViewPager pager;

    int[] colors = {android.R.color.holo_blue_light, android.R.color.holo_orange_light, android.R.color.holo_red_light, android.R.color.holo_green_light};
    List<View> views = new ArrayList<View>();
    TabLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (TabLayout)findViewById(R.id.container);
        getViews();
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
         pager.setOnPageChangeListener(opcl);

    }
    PagerAdapter adapter = new PagerAdapter() {

        @Override
        public int getCount() {
            return colors.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(views.get(position));

            return views.get(position);

        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    };
    ViewPager.OnPageChangeListener opcl = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            container.setProgress(position, positionOffset);
        }

        @Override
        public void onPageSelected(int position) {

            container.setPosition(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    public void getViews() {
        for (int i = 0; i < colors.length; i++) {
            View v = new View(this);

            v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            v.setBackgroundColor(getResources().getColor(colors[i]));
            views.add(v);
        }


    }
}
