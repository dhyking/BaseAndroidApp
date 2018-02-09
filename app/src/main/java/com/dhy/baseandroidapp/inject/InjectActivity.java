package com.dhy.baseandroidapp.inject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Field;

public class InjectActivity extends AppCompatActivity {

    int mLayoutId =-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectLayout();
        injectView();
    }

    /**
     * 解析注解 view id
     */
    private void injectView() {
        if (mLayoutId == -1) {return;}
        Class<?> clz = this.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            //判断是否有注解
            if (field.getAnnotations() != null) {
                //判断属于该注解
                if (field.isAnnotationPresent(ViewInject.class)){
                    //设置控件属性
                    field.setAccessible(true);//允许修改反射属性
                    ViewInject inject = field.getAnnotation(ViewInject.class);
                    try {
                        field.set(this,this.findViewById(inject.value()));
                    } catch (IllegalAccessException mE) {
                        Log.e("InjectActivity", "can not find view id");
                        mE.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     *注解布局 layout id
     */
    private void injectLayout() {
        Class<?> clz = getClass();
        if (clz.getAnnotations() != null) {
            if (clz.isAnnotationPresent(LayoutInject.class)){
                LayoutInject mInject = clz.getAnnotation(LayoutInject.class);
                mLayoutId = mInject.value();
                setContentView(mLayoutId);
            }
        }
    }
}
