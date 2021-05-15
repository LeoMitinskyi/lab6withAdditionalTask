package com.LeoMitinskyi.lab6;

import java.net.SocketException;

public class StartClient {
    public static final int port = 2222;
    public static void main(String[] args) throws SocketException{
        ClientLoop clientLoop = new ClientLoop(port);
        clientLoop.startLoop();
    }
}
