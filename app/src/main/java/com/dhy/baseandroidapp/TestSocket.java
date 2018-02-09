package com.dhy.baseandroidapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dhy on 2018/1/23.
 */

public class TestSocket {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerReceviedByTcp();
            }
        }).start();
    }

    public static void ServerReceviedByTcp() {
        // 声明一个ServerSocket对象
        ServerSocket serverSocket = null;
        try {
            // 创建一个ServerSocket对象，并让这个Socket在1989端口监听
            serverSocket = new ServerSocket(1989);
            // 调用ServerSocket的accept()方法，接受客户端所发送的请求，
            // 如果客户端没有发送数据，那么该线程就停滞不继续
            Socket socket = serverSocket.accept();
            // 从Socket当中得到InputStream对象
            InputStream inputStream = socket.getInputStream();
            byte buffer[] = new byte[1024 * 4];
            int temp = 0;
            // 从InputStream当中读取客户端所发送的数据
            while ((temp = inputStream.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, temp));
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
