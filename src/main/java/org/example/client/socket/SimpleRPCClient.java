package org.example.client.socket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pojo.RPCRequest;
import org.example.pojo.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SimpleRPCClient implements MyClient{

    private String host;
    private int port;
    // 客户端发起一次请求调用，Socket建立连接，发起请求Request，得到响应Response
    // 这里的request是封装好的，不同的service需要进行不同的封装，客户端只知道Service接口，
    // 需要一层动态代理，根据反射封装不同的Service


    @Override
    public RPCResponse sendResponse(RPCRequest request) {
        try {
            Socket socket = new Socket(host,port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("request:" + request);
            // request写入oos流中
            oos.writeObject(request);
            oos.flush();
            // ois流读数据response
            RPCResponse response = (RPCResponse) ois.readObject();
            System.out.println("response.getData:" + response.getData());
            return response;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println();
            return null;
        }
    }

    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        try {
            Socket socket = new Socket(host,port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("request:" + request);
            // request写入oos流中
            oos.writeObject(request);
            oos.flush();
            // ois流读数据response
            RPCResponse response = (RPCResponse) ois.readObject();
            System.out.println("response.getData:" + response.getData());
            return response;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println();
            return null;
        }
    }
}
