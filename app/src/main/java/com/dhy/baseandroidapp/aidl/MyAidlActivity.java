package com.dhy.baseandroidapp.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dhy.baseandroidapp.IMyAidl;
import com.dhy.baseandroidapp.R;
import com.dhy.baseandroidapp.bean.Student;

import java.util.Random;

/**
 * aidl 客户端实现
 */
public class MyAidlActivity extends AppCompatActivity implements View.OnClickListener{
    private IMyAidl mAidl;

    /**
     * 拿到 aidl类
     */
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            mAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAidl = null;
        }
    };
    TextView mTvShowStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_aidl);
        Button addStudent = (Button) findViewById(R.id.add_student);
        mTvShowStudent = (TextView) findViewById(R.id.tv_show_student);
        addStudent.setOnClickListener(this);
        Intent intent1 = new Intent(getApplicationContext(), MyAidlService.class);
        bindService(intent1, conn, BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    @Override
    public void onClick(View v) {
        Random mRandom = new Random();
        Student mStudent = new Student("student:"+mRandom.nextInt(100)+10);
        try {
            mAidl.addStudent(mStudent);
            Log.d("MyAidlActivity", "mAidl.getStudentList().size():" + mAidl.getStudentList().size());
            Log.d("MyAidlActivity", mAidl.getStudentList().toString());
            mTvShowStudent.setText(mAidl.getStudentList().toString());
        } catch (RemoteException mE) {
            mE.printStackTrace();
        }

    }
}
