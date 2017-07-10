package com.example.kfalasiri.mixers;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ////////////////////////////////////////////GLOBALS////////////////////////////////////////////
    private CustomViewPager viewPager;//used for tabbing
    private SectionsPageAdapter sectionPageAdapter;//for tabbing

    ////////////////////////////////////////////On Create//////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ViewPager
        sectionPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (CustomViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);//add the fragments to the viewPager
        viewPager.setPagingEnabled(false);//turn off swiping to move between tabs

        //TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    /////////////////////////////////////////Setting up views//////////////////////////////////////
    //Enable full screen mode
    @Override
    public void onWindowFocusChanged(boolean hasFocas) {
        super.onWindowFocusChanged(hasFocas);
        View decorView = getWindow().getDecorView();
        if(hasFocas) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    private void setupViewPager(CustomViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MixerFragment(), "Mixer");
        adapter.addFragment(new EffectsFragment(), "Effects");
        adapter.addFragment(new SettingsFragment(), "Settings");
        viewPager.setAdapter(adapter);
    }

}
