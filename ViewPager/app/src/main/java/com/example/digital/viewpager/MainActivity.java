package com.example.digital.viewpager;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.main_viewPager);

        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager(), cargarFragments());
        viewPager.setCurrentItem(2);
        viewPager.setAdapter(adapter);

    }

    private List<Fragment> cargarFragments (){
        List<Fragment> listaDeFragment = new ArrayList<>();

        listaDeFragment.add(FragmentColor.makeFRagmentColor(Color.RED));
        listaDeFragment.add(FragmentColor.makeFRagmentColor(Color.BLACK));
        listaDeFragment.add(FragmentColor.makeFRagmentColor(Color.CYAN));
        listaDeFragment.add(FragmentColor.makeFRagmentColor(Color.GREEN));
        listaDeFragment.add(FragmentColor.makeFRagmentColor(Color.GRAY));
        listaDeFragment.add(FragmentColor.makeFRagmentColor(Color.YELLOW));


        return listaDeFragment;

    }

}
