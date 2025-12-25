package org.example.client.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.client.proxy.ClientProxy;
import org.example.client.proxy.RPCProxy;
import org.example.client.service.UserService;
import org.example.client.socket.MyClient;
import org.example.pojo.RPCRequest;
import org.example.pojo.RPCResponse;
import org.example.pojo.User;


@NoArgsConstructor
@Data
@Builder
public class NettyClient implements MyClient {
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    private String host;
    private int port;
    public NettyClient(String host, int port){
        this.host = host;
        this.port = port;
    }
    // netty客户端初始化，重复使用
    static{
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient("127.0.0.1", 8899);
        RPCProxy rpcProxy = new RPCProxy(nettyClient,nettyClient.getHost(),nettyClient.getPort());
        UserService userService = rpcProxy.getProxy(UserService.class);
        User user = userService.getUserByUserId(10);
    }
    @Override
    public RPCResponse sendResponse(RPCRequest request) {
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            // 发送数据
            channel.writeAndFlush(request);
            channel.closeFuture().sync();

            AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
            RPCResponse response = channel.attr(key).get();
            System.out.println(response);
            return response;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            // 发送数据
            channel.writeAndFlush(request);
            channel.closeFuture().sync();

            AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
            RPCResponse response = channel.attr(key).get();
            System.out.println(response);
            return response;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
