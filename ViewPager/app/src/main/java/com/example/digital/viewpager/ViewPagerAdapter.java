package com.example.digital.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> listaDeFragments;

    public ViewPagerAdapter(FragmentManager fm,List<Fragment> listaDeFragments) {
        super(fm);
        this.listaDeFragments = listaDeFragments;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragmentAMostrar = listaDeFragments.get(position);

        return fragmentAMostrar;
    }

    @Override
    public int getCount() {
        return listaDeFragments.size();
    }
}
