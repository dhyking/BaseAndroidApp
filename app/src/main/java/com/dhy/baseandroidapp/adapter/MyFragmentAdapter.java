package com.dhy.baseandroidapp.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dhy on 2017/12/27.
 */

public class MyFragmentAdapter extends PagerAdapter {
    private static final String TAG = "MyFragmentAdapter";
    private static final boolean DEBUG = false;
    private List<Fragment> mFragmentList;
    private List<String> tagList;
    private String[] titles;
    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;


    public MyFragmentAdapter( FragmentManager mFragmentManager,List<Fragment> mFragmentList,List<String> FragmentTagList,String[] title) {
        this.mFragmentList = mFragmentList;
        this.mFragmentManager = mFragmentManager;
        this.tagList = FragmentTagList;
        this.titles = title;
    }


    @Override
    public void startUpdate(ViewGroup container) {
        if (container.getId() == View.NO_ID) {
            throw new IllegalStateException("ViewPager with adapter " + this
                    + " requires a view id");
        }
    }


    @Override
    public int getCount() {
        return (mFragmentList != null && mFragmentList.size() >0)? mFragmentList.size():0;
    }

    public Fragment getItem(int position){
        if (mFragmentList != null && mFragmentList.size()>0) {
            return mFragmentList.get(position);
        }
        return null;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment)object).getView() == view;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment)object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurTransaction != null) {
            mCurTransaction.commitNowAllowingStateLoss();
            mCurTransaction = null;
        }
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        final long itemId = getItemId(position);

        // Do we already have this fragment?
        String name = getFragTag(position);
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if (TextUtils.isEmpty(name)) {
            if (DEBUG) Log.v(TAG, "fragment's tag cannot be null");
            return null;
        }
        if (fragment != null) {
            if (DEBUG) Log.v(TAG, "Attaching item #" + itemId + ": f=" + fragment);
            mCurTransaction.attach(fragment);
        } else {
            fragment = getItem(position);
            if (DEBUG) Log.v(TAG, "Adding item #" + itemId + ": f=" + fragment);
            mCurTransaction.add(container.getId(), fragment,
                    getFragTag(position));
        }
        if (fragment != mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }

        return fragment;
    }

    private String getFragTag(int position) {
        if (tagList != null && tagList.size()> 0 ) {
            return tagList.get(position);
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        if (DEBUG) Log.v(TAG, "Detaching item #" + getItemId(position) + ": f=" + object
                + " v=" + ((Fragment)object).getView());
        mCurTransaction.detach((Fragment)object);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
