package com.dhy.baseandroidapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.dhy.baseandroidapp.adapter.RecyclerMovieAdapter;
import com.dhy.baseandroidapp.bean.Movie;
import com.dhy.baseandroidapp.mvp.presenter.PicPresenter;
import com.dhy.baseandroidapp.mvp.view.PicView;

import java.util.List;

public class MvpActivity extends BaseActivity<PicView,PicPresenter> implements PicView{

    RecyclerView mRecyclerView;
    RecyclerMovieAdapter mRecyclerMovieAdapter;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what ==1) {
                List<Movie> list = (List<Movie>) msg.obj;
                mRecyclerMovieAdapter.addNewData(list);
            }
        }
    };
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        initView();
        initData();
    }

    @Override
    protected PicPresenter createPresenter() {
        return new PicPresenter();
    }


    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_show);
        mProgressBar = (ProgressBar) findViewById(R.id.proBar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initData(){
        presenter.show();
    }

    @Override
    public void showPic(List<Movie> list) {
        mRecyclerMovieAdapter = new RecyclerMovieAdapter(this);
        mRecyclerMovieAdapter.addNewData(list);
        mRecyclerView.setAdapter(mRecyclerMovieAdapter);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}
