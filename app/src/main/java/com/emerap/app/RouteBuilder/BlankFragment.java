package com.emerap.app.RouteBuilder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emerap.library.RouteBuilder.RouteBuilder;

public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RouteBuilder.Item item = RouteBuilder.getCurrentRoute(getArguments());
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
}
