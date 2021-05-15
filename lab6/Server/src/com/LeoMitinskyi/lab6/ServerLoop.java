package com.LeoMitinskyi.lab6;

import com.LeoMitinskyi.lab6.Commands.*;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerLoop {
    protected static ByteBuffer buffer;
    protected static InetSocketAddress iAddr;
    protected static SocketAddress remoteAddr;
    protected static int port;

    Engine engine;
    DatagramChannel server = DatagramChannel.open();
    IOManager ioManager = new IOManager(this);

    protected ServerLoop(int port) throws IOException{
        ServerLoop.port = port;
    }

    protected void StartLoop(String filename) throws JAXBException, IOException {
        iAddr = new InetSocketAddress(port);
        engine = new Engine(this);
        engine.loadCollectionFromFile(filename);
        boolean StopFlag = true;
        System.out.println("Start Server!");
        server.bind(iAddr);
        buffer = ByteBuffer.allocate(65536);
        Runnable userInput = () -> {
            try {
                while (true) {
                    String aboba = ioManager.read();
                    if (aboba.equals("save")) {
                        SaveCommand saveCommand = new SaveCommand(engine,this);
                        ioManager.writeLine(saveCommand.execute());
                    } else if(aboba.equals("exit")){
                        SafeExit();
                    }
                    else{
                        ioManager.writeLine("Сервер не может принять такую команду");
                    }
                }
            }catch (Exception exception){
                ioManager.writeLine("Что-то пошло не так!");
            }
        };
        Thread thread = new Thread(userInput);
        thread.start();
        while (StopFlag) {
            StopFlag = processingClientRequest();
        }
    }

    private boolean processingClientRequest() throws JAXBException {
        String response;
        try {
            do {
                String commandName = receiveMessage(buffer);
                response = engine.readAndExecuteCommand(commandName);
                ioManager.writeLine("Получено сообщение '" + commandName + "'");
                sendMessage(buffer,response,server,remoteAddr);
            } while (!response.equals("exit"));
            return false;
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Произошла ошибка при работе с клиентом!");
        }
        return true;
    }

    private void SafeExit(){
        SaveCommand saveCommand = new SaveCommand(engine,this);
        saveCommand.execute();
        System.exit(0);
    }

    public void sendMessage(ByteBuffer buffer, String message,DatagramChannel server, SocketAddress remoteAddr) throws IOException {
        Request request = new Request(message);
        buffer.clear();
        buffer.put(serialize(request));
        buffer.flip();
        server.send(buffer, remoteAddr);
        buffer.clear();
    }



    public String receiveMessage(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        remoteAddr = server.receive(buffer);
        buffer.flip();
        Request request = deserialize();
        return request.getRequestBody();
    }

    private byte[] serialize(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        return buffer;
    }

    private Request deserialize() throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request response = (Request) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        buffer.clear();
        return response;
    }

    public SocketAddress getRemoteAddr(){
        return remoteAddr;
    }

    public ByteBuffer getByteBuffer(){
        return buffer;
    }

    public DatagramChannel getChannel(){
        return server;
    }

    public IOManager getIOManager() {
        return ioManager;
    }
}
