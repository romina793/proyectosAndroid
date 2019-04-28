package com.example.digital.viewpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentColor extends Fragment {


    public static final String CLAVE_COLOR = "claveColor";

    public static FragmentColor makeFRagmentColor (Integer color){
        FragmentColor fragmentColor = new FragmentColor();

        Bundle bundle = new Bundle();
        bundle.putInt(CLAVE_COLOR, color);

        fragmentColor.setArguments(bundle);

        return fragmentColor;
    }


    public FragmentColor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaDelFragment = inflater.inflate(R.layout.fragment_fragment_color, container, false);


        Bundle bundle = getArguments();
        Integer color= bundle.getInt(CLAVE_COLOR);

        vistaDelFragment.setBackgroundColor(color);


        return vistaDelFragment;
    }

}
