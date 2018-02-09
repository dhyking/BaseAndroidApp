package com.dhy.baseandroidapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dhy.baseandroidapp.MarqueeTextView;
import com.dhy.baseandroidapp.R;
import com.dhy.baseandroidapp.bean.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhy on 2017/12/27.
 */

public class RecyclerMovieAdapter extends RecyclerView.Adapter<RecyclerMovieAdapter.MyViewHolder> {
    List<Movie> list = new ArrayList<>();

    private Context context;

    public RecyclerMovieAdapter(Context mContext) {
        context = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_movie, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        final MyViewHolder mHolder = holder;
//        MyTask mMyTask = new MyTask(holder.icon);
//        mMyTask.execute(list.get(position).getIcon());
//        try {
//            holder.icon.setImageBitmap(mMyTask.get());
//        } catch (InterruptedException mE) {
//            mE.printStackTrace();
//        } catch (ExecutionException mE) {
//            mE.printStackTrace();
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        MarqueeTextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (MarqueeTextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }

    public void addNewData(List<Movie> mMovieList) {
        list.addAll(mMovieList);
        notifyDataSetChanged();
    }


    class MyTask extends AsyncTask<String, Integer, Bitmap> {
        protected ImageView mImageView;

        public MyTask(ImageView mImageView) {
            this.mImageView = mImageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String API = params[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(API);
                try {
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    return bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } catch (MalformedURLException mE) {
                mE.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap mBitmap) {
            if (mImageView != null && mBitmap != null) {
//                mImageView.setImageBitmap(mBitmap);
            }
        }
    }
}
