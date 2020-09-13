package com.example.android.popularnews.Adapter;

import android.os.Bundle;

import com.example.android.popularnews.Fragment.NewsFragment;
import com.example.android.popularnews.models.Categories;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    public static int CURRENT_POS = 0;
    NewsFragment frag;
    ArrayList<Categories> categories = new ArrayList<>();
    public NewsPagerAdapter(FragmentManager fragmentManager, Categories[] categories){
        super(fragmentManager);
        this.categories.addAll(Arrays.asList(categories));
    }
    @Override
    public Fragment getItem(int position) {
        CURRENT_POS = position;
        frag = new NewsFragment();
        Bundle args = new Bundle();
        args.putString("url", categories.get(position).getUrl());
        frag.setArguments(args);
        return frag;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getTitle();
    }
}