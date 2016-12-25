package com.emerap.library.RouteBuilder;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by karbunkul on 25.12.16.
 */

abstract public class RouteBuilderActivity extends AppCompatActivity implements RouteBuilder.Init {

    public static RouteBuilder getRouteBuilder(RouteBuilderActivity activity) {
        return activity.onRouteSetup();
    }
}
