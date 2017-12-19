package com.dhy.library.utils;

/**
 * Created by dhy on 2017/12/18.
 */

public interface CallBackAction {
    void onSuccess(Object object);
    void onError(int errorId,Object errorValue);
}
