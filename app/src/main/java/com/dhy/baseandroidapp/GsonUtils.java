package com.dhy.baseandroidapp;

import com.dhy.baseandroidapp.bean.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhy on 2017/12/27.
 */

public class GsonUtils {
    public static List<Movie> getMovie(String string){
        try {
            List<Movie> list = new ArrayList<>();
            JSONObject mJSONObject = new JSONObject(string);
            if (mJSONObject.has("movies")) {
                String movies = mJSONObject.getString("movies");
                JSONArray mJSONArray = new JSONArray(movies);
                for (int i = 0; i < mJSONArray.length(); i++) {
                    JSONObject mJSONObject1 = mJSONArray.getJSONObject(i);
                    Movie mMovie = new Movie();
                    if (mJSONObject1.has("titleCn")) {
                        mMovie.setTitle(mJSONObject1.getString("titleCn"));
                    }

                    if (mJSONObject1.has("img")) {
                        mMovie.setIcon(mJSONObject1.getString("img"));
                    }

                    list.add(mMovie);
                }
                return list;
            }
        } catch (JSONException mE) {
            mE.printStackTrace();
        }
        return null;
    }
}
