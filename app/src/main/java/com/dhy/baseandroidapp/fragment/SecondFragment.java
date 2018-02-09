package com.dhy.baseandroidapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhy.baseandroidapp.R;

/**
 * Created by dhy on 2017/12/26.
 */

public class SecondFragment extends BaseFragment {
    public final static String PARAM_NO_RESULT = FirstFragment.class.getSimpleName()+"_PARAM_NO_RESULT";
    public final static String PARAM_RESULT = FirstFragment.class.getSimpleName()+"_PARAM_RESULT";
    private View secondView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        secondView = inflater.inflate(R.layout.fragment_second,null,false);
        initClick();
        return secondView;
    }

    private void initClick() {
        secondView.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                double string = mFunctionManager.invoke(NO_PARAM_RESULT,Double.class);
                mFunctionManager.invoke(PARAM_NO_RESULT,"send to activity from second");

            }
        });
    }
}
