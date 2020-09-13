package com.example.android.popularnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;


import com.example.android.popularnews.Fragment.HomeFragment;
import com.example.android.popularnews.Fragment.NotificationFragment;
import com.example.android.popularnews.Fragment.SettingFragment;
import com.example.android.popularnews.Fragment.UserFragment;
import com.example.android.popularnews.Fragment.VideoFragment;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    final int HOME = 0;
    final int VIDEO = 1;
    final int NOTIFICATION = 2;
    final int USER = 3;
    final int SETTING = 4;

    //
    int currentPagerFragment = 0;
    Fragment homeFragment = null, videoFragment = null, notificationFragment = null, userFragment = null, settingFragment = null;
    private boolean doubleBackToExitPressedOnce = false;
    //
    // view
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();

    }


    void setupUI() {
        //fragment
        homeFragment = new HomeFragment();
        videoFragment = new VideoFragment();
        notificationFragment = new NotificationFragment();
        userFragment = new UserFragment();
        settingFragment = new SettingFragment();
        //tablayout
        tabLayout = findViewById(R.id.home_tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // defaur
        selectTab(currentPagerFragment);
    }

    void selectTab(int tab) {
        currentPagerFragment = tab;
        switch (currentPagerFragment) {
            case HOME:
                homeFragment = new HomeFragment();
                loadFragment(homeFragment);
                break;
            case VIDEO:
                videoFragment = new VideoFragment();
                loadFragment(videoFragment);
                break;
            case NOTIFICATION:
                loadFragment(notificationFragment);
                break;
            case USER:
                loadFragment(userFragment);
                break;
            case SETTING:
                loadFragment(settingFragment);
                break;
        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_frame_container, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn Back thêm 1 lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}