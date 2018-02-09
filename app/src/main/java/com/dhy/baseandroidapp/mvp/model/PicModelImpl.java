package com.dhy.baseandroidapp.mvp.model;

import android.os.AsyncTask;
import android.util.Log;

import com.dhy.baseandroidapp.GsonUtils;
import com.dhy.baseandroidapp.bean.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by dhy on 2017/12/27.
 */

public class PicModelImpl implements IModel<Movie> {
    public final static String API = "https://api-m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290";

    @Override
    public void setData(final OnLoadDataListener<Movie> listener) {
        MyTask mMyTask = new MyTask();
        mMyTask.execute(API);
        this.setOnImgLoadListener(new OnImgLoadListener() {
            @Override
            public void onLoad(List<Movie> list) {
                listener.onComplete(list);
            }
        });

    }

    public OnImgLoadListener mOnImgLoadListener;

    public interface OnImgLoadListener {
        void onLoad(List<Movie> list);
    }

    public void setOnImgLoadListener(OnImgLoadListener mOnImgLoadListener) {
        this.mOnImgLoadListener = mOnImgLoadListener;
    }

    class MyTask extends AsyncTask<String, Integer, List> {

        @Override
        protected List doInBackground(String... params) {
            List<Movie> list = null;
            try {
                URL url = new URL(API);
                try {
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(10000);
                    conn.connect();
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        Log.d("MvpActivity", "conn.getContentLength():" + conn.getContentLength());
                        InputStream mInputStream = conn.getInputStream();
                        int length = 0;
                        byte[] bt = new byte[1024];
                        StringBuffer sb = new StringBuffer();
                        while ((length = mInputStream.read(bt)) != -1) {
                            sb.append(new String(bt));
                        }
                        Log.d("MvpActivity", sb.toString());
                        list = GsonUtils.getMovie(sb.toString());
                        if (list != null && list.size() > 0) {
                            Log.d("MvpActivity", "list.size():" + list.size());
                            return list;
                        }
                    }
                } catch (IOException mE) {
                    mE.printStackTrace();
                }
            } catch (MalformedURLException mE) {
                mE.printStackTrace();
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(List mList) {
            if (mOnImgLoadListener != null && mList != null) {
                mOnImgLoadListener.onLoad(mList);
            }
        }
    }
}
