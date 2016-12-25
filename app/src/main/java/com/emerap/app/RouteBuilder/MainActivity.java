package com.emerap.app.RouteBuilder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.emerap.library.RouteBuilder.RouteBuilder;
import com.emerap.library.RouteBuilder.RouteBuilderActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends RouteBuilderActivity implements Drawer.OnDrawerItemClickListener {

    private static final int ROUTE_TASK = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) setSupportActionBar(toolbar);

        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Test").withIdentifier(1)
                ).withOnDrawerItemClickListener(this)
                .build();

    }

    @Override
    public RouteBuilder onRouteSetup() {
        return new RouteBuilder(R.id.fragment_container, this)
                .addRoute(new RouteBuilder.Item(ROUTE_TASK, new BlankFragment()));
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        getRouteBuilder(this).navigate(ROUTE_TASK, "route_task", "var");
        return false;
    }
}
