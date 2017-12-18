package com.dhy.library.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

import com.dhy.library.widget.LoadingDialog;

/**
 * Created by dhy on 2017/12/15.
 */

public class BaseFragment extends Fragment {
    /**
     * 加载框
     */
    public void startLoadingDialog(){
        LoadingDialog.showLoading(getActivity());
    }

    public void startLoadingDialog(String msg){
        LoadingDialog.showLoading(getActivity(),msg);
    }

    public void startLoadingDialog(String msg,boolean isCancel){
        LoadingDialog.showLoading(getActivity(),msg,isCancel);
    }

    public void startLoadingDialog(String msg, boolean isCancel, DialogInterface.OnCancelListener listener){
        LoadingDialog.showLoading(getActivity(),msg,isCancel,listener);
    }

    /**
     * 关闭加载框
     */
    public void closeLoadingDialog(){
        LoadingDialog.closeLoading();
    }


    /**
     * 隐藏输入法
     */
    public void hideInputSoft(){
        InputMethodManager mInputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromInputMethod(getActivity().getCurrentFocus().getWindowToken(),0);
    }

    /**
     * 开启新activity
     * @param cls
     */
    public void openActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(),cls);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(int resId) {
        getActivity().getWindow().setStatusBarColor(resId);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setNavigationBarColor(int resId){
        getActivity().getWindow().setNavigationBarColor(resId);
    }
}
