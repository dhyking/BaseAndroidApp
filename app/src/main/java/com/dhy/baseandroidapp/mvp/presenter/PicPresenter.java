package com.dhy.baseandroidapp.mvp.presenter;

import com.dhy.baseandroidapp.bean.Movie;
import com.dhy.baseandroidapp.mvp.model.IModel;
import com.dhy.baseandroidapp.mvp.model.PicModelImpl;
import com.dhy.baseandroidapp.mvp.view.PicView;

import java.util.List;

/**
 * Created by dhy on 2017/12/28.
 */

public class PicPresenter extends BasePresenter<PicView> {
    //拿到数据逻辑层接口
    private IModel<Movie> mIModel = new PicModelImpl();

    //展示数据
    public void show(){
        //是否获取到UI层接口
        if (mWeakReference.get() != null) {
            mWeakReference.get().showLoading();
            //逻辑层接口是否为空
            if (mIModel != null) {
                mIModel.setData(new IModel.OnLoadDataListener<Movie>() {
                    @Override
                    public void onComplete(List<Movie> list) {
                        if (mWeakReference.get() !=null) {
                            mWeakReference.get().closeLoading();
                            mWeakReference.get().showPic(list);
                        }
                    }
                });
            }
        }
    }

}
