package com.LeoMitinskyi.lab6.Commands;

import com.LeoMitinskyi.lab6.Engine;
import com.LeoMitinskyi.lab6.ServerLoop;
import com.LeoMitinskyi.lab6.types.Person;
import java.io.IOException;

public class UpdateCommand extends Command {

    public UpdateCommand(Engine engine, ServerLoop serverLoop) {
        super(engine,serverLoop);
    }
    @Override
    public String execute() throws IOException, ClassNotFoundException {
        long id = ioManager.LongReader("Enter id:");
        Person element = collection.get(id);
        if (element == null) {
            return "Элемент с данным ключём не найден.";
        } else {
            collection.remove(id);
            AddCommand addCommand = new AddCommand(engine,serverLoop);
            return addCommand.execute();
        }
    }

    @Override
    public String getDescription() {
        return ":обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String getName() {
        return "update";
    }
}
