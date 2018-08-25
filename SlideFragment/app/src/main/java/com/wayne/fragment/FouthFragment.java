package com.wayne.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.wayne.com.slidefragment.R;

public class FouthFragment extends Fragment {
    public FouthFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.fragment_me, container, false);
    }


}
