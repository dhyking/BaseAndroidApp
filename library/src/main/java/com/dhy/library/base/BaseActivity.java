package com.dhy.library.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.dhy.library.widget.LoadingDialog;

/**
 * activity基类
 * Created by dhy on 2017/12/15.
 */

public class BaseActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }


    /**
     * 加载框
     */
    public void startLoadingDialog(){
        LoadingDialog.showLoading(this);
    }

    public void startLoadingDialog(String msg){
        LoadingDialog.showLoading(this,msg);
    }

    public void startLoadingDialog(String msg,boolean isCancel){
        LoadingDialog.showLoading(this,msg,isCancel);
    }

    public void startLoadingDialog(String msg, boolean isCancel, DialogInterface.OnCancelListener listener){
        LoadingDialog.showLoading(this,msg,isCancel,listener);
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
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),0);
    }

    /**
     * 开启新activity
     * @param cls
     */
    public void openActivity(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(int resId) {
        getWindow().setStatusBarColor(resId);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setNavigationBarColor(int resId){
        getWindow().setNavigationBarColor(resId);
    }

}
