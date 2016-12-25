package com.emerap.library.RouteBuilder;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

/**
 * Helper for work with android fragments for build navigation
 * Created by karbunkul on 25.12.16.
 */

public class RouteBuilder {

    private int mContainer;
    private AppCompatActivity mContext;
    private HashMap<Integer, Item> mItems;
    private Item mCurrentRoute;

    interface Init {
        RouteBuilder onRouteSetup();
    }

    /**
     * Constructor.
     *
     * @param container fragment container (resource id)
     */
    public RouteBuilder(@IdRes int container, AppCompatActivity context) {
        this.mContext = context;
        this.mContainer = container;
        this.mItems = new HashMap<Integer, Item>();
    }

    /**
     * Add new route item.
     *
     * @param routeItem route item object.
     * @return this.
     */
    public RouteBuilder addRoute(Item routeItem) {
        this.mItems.put(routeItem.getIdentifier(), routeItem);
        return this;
    }

    /**
     * Get route item by identifier.
     *
     * @param identifier item identifier.
     * @return route item object.
     */
    Item getItem(int identifier) {
        return this.mItems.get(identifier);
    }

    public static RouteBuilder.Item getCurrentRoute(Bundle bundle) {
        RouteBuilder.Item item = (RouteBuilder.Item) bundle.getParcelable("current_route");
        return item;
    }

    /**
     * Navigate.
     *
     * @param identifier route item identifier.
     * @param tag        route item mTag
     * @param variant    route item mVariant
     */
    public void navigate(int identifier, String tag, String variant) {
        Item route = getItem(identifier);
        if (route.getFragment() != null) {
            Fragment fragment = route.getFragment();
            route.setTag(tag);
            route.setVariant(variant);
            this.setCurrentRoute(route);

            Bundle bundle = new Bundle();
            bundle.putParcelable("current_route", getCurrentRoute());
            fragment.setArguments(bundle);

            FragmentTransaction ft = mContext.getSupportFragmentManager().beginTransaction();
            ft.replace(mContainer, fragment);
            ft.commit();
        }

    }

    public void navigate(int identifier, String tag) {
        navigate(identifier, tag, null);
    }

    private AppCompatActivity getContext() {
        return mContext;
    }

    public Item getCurrentRoute() {
        return mCurrentRoute;
    }

    private void setCurrentRoute(Item currentRoute) {
        this.mCurrentRoute = currentRoute;
    }

    /**
     * Inner class for item represent.
     */
    public static class Item implements Parcelable {

        private Fragment mFragment;
        private int mIdentifier;
        private String mTag;
        private String mVariant;

        public Item(int identifier, Fragment fragment) {
            this.mFragment = fragment;
            this.mIdentifier = identifier;
        }

        protected Item(Parcel in) {
            mIdentifier = in.readInt();
            mTag = in.readString();
            mVariant = in.readString();
        }

        public Fragment getFragment() {
            return mFragment;
        }

        public int getIdentifier() {
            return mIdentifier;
        }

        public String getTag() {
            return mTag;
        }

        public void setTag(String tag) {
            this.mTag = tag;
        }

        public String getVariant() {
            return mVariant;
        }

        public void setVariant(String variant) {
            this.mVariant = variant;
        }

        public static final Creator<Item> CREATOR = new Creator<Item>() {
            @Override
            public Item createFromParcel(Parcel in) {
                return new Item(in);
            }

            @Override
            public Item[] newArray(int size) {
                return new Item[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.getIdentifier());
            parcel.writeValue(this.getFragment());
            parcel.writeString(this.getTag());
            parcel.writeString(this.getVariant());
        }
    }
}
