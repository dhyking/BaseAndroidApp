package com.dhy.baseandroidapp.socket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dhy.baseandroidapp.R;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        findViewById(R.id.btn_connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        connectServerWithTCPSocket();
                    }
                }).start();
            }
        });
    }

    protected void connectServerWithTCPSocket() {

        Socket socket;
        try {// 创建一个Socket对象，并指定服务端的IP及端口号
            socket = new Socket("192.168.1.30", 1989);
            // 创建一个InputStream用户读取要发送的文件。
            InputStream inputStream = new ByteArrayInputStream("传值到------------------>".getBytes());

            // 获取Socket的OutputStream对象用于发送数据。
            OutputStream outputStream = socket.getOutputStream();
            // 创建一个byte类型的buffer字节数组，用于存放读取的本地文件
            byte buffer[] = new byte[4 * 1024];
            int temp = 0;
            // 循环读取文件
            while ((temp = inputStream.read(buffer)) != -1) {
                // 把数据写入到OuputStream对象中
                outputStream.write(buffer, 0, temp);
            }
            // 发送读取的数据到服务端
            outputStream.flush();

            /** 或创建一个报文，使用BufferedWriter写入,看你的需求 **/
//          String socketData = ”[2143213;21343fjks;213]”;
//          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
//                  socket.getOutputStream()));
//          writer.write(socketData.replace(“\n”, ” ”) + ”\n”);
//          writer.flush();
            /************************************************/
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
