package com.dhy.baseandroidapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dhy.baseandroidapp.R;

/**
 * Created by dhy on 2017/12/26.
 */

public class LastFragment extends BaseFragment {
    public final static String PARAM_RESULT = FirstFragment.class.getSimpleName()+"_PARAM_RESULT";
    private View lastView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lastView = inflater.inflate(R.layout.fragment_last,null,false);
        initClick();
        return lastView;
    }

    private void initClick() {
        lastView.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = mFunctionManager.invoke(PARAM_RESULT,"have you receive?",String.class);
                Toast.makeText(getActivity(),"last fragment param result interface:"+string,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
