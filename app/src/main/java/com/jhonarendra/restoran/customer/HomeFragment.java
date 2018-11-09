package com.jhonarendra.restoran.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Jhonarendra on 11/7/2018.
 */

public class HomeFragment extends Fragment {
    protected LinearLayout llBurger;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        llBurger = view.findViewById(R.id.ll_burger);
        llBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MenuBurgerActivity.class);
                startActivity(i);
            }
        });
        return view;

    }
}
