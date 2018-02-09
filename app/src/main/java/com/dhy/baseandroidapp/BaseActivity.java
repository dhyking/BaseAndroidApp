package com.dhy.baseandroidapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dhy.baseandroidapp.mvp.presenter.BasePresenter;
import com.dhy.baseandroidapp.mvp.view.IView;

/**
 * Created by dhy on 2017/12/27.
 */

public abstract class BaseActivity<V extends IView,T extends BasePresenter<V>> extends AppCompatActivity{

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V) this);
    }

    protected abstract T createPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
