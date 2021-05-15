package com.LeoMitinskyi.lab6.Commands;

import com.LeoMitinskyi.lab6.Engine;
import com.LeoMitinskyi.lab6.ServerLoop;

public class ClearCommand extends Command {
    public ClearCommand(Engine engine, ServerLoop serverLoop) {
        super(engine,serverLoop);
    }
    @Override
    public String execute() {
        collection.clear();
        return "Коллекция очищена.";
    }

    @Override
    public String getDescription() {
        return ":очистить коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
