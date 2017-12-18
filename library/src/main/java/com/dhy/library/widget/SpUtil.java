package com.dhy.library.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.Set;

/**
 * Created by dhy on 2017/12/15.
 */

public class SpUtil {
    private String NAME = "";
    private Context context;
    private SharedPreferences sharePreference;
    private SharedPreferences.Editor editor;

    public SpUtil() {
        sharePreference = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
    }

    public static SpUtil getInstance() {
        return SpHolder.newInstance;
    }

    private static class SpHolder {
        private final static  SpUtil newInstance = new SpUtil();
    }

    public void initSpConfig(Context mContext,String shareName){
        this.context = mContext;
        if (!TextUtils.isEmpty(shareName)) {
            this.NAME = shareName;
        }
    }

    public synchronized SpUtil edit(){
        if (editor == null) {
            editor = sharePreference.edit();
        }
        return this;
    }

    public synchronized boolean commit(){
        boolean committed = editor.commit();
        editor = null;
        return committed;
    }

    public synchronized SpUtil put(String key,Object object){
        if (object == null) {
            editor.putString(key,"");
            return this;
        }
        if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Set) {
            editor.putStringSet(key, (Set<String>) object);
        } else {
            editor.putString(key,object.toString());
        }
        return this;
    }

    public Object get(String key,Object defaultObject){
        if (defaultObject == null) {
            return sharePreference.getString(key,null);
        }

        if (defaultObject instanceof Integer) {
            return sharePreference.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharePreference.getLong(key, (Long) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharePreference.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof String) {
            return sharePreference.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Set) {
            return sharePreference.getStringSet(key, (Set<String>) defaultObject);
        }
        return null;
    }

    public SpUtil remove(String key){
        editor.remove(key);
        return this;
    }
}
