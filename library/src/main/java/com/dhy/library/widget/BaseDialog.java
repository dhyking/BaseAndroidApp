package com.dhy.library.widget;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhy.library.R;
import com.dhy.library.utils.CallBackAction;

/**
 * Created by dhy on 2017/12/18.
 */

public abstract class BaseDialog implements View.OnClickListener{
    private Activity mActivity;
    private View baseView;
    private CallBackAction CallBackAction;
    private Object successCallBackValue,failedCallBackValue;
    private int onFailureErrorId;
    RelativeLayout mRelativeLayoutDialog,mRelativeLayoutTitle;
    LinearLayout mLinearLayoutInfo,mLinearLayoutBtn;
    TextView mTextViewTitle,mTextViewInfo;
    Button btnCancel,btnSubmit,btnClose;
    ImageView mImageViewClose;
    Dialog mDialog;
    boolean isClose;

    protected abstract void addContentView();

    public BaseDialog(CallBackAction mDialogCallBack, Activity mActivity) {
        CallBackAction = mDialogCallBack;
        this.mActivity = mActivity;
        init();
    }

    public void init() {
        if (mActivity != null && mActivity.getResources() != null) {
            int widthPix = mActivity.getResources().getDisplayMetrics().widthPixels;
            mDialog = new Dialog(mActivity, R.style.CustomLoadingDialog);
            Window window = mActivity.getWindow();
            window.getDecorView().setPadding(0,0,0,0);
            int side = mActivity.getResources().getDimensionPixelSize(R.dimen.lay_content_padding12);
            window.setBackgroundDrawable(null);
            WindowManager.LayoutParams mLayoutParams =  window.getAttributes();
            mLayoutParams.alpha = 1.0f;
            mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            mLayoutParams.width = widthPix - 2 * side;
            window.setWindowAnimations(R.style.simple_window_style);

            baseView = mActivity.getLayoutInflater().inflate(R.layout.dialog_base,null);
            mDialog.addContentView(baseView,mLayoutParams);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setCancelable(false);
        }
    }

    public RelativeLayout getRelativeLayoutDialog() {
        if (mRelativeLayoutDialog == null) {
            mRelativeLayoutDialog = baseView.findViewById(R.id.dialog_base_rl_bg);
            mRelativeLayoutDialog.setVisibility(View.VISIBLE);
        }
        return mRelativeLayoutDialog;
    }

    public LinearLayout getLinearLayoutInfo() {
        if (mLinearLayoutInfo == null) {
            mLinearLayoutInfo = baseView.findViewById(R.id.dialog_base_ll_info);
            mLinearLayoutInfo.setVisibility(View.VISIBLE);
        }
        return mLinearLayoutInfo;
    }

    public RelativeLayout getRelativeLayoutTitle() {
        if (mRelativeLayoutTitle == null) {
            mRelativeLayoutTitle = baseView.findViewById(R.id.dialog_base_rl_title);
            mRelativeLayoutTitle.setVisibility(View.VISIBLE);
        }
        return mRelativeLayoutTitle;
    }

    public LinearLayout getLinearLayoutBtn() {
        if (mLinearLayoutBtn == null) {
            mLinearLayoutBtn = baseView.findViewById(R.id.dialog_base_rl_title);
            mRelativeLayoutTitle.setVisibility(View.VISIBLE);
        }
        return mLinearLayoutBtn;
    }

    public Button getBtnCancel() {
        if (btnCancel == null) {
            btnCancel = baseView.findViewById(R.id.dialog_base_btn_cancel);
            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setOnClickListener(this);
        }
        return btnCancel;
    }

    public Button getBtnClose() {
        if (btnClose == null) {
            btnClose = baseView.findViewById(R.id.dialog_base_btn_close);
            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setOnClickListener(this);
        }
        return btnClose;
    }

    public Button getBtnSubmit() {
        if (btnSubmit == null) {
            btnSubmit  = baseView.findViewById(R.id.dialog_base_btn_submit);
            btnSubmit.setVisibility(View.VISIBLE);
            btnSubmit.setOnClickListener(this);
        }
        return btnSubmit;
    }

    public ImageView getImageViewClose(){
        if (mImageViewClose == null) {
            mImageViewClose  = baseView.findViewById(R.id.dialog_base_iv_close);
            mImageViewClose.setVisibility(View.VISIBLE);
            mImageViewClose.setOnClickListener(this);
        }
        return mImageViewClose;

    }


    public TextView getTextViewInfo() {
        if (mTextViewInfo == null) {
            mTextViewInfo = baseView.findViewById(R.id.dialog_base_tv_info);
            mTextViewInfo.setVisibility(View.VISIBLE);
        }
        return mTextViewInfo;
    }

    public TextView getTextViewTitle() {
        if (mTextViewTitle == null) {
            mTextViewTitle = baseView.findViewById(R.id.dialog_base_tv_title);
            mTextViewTitle.setVisibility(View.VISIBLE);
        }
        return mTextViewTitle;
    }

    public void setImageViewClose(ImageView mImageViewClose) {
        this.mImageViewClose = mImageViewClose;
    }

    public void setTextViewInfo(TextView mTextViewInfo) {
        this.mTextViewInfo = mTextViewInfo;
    }

    public void setTextViewTitle(TextView mTextViewTitle) {
        this.mTextViewTitle = mTextViewTitle;
    }

    public Button setBtnCancel(int resId) {
        getBtnCancel().setText(resId);
        return getBtnCancel();
    }

    public Button setBtnClose(int resId) {
        getBtnClose().setText(resId);
        return getBtnClose();
    }

    public Button setBtnSubmit(int resId) {
        getBtnSubmit().setText(resId);
        return getBtnSubmit();
    }

    public ImageView setImageClose(int resId){
        getImageViewClose().setImageResource(resId);
        return getImageViewClose();
    }

    public void setDialogBg(int resId) {
        getRelativeLayoutDialog().setBackgroundResource(resId);
    }


    public void dismiss(){
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public boolean isShowing() {
        if (mDialog != null && mDialog.isShowing()) {
            return true;
        }
        return false;
    }

    /***
     * 设置是否可以消失
     * 默认不可以
     */
    public void setCanceledOnTouchOutside(boolean bool) {
        if (mDialog != null) {
            mDialog.setCanceledOnTouchOutside(bool);
        }
    }

    public void setSuccessCallBackValue(Object mSuccessCallBackValue) {
        successCallBackValue = mSuccessCallBackValue;
    }

    public void setFailedCallBackValue(int errorId,Object mFailedCallBackValue) {
        failedCallBackValue = mFailedCallBackValue;
        onFailureErrorId = errorId;

    }

    public void setIsClose(boolean isClose){
        this.isClose = isClose;
    }


    @Override
    public void onClick(View v) {
       int viewId= v.getId();
        if (viewId == R.id.dialog_base_btn_cancel) {
            if (successCallBackValue != null) {
                CallBackAction.onError(onFailureErrorId,failedCallBackValue);
            }
            dismiss();
        } else if (viewId == R.id.dialog_base_iv_close) {
            if (successCallBackValue != null) {
                CallBackAction.onError(onFailureErrorId,failedCallBackValue);
            }
            dismiss();
        } else if (viewId == R.id.dialog_base_btn_submit) {
            if (successCallBackValue != null) {
                CallBackAction.onSuccess(successCallBackValue);
            }
            if (isClose) {
                dismiss();
            }
        } else if (viewId == R.id.dialog_base_btn_close) {
            if (successCallBackValue != null) {
                CallBackAction.onSuccess(successCallBackValue);
            }
            if (isClose) {
                dismiss();
            }
        }
    }
}
