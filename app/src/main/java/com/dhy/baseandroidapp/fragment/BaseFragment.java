package com.dhy.baseandroidapp.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.dhy.baseandroidapp.MainActivity;
import com.dhy.baseandroidapp.mvp.presenter.BasePresenter;
import com.dhy.baseandroidapp.mvp.view.IView;
import com.dhy.library.struct.FunctionManager;

/**
 * Created by dhy on 2017/12/26.
 */

public class BaseFragment<V extends IView,T extends BasePresenter<V>> extends Fragment {
    public FunctionManager mFunctionManager;
    public T presenter;
    public void setFunctionManager(FunctionManager mFunctionManager) {
        this.mFunctionManager = mFunctionManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            MainActivity mActivity = (MainActivity) context;
            Log.e("BaseFragment", "tag:"+getTag());
            mActivity.setFunctionForFragment(getTag());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
