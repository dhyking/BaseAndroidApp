package com.dhy.baseandroidapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dhy.baseandroidapp.adapter.MyFragmentAdapter;
import com.dhy.baseandroidapp.fragment.BaseFragment;
import com.dhy.baseandroidapp.fragment.FirstFragment;
import com.dhy.baseandroidapp.fragment.LastFragment;
import com.dhy.baseandroidapp.fragment.SecondFragment;
import com.dhy.baseandroidapp.fragment.ThirdFragment;
import com.dhy.library.struct.FunctionManager;
import com.dhy.library.struct.FunctionNoParamNoResult;
import com.dhy.library.struct.FunctionOnlyParam;
import com.dhy.library.struct.FunctionOnlyResult;
import com.dhy.library.struct.FunctionParamAndResult;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    private String[] textTitles = new String[]{"首页","二页","三页","尾页"};
    private List<String> tagList = new ArrayList<>();
    private Fragment[] fragmentArr = new Fragment[]{new FirstFragment(), new SecondFragment(), new ThirdFragment(), new LastFragment()};
    private String[] Frag_Tag = new String[]{FirstFragment.NO_PARAM_NO_RESULT,SecondFragment.PARAM_NO_RESULT,
            ThirdFragment.NO_PARAM_RESULT,LastFragment.PARAM_RESULT};
    private String test;
    ViewPager mViewPager;
    TabLayout mTabLayout;
    private boolean isPermissioned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Log.e("MainActivity", "patch start");
    }

    private void initViews() {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N) {
            int code = ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (code != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},10);
            } else {
                isPermissioned = true;
            }
        }

        LinearLayout mLinearLayout = new LinearLayout(this);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        for (int mI = 0; mI < Frag_Tag.length; mI++) {
            tagList.add(Frag_Tag[mI]);
        }
        for (int i = 0; i < 4; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(textTitles[i]));
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "position:" + position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Button btnLoad = (Button) findViewById(R.id.load);
        btnLoad.setVisibility(View.GONE);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadPatch(v);

            }
        });

        initFragment();
    }


    private void initFragment() {
        FirstFragment mFirstFragment = new FirstFragment();
        SecondFragment mSecondFragment = new SecondFragment();
        ThirdFragment mThirdFragment = new ThirdFragment();
        LastFragment mLastFragment = new LastFragment();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(mFirstFragment);
        fragmentList.add(mSecondFragment);
        fragmentList.add(mThirdFragment);
        fragmentList.add(mLastFragment);
        MyFragmentAdapter mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),fragmentList,tagList,textTitles);
        mViewPager.setAdapter(mMyFragmentAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    public void setFunctionForFragment(String tag){
        FragmentManager mFragmentManager = getSupportFragmentManager();
        BaseFragment mBaseFragment = (BaseFragment) mFragmentManager.findFragmentByTag(tag);
        FunctionManager mFunctionManager = FunctionManager.getInstance();
        mBaseFragment.setFunctionManager(mFunctionManager.addFunction(new FunctionNoParamNoResult(FirstFragment.NO_PARAM_NO_RESULT) {
            @Override
            public void function() {
                Toast.makeText(MainActivity.this,"first no param no result interface",Toast.LENGTH_SHORT).show();
            }
        }).addFunction(new FunctionOnlyParam<String>(SecondFragment.PARAM_NO_RESULT) {
                    @Override
                    public void function(String param) {
                        Toast.makeText(MainActivity.this,"second param no result interface:"+param,Toast.LENGTH_SHORT).show();
                    }
                }).addFunction(new FunctionOnlyResult<String>(ThirdFragment.NO_PARAM_RESULT) {
                    @Override
                    public String function() {
                        return "send to third fragment";
                    }
                }).addFunction(new FunctionParamAndResult<String, String>(LastFragment.PARAM_RESULT) {
            @Override
            public String function(String param) {
                test = param;
                Toast.makeText(MainActivity.this,"activity param result interface:"+param,Toast.LENGTH_SHORT).show();
                return "yes i have";
            }
        })
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
           if (permissions[0].equals(grantResults[0])) {
               isPermissioned = true;
           } else {
               isPermissioned = false;
           }
        }
    }

    public void loadPatch(View view) {
        Log.e("MainActivity", "isPermissioned:" + isPermissioned);
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
    }


}
