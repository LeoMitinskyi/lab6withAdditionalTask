package com.LeoMitinskyi.lab6.Commands;

import com.LeoMitinskyi.lab6.Engine;
import com.LeoMitinskyi.lab6.IOManager;
import com.LeoMitinskyi.lab6.ServerLoop;
import com.LeoMitinskyi.lab6.types.People;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {
    protected final Engine engine;
    protected final IOManager ioManager;
    protected final People collection;
    protected final ServerLoop serverLoop;
    public Command(Engine engine, ServerLoop serverLoop) {
        this.engine = engine;
        this.serverLoop = serverLoop;
        this.ioManager = serverLoop.getIOManager();
        this.collection = engine.getCollection();
    }

    public abstract String execute() throws IOException, JAXBException, ClassNotFoundException;
    public abstract String getDescription();
    public abstract String getName();
}
