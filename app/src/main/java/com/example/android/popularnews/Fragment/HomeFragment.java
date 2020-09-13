package com.example.android.popularnews.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.popularnews.Adapter.ArticleAdapter;
import com.example.android.popularnews.Adapter.NewsPagerAdapter;
import com.example.android.popularnews.MainActivity;
import com.example.android.popularnews.R;
import com.example.android.popularnews.Utils.ConstantAPI;
import com.google.android.material.tabs.TabLayout;



public class HomeFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager pager;
    private MainActivity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            this.context=(MainActivity) context;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pager = (ViewPager) view.findViewById(R.id.home_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.home_tablayout);
        FragmentManager manager = context.getSupportFragmentManager();
        NewsPagerAdapter adapter = new NewsPagerAdapter(manager, ConstantAPI.BASE_CATEGORIES);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
    }
}