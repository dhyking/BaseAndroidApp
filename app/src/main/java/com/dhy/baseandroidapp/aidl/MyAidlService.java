package com.dhy.baseandroidapp.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dhy.baseandroidapp.IMyAidl;
import com.dhy.baseandroidapp.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * aidl 服务端
 * Created by dhy on 2018/1/23.
 */

public class MyAidlService extends Service {
    private List<Student> stuList;

    /**
     * 客户端与服务端绑定时的回调，返回 mIBinder 后客户端就可以通过它远程调用服务端的方法，即实现了通讯
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        stuList = new ArrayList<>();
        Log.d("MyAidlService", "onBind");
        return mIBinder;
    }

    /**
     * 创建生成的本地 Binder 对象，实现 AIDL 制定的方法
     */
    private IBinder mIBinder = new IMyAidl.Stub(){

        @Override
        public void addStudent(Student student) throws RemoteException {
            stuList.add(student);
        }

        @Override
        public List<Student> getStudentList() throws RemoteException {
            return stuList;
        }
    };
}
