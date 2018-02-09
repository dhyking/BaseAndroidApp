package com.dhy.baseandroidapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhy.baseandroidapp.R;
import com.dhy.baseandroidapp.socket.SocketActivity;

/**
 * Created by dhy on 2017/12/26.
 */

public class FirstFragment extends BaseFragment {
    private View firstView;
    public final static String NO_PARAM_NO_RESULT = FirstFragment.class.getSimpleName()+"_NO_PARAM_NO_RESULT";
    public final static String PARAM_NO_RESULT = FirstFragment.class.getSimpleName()+"_PARAM_NO_RESULT";
    public final static String NO_PARAM_RESULT = FirstFragment.class.getSimpleName()+"_NO_PARAM_RESULT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firstView = inflater.inflate(R.layout.fragment_first,null,false);
        initClick();
        return firstView;
    }

    private void initClick() {
        firstView.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mFunctionManager.invoke(NO_PARAM_NO_RESULT);
//                startActivity(new Intent(getActivity(), InjectImplActivity.class));
//                startActivity(new Intent(getActivity(), MyAidlActivity.class));
                startActivity(new Intent(getActivity(), SocketActivity.class));
            }
        });
    }



}
