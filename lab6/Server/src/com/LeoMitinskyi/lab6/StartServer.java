package com.LeoMitinskyi.lab6;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class StartServer {
    public static final int port = 2222;
    public static void main(String[] args) throws JAXBException, IOException {
        try {
            ServerLoop serverLoop = new ServerLoop(port);
            serverLoop.StartLoop(args[0]);
        }catch (ArrayIndexOutOfBoundsException exception){
            System.out.println("Invalid file");
            System.exit(0);
        }
    }
}
