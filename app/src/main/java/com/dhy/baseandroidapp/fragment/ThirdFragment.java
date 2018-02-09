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

public class ThirdFragment extends BaseFragment {
    public final static String NO_PARAM_RESULT = FirstFragment.class.getSimpleName()+"_NO_PARAM_RESULT";
    private View thirdView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thirdView = inflater.inflate(R.layout.fragment_third,null,false);
        initClick();
        return thirdView;
    }

    private void initClick() {
        thirdView.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                double string = mFunctionManager.invoke(NO_PARAM_RESULT,Double.class);
                String string = mFunctionManager.invoke(NO_PARAM_RESULT,String.class);
                Toast.makeText(getActivity(),"third fragment param result interface:"+string,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
