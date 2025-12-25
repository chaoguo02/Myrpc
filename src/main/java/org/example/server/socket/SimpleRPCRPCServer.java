package org.example.server.socket;

import org.example.pojo.WorkThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class SimpleRPCRPCServer implements RPCServer{
    // 存在服务接口名 -> service对象的map
    private Map<String, Object> serviceProvide;

    public SimpleRPCRPCServer(Map<String, Object> serviceProvide){
        this.serviceProvide = serviceProvide;
    }
    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务器启动了");
            // BIO的方式监听Socket
            while(true){
                Socket socket = serverSocket.accept();
                // 开启一个新线程去处理
                new Thread(new WorkThread(socket, serviceProvide)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    @Override
    public void stop() {

    }
}
