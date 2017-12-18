package com.dhy.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dhy.library.R;

/**
 * Created by dhy on 2017/12/15.
 */

public class LoadingDialog {
    private static Dialog loadingDialog;

    public static void showLoading(Context context,int resId,String msg,boolean isCancel) {
        View view = LayoutInflater.from(context).inflate(resId,null,false);
        TextView tvMsg = view.findViewById(R.id.tv_loading_msg);
        tvMsg.setText(msg);
        loadingDialog = new Dialog(context,R.style.CustomLoadingDialog);
        loadingDialog.setCanceledOnTouchOutside(isCancel);
        loadingDialog.setCancelable(isCancel);
        loadingDialog.setContentView(view);
        loadingDialog.show();

    }

    /**
     * 有取消监听的加载框
     * @param context
     * @param msg
     * @param isCancel
     * @param cancelListener
     */
    public static void showLoading(Context context, String msg, boolean isCancel, DialogInterface.OnCancelListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null,false);
        TextView tvMsg = view.findViewById(R.id.tv_loading_msg);
        tvMsg.setText(msg);
        loadingDialog = new Dialog(context,R.style.CustomLoadingDialog);
        loadingDialog.setCanceledOnTouchOutside(isCancel);
        loadingDialog.setCancelable(isCancel);
        loadingDialog.setContentView(view);
        loadingDialog.show();
        loadingDialog.setOnCancelListener(cancelListener);
    }

    public static void showLoading(Context context,String msg,boolean isCancel) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null,false);
        TextView tvMsg = view.findViewById(R.id.tv_loading_msg);
        tvMsg.setText(msg);
        loadingDialog = new Dialog(context,R.style.CustomLoadingDialog);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(isCancel);
        loadingDialog.setContentView(view);
        loadingDialog.show();

    }


    public static void showLoading(Context context,String msg) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null,false);
        TextView tvMsg = view.findViewById(R.id.tv_loading_msg);
        tvMsg.setText(msg);
        loadingDialog = new Dialog(context,R.style.CustomLoadingDialog);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(true);
        loadingDialog.setContentView(view);
        loadingDialog.show();

    }

    public static void showLoading(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null,false);
        loadingDialog = new Dialog(context,R.style.CustomLoadingDialog);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(true);
        loadingDialog.setContentView(view);
        loadingDialog.show();
    }

    public static void closeLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.cancel();
        }
    }
}
