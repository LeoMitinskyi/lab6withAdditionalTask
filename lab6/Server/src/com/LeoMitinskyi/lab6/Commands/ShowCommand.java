package com.LeoMitinskyi.lab6.Commands;

import com.LeoMitinskyi.lab6.Engine;
import com.LeoMitinskyi.lab6.ServerLoop;

public class ShowCommand extends Command{
    public ShowCommand(Engine engine, ServerLoop serverLoop) {
        super(engine, serverLoop);
    }
    @Override
    public String execute() {
        collection.sort();
        return collection.show();
    }

    @Override
    public String getDescription() {
        return ":вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String getName() {
        return "show";
    }
}
