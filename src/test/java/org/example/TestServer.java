package org.example;

import org.example.client.ServiceProvider;
import org.example.client.service.BlogService;
import org.example.client.service.UserService;
import org.example.client.service.impl.BlogServiceImpl;
import org.example.client.service.impl.UserServiceImpl;
import org.example.server.nettyserver.NettyRPCServer;
import org.example.server.socket.RPCServer;
import org.example.server.socket.SimpleRPCRPCServer;

public class TestServer {
    public static void main(String[] args) {
        // 创建两个interfaceName
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        // 通过serviceProvider处理传入的两个interfaceName
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);
//        RPCServer rpcServer = new SimpleRPCRPCServer(serviceProvider.getInterfaceProvider());
        RPCServer rpcServer = new NettyRPCServer(serviceProvider);
        rpcServer.start(8899);
    }

}


