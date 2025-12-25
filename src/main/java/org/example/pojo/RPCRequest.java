package org.example.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/*
RPC的请求类：
 */
@Data
@Builder
public class RPCRequest implements Serializable {
    private String interfaceName;           // 要调用的服务接口的全限定名
    private String methodName;              // 要调用的方法名
    private Object[] params;                // 调用这个方法时，传入的实际参数值数组
    private Class<?>[] paramsTypes;         // 参数的类型数组
}
