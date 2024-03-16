package org.example.rsocket;

import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.CloseableChannel;
import io.rsocket.transport.netty.server.TcpServerTransport;
import org.example.rsocket.service.SockerAcceptorImpl;

public class Server {
    public static void main(String[] args) {
        RSocketServer socketServer = RSocketServer.create(new SockerAcceptorImpl());
        CloseableChannel closeableChannel = socketServer.bindNow(TcpServerTransport.create(6565));

        // keep listening
        closeableChannel.onClose().block();

    }
}
