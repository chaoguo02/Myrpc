package org.example.client.socket;

import lombok.Data;
import org.example.pojo.RPCRequest;
import org.example.pojo.RPCResponse;


public interface MyClient {

    RPCResponse sendResponse(RPCRequest request);

    RPCResponse sendRequest(RPCRequest request);
}
