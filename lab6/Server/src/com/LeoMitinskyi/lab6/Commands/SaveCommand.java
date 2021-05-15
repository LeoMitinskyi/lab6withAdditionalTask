package com.LeoMitinskyi.lab6.Commands;

import com.LeoMitinskyi.lab6.Engine;
import com.LeoMitinskyi.lab6.ServerLoop;

public class SaveCommand extends Command{
    public SaveCommand(Engine engine, ServerLoop serverLoop) {
        super(engine,serverLoop);
    }
    @Override
    public String execute(){
        collection.save();
        return "Коллекция успешно сохранена";
    }

    @Override
    public String getDescription() {
        return ":сохранить коллекцию в файл";
    }

    @Override
    public String getName() {
        return "save";
    }
}
