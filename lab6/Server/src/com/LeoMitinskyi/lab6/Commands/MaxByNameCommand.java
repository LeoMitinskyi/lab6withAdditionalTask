package com.LeoMitinskyi.lab6.Commands;

import com.LeoMitinskyi.lab6.Engine;
import com.LeoMitinskyi.lab6.ServerLoop;

public class MaxByNameCommand extends Command {
    public MaxByNameCommand(Engine engine, ServerLoop serverLoop) {
        super(engine,serverLoop);
    }
    @Override
    public String execute() {
        if (collection.MaxName().getName() != null ){
            return collection.MaxName().toString();
        }else{
            return "Коллекция пуста";
        }
    }

    @Override
    public String getDescription() {
        return ":вывести любой объект из коллекции, значение поля name которого является максимальным";
    }

    @Override
    public String getName() {
        return "max_by_name";
    }
}
